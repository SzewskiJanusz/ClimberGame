package com.janusz.climbergame.game.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.controllers.AnvilController;
import com.janusz.climbergame.game.controllers.BananaController;
import com.janusz.climbergame.game.controllers.EnergyBar;
import com.janusz.climbergame.game.controllers.FrameEnergyBar;
import com.janusz.climbergame.game.controllers.GameOverController;
import com.janusz.climbergame.game.entities.Player;
import com.janusz.climbergame.game.entities.Wall;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.environment.EntireWall;


public class GameScreen extends AbstractScreen
{

    public static Player player;
    public static boolean gameOver;

    private BananaController bc;
    private EntireLiana el;
    private EntireWall ew;
    private AnvilController ac;
    private GameOverController goc;
    private EnergyBar eb;
    private FrameEnergyBar feb;

    private JungleBackground background;
    private TrunkBackground trunk;



    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    public static int selectPlace(int place)
    {
        switch (place)
        {
            case 1: return Wall.WIDTH;
            case 2: return EntireLiana.first_liana_x;
            case 3: return EntireLiana.second_liana_x;
            case 4: return EntireLiana.third_liana_x;
            default: return 0;
        }
    }

    protected void init()
    {
        initPlayer();
        el = new EntireLiana();
        ew = new EntireWall();
        bc = new BananaController();
        ac = new AnvilController();
        goc = new GameOverController();
        eb = new EnergyBar();
        feb = new FrameEnergyBar();
        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(feb);
        stage.addActor(eb);
        stage.addActor(background);
        stage.addActor(trunk);

        // Set background to back
        trunk.toBack();
        background.toBack();

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


    private void movePlayer()
    {
        switch(player.place)
        {
            case 0: player.setX(Wall.WIDTH); break;
            case 1: player.setX(EntireLiana.first_liana_x); break;
            case 2: player.setX(EntireLiana.second_liana_x); break;
            case 3: player.setX(EntireLiana.third_liana_x); break;
            default: throw new IllegalArgumentException("Error in player place. (" + player.place +")");
        }
    }

    private void update(float delta)
    {
        stage.act();
        player.toFront();
        feb.toFront();
        eb.toFront();
        movePlayer();

        ew.moveWallDown(delta);
        el.moveAllLianasDown(delta);

        bc.updateAllBananas(delta,eb);
        ac.updateAllAnvils(delta);

        checkIfGameOver();

    }

    private void checkIfGameOver()
    {
        if (gameOver)
        {
            stage.addActor(goc.getGameOverLabel());
            stage.addActor(goc.getYourFinalScoreTextLabel());
            stage.addActor(goc.getYourFinalScoreLabel());
            stage.addActor(goc.getYourBestScoreTextLabel());
            stage.addActor(goc.getYourBestScoreLabel());
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



