package com.janusz.climbergame.game.bosses;

import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.managers.ItemRandomizer;

import java.util.ArrayList;
import java.util.List;


public class ItemsWave
{
    private List<AbstractItem> items;
    private ItemRandomizer ir = new ItemRandomizer();

    public ItemsWave(int length, boolean good)
    {
        items = new ArrayList<AbstractItem>();
        if (good)
        {
            for (int i = 0; i < length; i++)
            {
                items.add(ir.randomGoodItem());
            }
        }
        else
        {
            for (int i = 0; i < length; i++)
            {
                items.add(ir.randomBadItem());
            }
        }
    }

    public List<AbstractItem> getWave()
    {
        return items;
    }
}
