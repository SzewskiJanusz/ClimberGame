package com.janusz.climbergame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.managers.DifficultyController;
import com.janusz.climbergame.game.managers.HUD;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.player.MovingListener;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;

import java.util.ArrayList;
import java.util.List;


public class PlayGameState implements GameState
{
    public GameScreen gameScreen;
    public EntireLiana allLianas;
    public HUD hud;
    public Player player;
    public List<AbstractItem> entities;
    public boolean deathAnimation;
    public double queueTimer;
    public com.janusz.climbergame.game.managers.DifficultyController difficultyControl;
    public ScoreManager scoreMgr;
    private ClimberGame game;

    public PlayGameState(BeginningState bs, ClimberGame game)
    {
        this.gameScreen = bs.gameScreen;
        this.allLianas = bs.allLianas;
        this.game = game;
        init();
    }

    private void init()
    {
        Gdx.input.setInputProcessor(gameScreen.stage);
        GameSound.instance();
        player = new Player(this);
        hud = new HUD(this);
        entities = new ArrayList<AbstractItem>();
        gameScreen.stage.addActor(player);
        scoreMgr = new ScoreManager();
        gameScreen.stage.addActor(scoreMgr.ScoreLabel);
        gameScreen.stage.addListener(new MovingListener(player, hud.pauseControl));
        difficultyControl = new DifficultyController(this);
    }

    @Override
    public void tick(float delta)
    {
        if (!hud.pauseControl.isGamePaused())
        {
            update(delta);
        }
    }

    @Override
    public void update(float delta)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            hud.pauseControl.pauseGame();
        }
        gameScreen.stage.act();
        scoreMgr.update();
        allLianas.moveAllLianasDown(delta);
        hud.indicatorControl.update();
        updateEntities();
        checkIfGameOver();
        difficultyControl.difficultyUpdate(delta);
        difficultyControl.itemSpawner.trySpawnItemFromQueue();
        queueTimer -= delta;
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

    private boolean checkCollision(AbstractItem a)
    {
        return player.playerState == PlayerState.CLIMBING_LIANA &&
                Intersector.overlaps(a.getBounds(),
                        a.getName().equals("good") ? player.getBounds() :
                                player.getBadCollisionBounds());
    }

    private void checkIfGameOver()
    {
        if (deathAnimation)
        {
            player.playerState = PlayerState.DYING;
            final PlayGameState temp = this;
            Timer.schedule(new Timer.Task()
                           {
                               @Override
                               public void run()
                               {
                                       hud.pauseControl.pauseButton.remove();
                                       scoreMgr.ScoreLabel.remove();
                                       hud.indicatorControl.dispose();
                                       Timer.instance().clear();
                                       hud.lvlTexts.remove();
                                       player.remove();
                                       gameScreen.setState(new GameOverState(temp, game));
                               }

                           }
                    , 1       //    (delay)
            );
        }
    }
}
