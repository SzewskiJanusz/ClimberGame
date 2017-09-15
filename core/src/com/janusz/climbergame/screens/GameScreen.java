package com.janusz.climbergame.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.LianaTile;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    public List<LianaTile> lianas;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initLiana();
    }

    private void initLiana()
    {
        lianas = new ArrayList<LianaTile>();
        createLianaTile();
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();
        moveWholeLianaDown(delta);

        if (lianas.get(lianas.size() - 1).getY() + LianaTile.HEIGHT <= LianaTile.STARTING_Y )
        {
            createLianaTile();
        }

        if (lianas.get(0).getY() + LianaTile.HEIGHT <= 0)
        {
            disposeLastLianaTile();
        }

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
        for(LianaTile l : lianas)
        {
            l.moveLianaTile(delta);
        }
    }

    public void createLianaTile()
    {
        LianaTile l = new LianaTile();
        lianas.add(l);
        stage.addActor(l);
    }

    public void disposeLastLianaTile()
    {
        lianas.get(0).remove();
        lianas.remove(0);
    }

}



