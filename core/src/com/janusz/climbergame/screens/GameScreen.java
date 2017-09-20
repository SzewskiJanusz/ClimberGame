package com.janusz.climbergame.screens;


import com.badlogic.gdx.graphics.Texture;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.Banana;
import com.janusz.climbergame.entities.Player;
import com.janusz.climbergame.entities.Wall;
import com.janusz.climbergame.environment.EntireLiana;
import com.janusz.climbergame.environment.EntireWall;



/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    private EntireLiana el;
    private EntireWall ew;

    public static Player player;
    Banana b;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initPlayer();
        el = new EntireLiana();
        ew = new EntireWall();
        b = new Banana(new Texture("banana.png"), 500);
        stage.addActor(b);
    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();

        ew.moveWallDown(delta);
        el.moveAllLianasDown(delta);

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
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

    private void update()
    {
        stage.act();
        player.toFront();
        movePlayer();
    }

}



