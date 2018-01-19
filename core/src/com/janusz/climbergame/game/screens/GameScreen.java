package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.CoffeeManager;
import com.janusz.climbergame.game.managers.StoneManager;
import com.janusz.climbergame.game.managers.TequilaManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.texts.TapImage;
import com.janusz.climbergame.game.texts.TapToStartLabel;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Main game screen
 */
public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    // Flaga gameOver, czy gra się skończyła
    public static boolean gameOver;
    public static boolean onBeginning;
    public static float difficultyTimer;

    /* Prywatne pola menadżerów */
    public static BananaManager bananaMgr;
    public static AnvilManager anvilMgr;
    public static TequilaManager tequilaMgr;
    public GameOverManager gameOverMgr;
    public JungleBackground background;
    public static CoffeeManager coffeeMgr;
    public static StoneManager stoneMgr;

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
        TapToStartLabel.instance().toFront();
        TapImage.instance().toFront();
    }

    @Override
    public void render(float delta)
    {
        if (!gameOver)
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
        IndicatorController.instance().update();

        bananaMgr.updateEntities(delta);
        anvilMgr.updateEntities(delta);
        tequilaMgr.updateEntities(delta);
        coffeeMgr.updateEntities(delta);
        stoneMgr.updateEntities(delta);

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



