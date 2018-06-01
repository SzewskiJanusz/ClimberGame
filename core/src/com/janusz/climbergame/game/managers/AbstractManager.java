package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.environment.EntireLiana;

/**
 * Created by Janusz on 2018-02-03.
 */

public abstract class AbstractManager
{
    // Time after spawn occurs
    protected float entitySpawnTime;
    // Spawn delay
    private int delay ;

    /**
     * Losuje liczbę entitySpawnTime
     */
    protected abstract void randomizeSpawnTime();

    /**
     * Tworzy ustalony obiekt
     */
    protected abstract void createEntity();

    /**
     * Initialize List and timer
     * @param delay - starting delay
     */
    public AbstractManager(int delay)
    {
        this.delay = delay;
        randomizeSpawnTime();
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

    public void startTimer(int del)
    {
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               createEntity();
                           }
                       }
                , del      //    (delay)
                , entitySpawnTime
        );
    }

    public void startTimer()
    {
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               createEntity();
                           }
                       }
                , delay      //    (delay)
                , entitySpawnTime
        );
    }
}
