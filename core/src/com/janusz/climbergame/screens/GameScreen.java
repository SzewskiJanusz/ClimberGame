package com.janusz.climbergame.screens;


import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.LianaTile;
import com.janusz.climbergame.entities.Player;
import com.janusz.climbergame.entities.Wall;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    // Lists for moving effect
    public List<LianaTile> first_wholeLiana;
    public List<LianaTile> second_wholeLiana;
    public List<LianaTile> third_wholeLiana;

    // Walls for moving effect
    public List<Wall> walls;

    public static final int first_liana_x = 575;
    public static final int second_liana_x = 950;
    public static final int third_liana_x = 1325;


    public static Player player;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initPlayer();
        initLiana();
        initWall();

    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }

    private void initLiana()
    {
        first_wholeLiana = new ArrayList<LianaTile>();
        second_wholeLiana = new ArrayList<LianaTile>();
        third_wholeLiana = new ArrayList<LianaTile>();

        createLianaTile();
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
        moveWholeLianaDown(delta);


        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void movePlayer()
    {
        switch(player.place)
        {
            case 0: player.setX(Wall.WIDTH); break;
            case 1: player.setX(first_liana_x); break;
            case 2: player.setX(second_liana_x); break;
            case 3: player.setX(third_liana_x); break;
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

    public void moveWholeLianaDown(float delta)
    {
        for (int i = 0 ; i < first_wholeLiana.size() ;i ++)
        {
            first_wholeLiana.get(i).moveLianaTile(delta);
            second_wholeLiana.get(i).moveLianaTile(delta);
            third_wholeLiana.get(i).moveLianaTile(delta);
        }

        checkIfNeedCreateTile();
        checkIfNeedDisposeLastTile();
    }

    private void checkIfNeedCreateTile()
    {
        int lastTile = first_wholeLiana.size() - 1;

        if (first_wholeLiana.get(lastTile).getY() + LianaTile.HEIGHT <= LianaTile.STARTING_Y )
        {
            createLianaTile();
        }
    }

    private void checkIfNeedDisposeLastTile()
    {
        if (first_wholeLiana.get(0).getY() + LianaTile.HEIGHT <= 0)
        {
            disposeLastLianaTile();
        }
    }

    public void createLianaTile()
    {
        LianaTile l = new LianaTile(first_liana_x);
        first_wholeLiana.add(l);
        stage.addActor(l);

        LianaTile l1 = new LianaTile(second_liana_x);
        second_wholeLiana.add(l1);
        stage.addActor(l1);

        LianaTile l2 = new LianaTile(third_liana_x);
        third_wholeLiana.add(l2);
        stage.addActor(l2);
    }

    public void disposeLastLianaTile()
    {
        first_wholeLiana.get(0).remove();
        first_wholeLiana.remove(0);

        second_wholeLiana.get(0).remove();
        second_wholeLiana.remove(0);

        third_wholeLiana.get(0).remove();
        third_wholeLiana.remove(0);
    }

}



