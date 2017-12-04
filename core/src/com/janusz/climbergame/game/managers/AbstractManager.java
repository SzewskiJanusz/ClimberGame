package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.entities.Wall;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-11-02.
 */

public abstract class AbstractManager<T extends AbstractItem>
{

    protected List<T> entities;
    protected float entitySpawnTime;


    protected abstract void randomizeSpawnTime();
    protected abstract void spawnEntity();
    protected abstract void triggerEffect();

    /**
     * Initialize List and timer
     * @param delay - starting delay
     */
    public AbstractManager(int delay)
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
                triggerEffect();

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
        if (Player.instance().playerState == PlayerState.CLIMBING_LIANA ||
                Player.instance().playerState == PlayerState.CLIMBING_WALL)
        {
            return Intersector.overlaps(
                    entities.get(i).getBounds(),
                    Player.instance().getBounds()
            );
        }
        else
            return false;

    }

    protected int selectPlace(int place)
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

}
