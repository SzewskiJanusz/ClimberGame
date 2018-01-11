package com.janusz.climbergame.game.screens;

import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.CoffeeManager;
import com.janusz.climbergame.game.managers.StoneManager;
import com.janusz.climbergame.game.managers.TequilaManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.managers.score.ScoreManager;

/**
 * Main game screen
 */
public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    // Flaga gameOver, czy gra się skończyła
    public static boolean gameOver;
    public static float difficultyTimer;

    /* Prywatne pola menadżerów */
    private BananaManager bananaMgr;
    private AnvilManager anvilMgr;
    private TequilaManager tequilaMgr;
    private GameOverManager gameOverMgr;
    private JungleBackground background;
    private TrunkBackground trunk;
    private CoffeeManager coffeeMgr;
    private StoneManager stoneMgr;

    // Konstruktor
    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    // W metodzie init inicjalizacja wszystkich menadżerów potrzebnych do gry
    // oraz dodanie ich do sceny
    protected void init()
    {
        gameOver = false;
        ScoreManager.getInstance().ScoreLogic.setScore(0);
        Player.instance().reset();
        difficultyTimer = 0;
        EntireLiana.get().reset();

        background = new JungleBackground();
        trunk = new TrunkBackground();
        stage.addActor(background);
        stage.addActor(trunk);
        stage.addActor(Player.instance());
        bananaMgr = new BananaManager();
        anvilMgr = new AnvilManager();
        gameOverMgr = new GameOverManager(game);
        background = new JungleBackground();
        trunk = new TrunkBackground();
        tequilaMgr = new TequilaManager();
        coffeeMgr = new CoffeeManager();
        stoneMgr = new StoneManager();

        stage.addActor(ScoreManager.getInstance().ScoreLabel);
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

        EntireLiana.get().moveAllLianasDown(delta);

        bananaMgr.updateEntities(delta);
        anvilMgr.updateEntities(delta);
        tequilaMgr.updateEntities(delta);
        coffeeMgr.updateEntities(delta);
        stoneMgr.updateEntities(delta);
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



