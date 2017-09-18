package com.janusz.climbergame.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.LianaTile;
import com.janusz.climbergame.entities.Player;
import com.janusz.climbergame.entities.Wall;
import com.janusz.climbergame.environment.EntireLiana;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    private EntireLiana el;

    // Walls for moving effect
    private List<Wall> walls;

    public static Player player;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initPlayer();
        el = new EntireLiana();
        initWall();
    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }



    private void initWall()
    {
        walls = new ArrayList<Wall>();

        // Starter wall. Spawns on entire height of screen
        Wall w = new Wall(true);
        walls.add(w);
        stage.addActor(w);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();

        player.toFront();

        movePlayer();
        moveWallDown(delta);
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

    private void moveWallDown(float delta)
    {
        for (int i = 0 ; i < walls.size() ; i++)
        {
            walls.get(i).moveWall(delta);
        }

        checkIfNeedCreateWall();
        checkIfNeedDisposeWall();
    }

    private void checkIfNeedDisposeWall()
    {
        if (walls.get(0).getY() + Wall.HEIGHT <= Wall.STARTING_Y)
        {
            disposeLastWall();
        }
    }

    private void disposeLastWall()
    {
        walls.get(0).remove();
        walls.remove(0);

    }

    private void checkIfNeedCreateWall()
    {
        int lastWall = walls.size() - 1;

        if (walls.get(lastWall).getY() <= Wall.STARTING_Y )
        {
            createNewWall();
        }

    }

    private void createNewWall()
    {
        Wall w = new Wall(false);
        walls.add(w);
        stage.addActor(w);
    }


    private void update()
    {
        stage.act();
    }





}



