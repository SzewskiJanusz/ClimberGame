package com.janusz.climbergame.game.controllers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-11-02.
 */

public abstract class AbstractController<T extends AbstractItem>
{

    protected List<T> entities;
    protected float entitySpawnTime;


    protected abstract void randomizeSpawnTime();
    protected abstract void spawnEntity();

    /**
     * Initialize List and timer
     * @param delay - starting delay
     */
    public AbstractController(int delay)
    {
        entities = new ArrayList<T>();
        randomizeSpawnTime();


        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               spawnEntity();
                           }
                       }
                , delay       //    (delay)
                , entitySpawnTime
        );
    }

    public void updateEntities(float delta)
    {
        for (int i = 0 ; i < entities.size() ; i++)
        {
            if (checkCollision(i))
            {
                entities.get(i).remove();
                entities.remove(i);
                GameScreen.gameOver = true;
                break;
            }
        }

        for(T a : entities)
        {
            a.update(delta);
        }
    }

    private boolean checkCollision(int i)
    {
        return Intersector.overlaps(
                entities.get(i).getBounds(),
                GameScreen.player.getBounds()
        );
    }

}
