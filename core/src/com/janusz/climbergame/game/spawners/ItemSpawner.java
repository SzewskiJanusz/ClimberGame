package com.janusz.climbergame.game.spawners;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.states.PlayGameState;

import java.util.List;

public class ItemSpawner
{
    public Timer.Task spawnTimerTask;
    public QueueManager queue;
    private PlayGameState playGameState;
    private ItemRandomizer itemRandomizer;
    private int timerCounter;

    public ItemSpawner(PlayGameState pgs)
    {
        this.queue = new QueueManager();
        this.playGameState = pgs;
        initializeTimer();
        itemRandomizer = new ItemRandomizer(playGameState);
        timerCounter = 0;
    }

    public void spawnWaves(final int waves)
    {
        ItemWave wave = new ItemWave(playGameState);
        wave.loadWave(waves);
        List<AbstractItem> items = wave.getWave();
        for(AbstractItem ai : items)
            queue.addToQueue(ai);
    }

    private void initializeTimer()
    {

        spawnTimerTask = Timer.schedule(new Timer.Task(){
                               @Override
                               public void run()
                               {
                                   int roll = MathUtils.random(0,3);
                                   if (roll == 0 || roll == 1 || roll == 2)
                                        addRandomItemToQueue();

                                   if (timerCounter % 4 == 0|| roll == 4)
                                   {
                                       addEffectItemToQueue();
                                   }

                                   timerCounter++;
                               }
                           }
                    , 2      //    delay in sec
                    , 0.9f    // frequency in sec
            );
    }

    private void addEffectItemToQueue()
    {
        int rnd = MathUtils.random(1,3);
        if (rnd == 1)
            queue.addToQueue(itemRandomizer.getCoffeeWithRandomDirection());
        if (rnd == 2)
            queue.addToQueue(itemRandomizer.getFatItemWithRandomDirection());
        else
            queue.addToQueue(itemRandomizer.getTequilaWithRandomDirection());
    }

    private void addRandomItemToQueue()
    {
        boolean isGood = MathUtils.randomBoolean();
        if (isGood)
            queue.addToQueue(itemRandomizer.getRandomGoodItem());
        else
            queue.addToQueue(itemRandomizer.getRandomBadItem());
    }

    public void trySpawnItemFromQueue()
    {
        if (playGameState.queueTimer <= 0)
        {
            AbstractItem j;
            if ((j = queue.getFirst()) != null)
            {
                playGameState.entities.add(j);
                playGameState.gameScreen.stage.addActor(j);
                j.toFront();
                playGameState.queueTimer = 0.45;
            }
        }
    }
}
