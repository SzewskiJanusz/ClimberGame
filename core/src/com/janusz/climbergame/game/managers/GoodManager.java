package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.Intersector;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2018-01-14.
 */

public abstract class GoodManager<T extends AbstractItem> extends AbstractManager
{
    // Lista obiektów
    protected List<T> entities;

    /**
     * Initialize List and timer
     * @param delay - starting delay
     */
    public GoodManager(int delay)
    {
        super(delay);
        entities = new ArrayList<T>();
    }

    /**
     * Odświeża wszystkie obiekty menadżera
     * @param delta - czas w jednej klatce
     */
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

    /**
     * Sprawdza czy obiekt ma kolizje z graczem
     * @param i - indeks poszczególnego obiektu
     * @return
     */
    protected boolean checkCollision(int i)
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
}
