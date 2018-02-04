package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-03.
 */

abstract class AbstractManager
{
    // Lista obiektów
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
    public AbstractManager(int delay)
    {
        this.delay = delay;
        randomizeSpawnTime();
    }

    /**
     * Odświeża wszystkie obiekty menadżera
     * @param delta - czas w jednej klatce
     */
    abstract public void updateEntities(float delta);


    /**
     * Sprawdza czy obiekt ma kolizje z graczem
     * @param i - indeks poszczególnego obiektu
     * @return
     */
    abstract protected boolean checkCollision(int i);


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
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               if (!GameScreen.paused)
                                    spawnEntity();
                           }
                       }
                , delay       //    (delay)
                , entitySpawnTime
        );
    }
}
