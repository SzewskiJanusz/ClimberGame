package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2018-01-14.
 */

public abstract class GoodManager<T extends AbstractItem>
{
    // Lista obiektów
    protected List<T> entities;
    protected float entitySpawnTime;
    private int delay ;

    /*
     * Metody abstrakacyjne do poprawnego zarządzania obiektami
     */

    /**
     * Losuje liczbę entitySpawnTime
     */
    protected abstract void randomizeSpawnTime();

    /**
     * Tworzy ustalony obiekt
     */
    protected abstract void spawnEntity();

    /**
     * Wywołanie efektu po kolizji
     */
    protected abstract void triggerEffect();

    /**
     * Initialize List and timer
     * @param delay - starting delay
     */
    public GoodManager(int delay)
    {
        this.delay = delay;
        entities = new ArrayList<T>();
        randomizeSpawnTime();
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

    /**
     * Zwraca X danego miejsca w widoku
     * @param place - miejsce (od 0 do 3)
     * @return
     */
    protected int selectPlace(int place)
    {
        switch (place)
        {
            case 2: return EntireLiana.first_liana_x;
            case 3: return EntireLiana.second_liana_x;
            case 4: return EntireLiana.third_liana_x;
            default: return 0;
        }
    }

    public void startTimer()
    {
        if (!GameScreen.paused){
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

    }
}
