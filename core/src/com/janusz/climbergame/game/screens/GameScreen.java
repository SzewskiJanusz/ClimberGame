package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.AppleManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.CoffeeManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.managers.StoneManager;
import com.janusz.climbergame.game.managers.TequilaManager;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.pause.PauseController;
import com.janusz.climbergame.game.texts.TapImage;
import com.janusz.climbergame.game.texts.TapToStartLabel;

/**
 * Main game screen
 */
public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    // Flaga gameOver, czy gra się skończyła
    public static boolean gameOver;
    public static boolean onBeginning;
    public static float difficultyTimer;
    public static boolean deathAnimation;

    /* Prywatne pola menadżerów */
    public BananaManager bananaMgr;
    public AnvilManager anvilMgr;
    public TequilaManager tequilaMgr;
    private GameOverManager gameOverMgr;
    private JungleBackground background;
    public CoffeeManager coffeeMgr;
    public StoneManager stoneMgr;
    public AppleManager appleMgr;
    public static boolean paused;
    private boolean added;

    private long diff, start = System.currentTimeMillis();

    // Konstruktor
    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    // W metodzie init inicjalizacja wszystkich menadżerów potrzebnych do gry
    // oraz dodanie ich do sceny
    protected void init()
    {
        onBeginning = true;
        gameOver = false;
        paused = false;
        deathAnimation = false;
        stage.addActor(TapToStartLabel.instance());
        stage.addActor(TapImage.instance());
        ScoreManager.getInstance().ScoreLogic.setScore(0);
        Player.instance().reset();
        difficultyTimer = 0;
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

        bananaMgr.updateEntities(delta);
        anvilMgr.updateEntities(delta);
        tequilaMgr.updateEntities(delta);
        coffeeMgr.updateEntities(delta);
        stoneMgr.updateEntities(delta);
        appleMgr.updateEntities(delta);

        checkIfGameOver();
        difficultyTimer += delta;

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
    }

    private void sleep(int fps) {
        if(fps>0){
            diff = System.currentTimeMillis() - start;
            long targetDelay = 1000/fps;
            if (diff < targetDelay) {
                try{
                    Thread.sleep(targetDelay - diff);
                } catch (InterruptedException e) {}
            }
            start = System.currentTimeMillis();
        }
    }

}



