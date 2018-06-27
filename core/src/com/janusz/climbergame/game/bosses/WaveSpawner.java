package com.janusz.climbergame.game.bosses;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import java.util.List;

public class WaveSpawner
{
    private Timer.Task spawner;
    int goodwavecounter = 0;
    public WaveSpawner()
    {
    }

    public void spawnWaves(final int lvl, final int waves)
    {
        spawner = Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                ItemWave wave = new ItemWave(MathUtils.random(lvl + 2, lvl + 6),
                        MathUtils.random(0,1) == 1);
                List<AbstractItem> items = wave.getWave();
                for(AbstractItem ai : items)
                    QueueManager.instance().addToQueue(ai);
            }
        },0,1,waves);
    }

    public void spawnWaves(final int lvl, final int goodwaves, final int badwaves)
    {
        spawner = Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                boolean isgood = MathUtils.random(0,1) == 1;
                if (isgood && goodwavecounter < goodwaves) goodwavecounter++;
                else isgood = false;
                ItemWave wave = new ItemWave(MathUtils.random(lvl + 2, lvl + 6),
                        isgood);
                List<AbstractItem> items = wave.getWave();
                for(AbstractItem ai : items)
                    QueueManager.instance().addToQueue(ai);
            }
        },0,1, goodwaves + badwaves);
    }

    public void spawnNonOrderedMixedWaves(final int lvl, final int items)
    {
        spawner = Timer.schedule(new Timer.Task()
        {
            @Override
            public void run()
            {
                ItemWave wave = new ItemWave();
                wave.addMixedItemsDifferentDirections(items);
                List<AbstractItem> items = wave.getWave();
                for(AbstractItem ai : items)
                    QueueManager.instance().addToQueue(ai);
            }
        },0,1, items);
    }
}
