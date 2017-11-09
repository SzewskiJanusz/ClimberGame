package com.janusz.climbergame.game.screens;


import com.badlogic.gdx.math.Vector2;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.background.TrunkBackground;
import com.janusz.climbergame.game.managers.AnvilManager;
import com.janusz.climbergame.game.managers.BananaManager;
import com.janusz.climbergame.game.managers.EnergyBar;
import com.janusz.climbergame.game.managers.FrameEnergyBar;
import com.janusz.climbergame.game.managers.GameOverManager;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.Wall;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.environment.EntireWall;


public class GameScreen extends AbstractScreen
{

    public static Player player;
    public static boolean gameOver;

    private BananaManager bc;
    private EntireWall ew;
    private AnvilManager ac;
    private GameOverManager goc;
    private FrameEnergyBar feb;

    private JungleBackground background;
    private TrunkBackground trunk;

    private Vector2 velocity;


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
        velocity = new Vector2(4, -4);
        initPlayer();
        ew = new EntireWall();
        bc = new BananaManager();
        ac = new AnvilManager();
        goc = new GameOverManager();
        feb = new FrameEnergyBar();
        background = new JungleBackground();
        trunk = new TrunkBackground();

        stage.addActor(feb);
        stage.addActor(EnergyBar.get());
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
        player.toFront();
        feb.toFront();
        EnergyBar.get().toFront();
        movePlayer(delta);

        ew.moveWallDown(delta);
        EntireLiana.get().moveAllLianasDown(delta);

        bc.updateEntities(delta);
        ac.updateEntities(delta);
        trunk.moveDown(delta);

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



