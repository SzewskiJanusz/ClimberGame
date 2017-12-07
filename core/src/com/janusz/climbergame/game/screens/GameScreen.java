package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.Gdx;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.CoffeeManager;
import com.janusz.climbergame.game.managers.TequilaManager;
import com.janusz.climbergame.game.managers.energy.EnergyManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.environment.EntireWall;
import com.janusz.climbergame.game.managers.score.ScoreManager;


public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    public static boolean gameOver;
    public static float difficultyTimer;

    private BananaManager bananaMgr;
    private EntireWall entireWall;
    private AnvilManager anvilMgr;
    private TequilaManager tequilaMgr;
    private GameOverManager gameOverMgr;
    private JungleBackground background;
    private TrunkBackground trunk;
    private CoffeeManager coffeeMgr;




    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        gameOver = false;
        ScoreManager.getInstance().ScoreLogic.setScore(0);
        Player.instance().place = 2;
        difficultyTimer = 0;
        EntireLiana.get().reset();

        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(background);
        stage.addActor(trunk);


        stage.addActor(Player.instance());
        entireWall = new EntireWall();
        bananaMgr = new BananaManager();
        anvilMgr = new AnvilManager();
        gameOverMgr = new GameOverManager(game);
        background = new JungleBackground();
        trunk = new TrunkBackground();
        tequilaMgr = new TequilaManager();
        coffeeMgr = new CoffeeManager();

        stage.addActor(ScoreManager.getInstance().ScoreLabel);
        stage.addActor(EnergyManager.getInstance().frameEnergyBar);
        stage.addActor(EnergyManager.getInstance().energyBar);
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
        Player.instance().toFront();
        Player.instance().movePlayer(delta);

        entireWall.moveWallDown(delta);
        EntireLiana.get().moveAllLianasDown(delta);

        bananaMgr.updateEntities(delta);
        anvilMgr.updateEntities(delta);
        tequilaMgr.updateEntities(delta);
        coffeeMgr.updateEntities(delta);
        trunk.moveDown(delta);


        checkIfGameOver();
        difficultyTimer += delta;
    }

    private void checkIfGameOver()
    {
        if (gameOver)
        {
            stage.addActor(gameOverMgr.getTable());
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



