package com.janusz.climbergame.game.managers.queue;

import com.janusz.climbergame.game.entities.AbstractItem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Janusz on 2018-02-09.
 */

public class QueueManager
{
    private Queue<AbstractItem> spawnQueue;
    private static QueueManager ins;

    /* Singleton */
    public static QueueManager instance()
    {
        if (ins == null)
            ins = new QueueManager();

        return ins;
    }

    private QueueManager()
    {
        spawnQueue = new LinkedList<AbstractItem>();
    }
    /* */

    public AbstractItem getFirst()
    {
        return spawnQueue.poll();
    }

    public void addToQueue(AbstractItem a)
    {
        spawnQueue.add(a);
    }

    public void reset()
    {
        spawnQueue.clear();
    }
}
