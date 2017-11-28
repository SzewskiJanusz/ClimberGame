package com.janusz.climbergame.game.screens;


import com.badlogic.gdx.math.Vector2;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.energy.EnergyManager;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.Wall;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.environment.EntireWall;
import com.janusz.climbergame.game.score.ScoreManager;


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

    private Vector2 velocity;


    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        velocity = new Vector2(4, -4);
        initPlayer();
        entireWall = new EntireWall();
        bananaMgr = new BananaManager();
        anvilMgr = new AnvilManager();
        gameOverMgr = new GameOverManager();
        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(EnergyManager.getInstance().frameEnergyBar);
        stage.addActor(EnergyManager.getInstance().energyBar);
        stage.addActor(background);
        stage.addActor(trunk);
        stage.addActor(ScoreManager.getInstance().ScoreLabel);

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


    private void movePlayer(float delta)
    {
        if (Player.playerState == PlayerState.FLYING_LEFT)
        {
            if (player.catchLiana())
            {
                Player.playerState = PlayerState.CLIMBING_LIANA;
                setPlayerOnLiana();
            }
            else
            {
                velocity.y += delta * 10;

                player.setX(player.getX() - velocity.x);
                player.setY(player.getY() - velocity.y);
            }

        }
        else if (Player.playerState == PlayerState.FLYING_RIGHT)
        {
            if (player.catchLiana())
            {
                Player.playerState = PlayerState.CLIMBING_LIANA;
                setPlayerOnLiana();
            }
            else
            {
                velocity.y += delta * 10;

                player.setX(player.getX() + velocity.x);
                player.setY(player.getY() - velocity.y);
            }

        }
        else if (Player.playerState == PlayerState.FLYING_WALL)
        {
            velocity.y += delta * 10;

            player.setX(player.getX() - velocity.x);
            player.setY(player.getY() - velocity.y);
        }
    }

    private void setPlayerOnLiana()
    {
        switch(player.place)
        {
            case 0: player.setX(Wall.WIDTH); break;
            case 1: player.setX(EntireLiana.first_liana_x - 16); break;
            case 2: player.setX(EntireLiana.second_liana_x - 16); break;
            case 3: player.setX(EntireLiana.third_liana_x - 16); break;
            default: throw new IllegalArgumentException("Error in player place. (" + player.place +")");
        }
        player.setY(Player.STARTING_Y);
        velocity.y = -4;
    }

    private void update(float delta)
    {
        stage.act();
        ScoreManager.getInstance().update();
        player.toFront();
        EnergyManager.getInstance().frameEnergyBar.toFront();
        EnergyManager.getInstance().energyBar.toFront();
        movePlayer(delta);

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



