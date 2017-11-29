package com.janusz.climbergame.game.screens;

import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.energy.EnergyManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.environment.EntireWall;
import com.janusz.climbergame.game.managers.score.ScoreManager;


public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{

    public static Player player;
    public static boolean gameOver;

    private BananaManager bananaMgr;
    private EntireWall entireWall;
    private AnvilManager anvilMgr;
    private GameOverManager gameOverMgr;
    private JungleBackground background;
    private TrunkBackground trunk;




    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(background);
        stage.addActor(trunk);


        initPlayer();
        entireWall = new EntireWall();
        bananaMgr = new BananaManager();
        anvilMgr = new AnvilManager();
        gameOverMgr = new GameOverManager();
        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(ScoreManager.getInstance().ScoreLabel);
        stage.addActor(EnergyManager.getInstance().frameEnergyBar);
        stage.addActor(EnergyManager.getInstance().energyBar);
    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta)
    {
        if (!gameOver)
        {
            super.render(delta);
            update(delta);

            spriteBatch.begin();
            stage.draw();
            spriteBatch.end();
        }
    }

    private void update(float delta)
    {
        stage.act();
        ScoreManager.getInstance().update();
        player.toFront();
        player.movePlayer(delta);

        entireWall.moveWallDown(delta);
        EntireLiana.get().moveAllLianasDown(delta);

        bananaMgr.updateEntities(delta);
        anvilMgr.updateEntities(delta);
        trunk.moveDown(delta);

        checkIfGameOver();

    }

    private void checkIfGameOver()
    {
        if (gameOver)
        {
            stage.addActor(gameOverMgr.getGameOverLabel());
            stage.addActor(gameOverMgr.getYourFinalScoreTextLabel());
            stage.addActor(gameOverMgr.getYourFinalScoreLabel());
            stage.addActor(gameOverMgr.getYourBestScoreTextLabel());
            stage.addActor(gameOverMgr.getYourBestScoreLabel());
        }
    }

    @Override
    public void dispose()
    {
        super.dispose();
        spriteBatch.dispose();
        stage.dispose();
    }
}



