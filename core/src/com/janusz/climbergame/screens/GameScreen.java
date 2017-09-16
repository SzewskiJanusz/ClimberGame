package com.janusz.climbergame.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.LianaTile;
import com.janusz.climbergame.entities.Wall;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    public List<LianaTile> first_wholeLiana;
    public List<LianaTile> second_wholeLiana;
    public List<LianaTile> third_wholeLiana;

    private final int first_liana_x = 400;
    private final int second_liana_x = 700;
    private final int third_liana_x = 1000;

    private Wall wall;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initLiana();
        initWall();
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
        wall = new Wall();
        stage.addActor(wall);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();
        moveWholeLianaDown(delta);

        checkIfNeedCreateTile();
        checkIfNeedDisposeTile();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
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
    }

    private void checkIfNeedCreateTile()
    {
        int lastTile = first_wholeLiana.size() - 1;

        if (first_wholeLiana.get(lastTile).getY() + LianaTile.HEIGHT <= LianaTile.STARTING_Y )
        {
            createLianaTile();
        }
    }

    private void checkIfNeedDisposeTile()
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



