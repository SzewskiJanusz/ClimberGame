package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Satellite;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.*;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.managers.*;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.pause.PauseController;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.texts.*;
import com.janusz.climbergame.shared.DefComponents;

import java.util.ArrayList;
import java.util.List;


/**
 * Main game screen
 */
public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    /* Flags */
    public static boolean gameOver;
    public static boolean onBeginning;
    public static boolean deathAnimation;
    public static double goodFreq;
    public static double badFreq;
    public static boolean paused;
    private boolean added;

    /* Managers (builders) */
    public BananaManager bananaMgr;
    public AnvilManager anvilMgr;
    public TequilaManager tequilaMgr;
    private GameOverManager gameOverMgr;
    private JungleBackground background;
    public CoffeeManager coffeeMgr;
    public StoneManager stoneMgr;
    public AppleManager appleMgr;
    public WatermelonManager waterMgr;
    public CarrotManager carrotMgr;
    public PearManager pearMgr;
    public TreasureManager treasureMgr;
    public GrapesManager grapesMgr;
    public FriesManager friesMgr;
    public TrashcanManager trashMgr;
    public SatelliteManager satelliteMgr;

    /* Queue to avoid stacking items */
    private double queueTimer;

    /* All entities on stage */
    public static List<AbstractItem> entities;

    /* FPS limiter variables */
    private long diff, start = System.currentTimeMillis();

    /* Level selector stuff */
    public static int levelVelocity;
    private static float difficultyTimer;
    public static int level;



    // Constructor
    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    /**
     * Initialize all stuff that is needed for game start
     */
    protected void init()
    {
        GameSound.instance().prepareSounds();
        level = 1;
        onBeginning = true;
        gameOver = false;
        paused = false;
        deathAnimation = false;
        goodFreq = 0;
        badFreq = 0;
        difficultyTimer = 0;
        levelVelocity = 0;
        QueueManager.instance().reset();
        entities = new ArrayList<AbstractItem>();
        entities.clear();
        stage.addActor(TapToStartLabel.instance());
        stage.addActor(TapImage.instance());
        ScoreManager.getInstance().ScoreLogic.setScore(0);
        Player.instance().reset();
        EntireLiana.get().reset();
        background = new JungleBackground();
        stage.addActor(background);
        bananaMgr = new BananaManager();
        anvilMgr = new AnvilManager();
        gameOverMgr = new GameOverManager(game);
        background = new JungleBackground();
        tequilaMgr = new TequilaManager();
        coffeeMgr = new CoffeeManager();
        stoneMgr = new StoneManager();
        appleMgr = new AppleManager();
        waterMgr = new WatermelonManager();
        carrotMgr = new CarrotManager();
        pearMgr = new PearManager();
        treasureMgr = new TreasureManager();
        grapesMgr = new GrapesManager();
        friesMgr = new FriesManager();
        trashMgr = new TrashcanManager();
        satelliteMgr = new SatelliteManager();

        stage.addActor(PauseController.instance().pauseButton);
        stage.addActor(PauseController.instance().pauseLabel);
        TapToStartLabel.instance().toFront();
        TapImage.instance().toFront();
        PauseController.instance().hideLabel();
        stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (Player.instance().playerState == PlayerState.CLIMBING_LIANA && !GameScreen.paused)
                {
                    if (isFingerOnLeft(x, Player.instance().getX() + Player.instance().getWidth() / 2))
                    {
                        if (!Player.drunk)
                            Player.instance().jumpLeft();
                        else
                            Player.instance().jumpRight();
                    }
                    else
                    {
                        if (!Player.drunk)
                            Player.instance().jumpRight();
                        else
                            Player.instance().jumpLeft();
                    }
                }
              return true;
            }
        });
    }

    /**
     * Return if touch is on the left of player
     * @param screenX - touch X
     * @param playerOnScreenX - player X
     * @return true - touched on left, false - touched on right
     */
    private boolean isFingerOnLeft(float screenX, float playerOnScreenX)
    {
        return playerOnScreenX > screenX;
    }

    @Override
    public void render(float delta)
    {
        if (!gameOver)
        {
            if (!paused)
            {

                super.render(delta);

                if (!onBeginning)
                {
                    update(delta);
                }
                else
                {
                    EntireLiana.get().moveAllLianasDown(delta);
                    TapImage.instance().toFront();
                    TapToStartLabel.instance().toFront();
                }
            }
        }

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();

        sleep(60);
    }

    private void update(float delta)
    {
        stage.act();
        ScoreManager.getInstance().update();

        EntireLiana.get().moveAllLianasDown(delta);
        IndicatorController.instance().update();

        updateEntities();

        checkIfGameOver();
        difficultyUpdate(delta);

        if (queueTimer <= 0)
        {
            AbstractItem j;
            if ((j = QueueManager.instance().getFirst()) != null)
            {
                entities.add(j);
                stage.addActor(j);
                queueTimer = 0.5;
            }
        }
        queueTimer -= delta;

    }

    private void difficultyUpdate(float delta)
    {
        if (difficultyTimer >= 20 + level)
        {
            level++;
            levelVelocity += 20 + level;
            difficultyTimer = 0;
            stage.addActor(new BouncingText("LEVEL UP!",DefComponents.LABEL_STYLE, Effect.DEFAULT));
            GameSound.instance().playLevelUp();
            switch(level)
            {
                case 2: friesMgr.startTimer(); break;
                case 3: waterMgr.startTimer(); break;
                case 4: trashMgr.startTimer(); break;
                case 5: carrotMgr.startTimer(); break;
                case 6: treasureMgr.startTimer();
                    satelliteMgr.startTimer(); break;
                case 7: satelliteMgr.startTimer(); break;
                case 8: pearMgr.startTimer(); break;
                case 9: grapesMgr.startTimer(); break;
            }
        }
        else
        {
            difficultyTimer += delta;
        }
    }

    private void checkIfGameOver()
    {
        if (deathAnimation)
        {
            added = false;
            Player.instance().playerState = PlayerState.DYING;

            Timer.schedule(new Timer.Task(){
                               @Override
                               public void run()
                               {
                                   if (deathAnimation)
                                   {
                                       gameOver = true;
                                       if (!added)
                                       {
                                           stage.addActor(gameOverMgr.getTable());
                                           PauseController.instance().pauseButton.remove();
                                           ScoreManager.getInstance().ScoreLabel.remove();
                                           Timer.instance().clear();
                                           added = true;
                                       }

                                       Player.instance().remove();
                                   }
                               }
                           }
                    , 1       //    (delay)
            );

        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        spriteBatch.dispose();
        stage.dispose();
        GameSound.instance().dispose();
    }

    private void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e)
                {
                    //Do nothing
                }
            }
            start = System.currentTimeMillis();
        }
    }

    private void updateEntities()
    {

        for (int i = 0 ; i < entities.size() ; i++)
        {
            if (checkCollision(entities.get(i)))
            {
                entities.get(i).triggerEffect();
                entities.get(i).remove();
                entities.remove(i);
                break;
            }
        }
    }

    /**
     * Check collision with player
     * @param a - entity on stage to check with player
     * @return if collision happened
     */
    private boolean checkCollision(AbstractItem a)
    {
        if (a.getName().equals("good"))
        {
            return Player.instance().playerState == PlayerState.CLIMBING_LIANA &&
                    Intersector.overlaps(a.getBounds(),
                            Player.instance().getBounds());
        }
        else
        {
            return Player.instance().playerState == PlayerState.CLIMBING_LIANA &&
                    Intersector.overlaps(
                    a.getBounds(),
                    Player.instance().getBadCollisionBounds());
        }
    }
}



