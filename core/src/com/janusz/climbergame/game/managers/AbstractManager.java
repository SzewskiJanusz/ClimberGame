package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.managers.queue.QueueManager;

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

    public abstract AbstractItem build(int x);

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
    private int selectPlace(int place)
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
                               createEntityAndAddToQueue();
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
                               createEntityAndAddToQueue();
                           }
                       }
                , delay      //    (delay)
                , entitySpawnTime
        );
    }

    private void createEntityAndAddToQueue()
    {
        int x = selectPlace(MathUtils.random(2,4));
        AbstractItem ai = build(x);
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(ai);
    }
}
