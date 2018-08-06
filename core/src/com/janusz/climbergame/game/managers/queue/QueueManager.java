package com.janusz.climbergame.game.managers.queue;

import com.janusz.climbergame.game.entities.AbstractItem;
import java.util.LinkedList;
import java.util.Queue;

public class QueueManager
{
    private Queue<AbstractItem> spawnQueue;

    public QueueManager()
    {
        spawnQueue = new LinkedList<AbstractItem>();
    }

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
