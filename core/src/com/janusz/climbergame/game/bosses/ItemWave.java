package com.janusz.climbergame.game.bosses;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.managers.ItemRandomizer;

import java.util.ArrayList;
import java.util.List;


public class ItemWave
{
    private List<AbstractItem> items;


    public ItemWave()
    {
        items = new ArrayList<AbstractItem>();
    }

    public ItemWave(int length, boolean good)
    {
        items = new ArrayList<AbstractItem>();
        addSelectedItems(length, good);
    }

    public ItemWave(int length, boolean good, int direction)
    {
        items = new ArrayList<AbstractItem>();
        addSelectedItemsWithDirection(length, good, direction);
    }

    private void addSelectedItemsWithDirection(int length, boolean good, int direction)
    {
        ItemRandomizer ir = new ItemRandomizer(direction);
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

    private void addSelectedItems(int length, boolean good)
    {
        ItemRandomizer ir = new ItemRandomizer();
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

    public void addSelectedItemsDifferentDirections(int length, boolean good)
    {
        ItemRandomizer ir = new ItemRandomizer(true);
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

    public void addMixedItemsDifferentDirections(int length)
    {
        ItemRandomizer ir = new ItemRandomizer(true);

        for(int i = 0 ; i < length ; i++)
        {
            boolean good = MathUtils.random(0, 1) == 1;
            if (good)
            {
                items.add(ir.randomGoodItem());
            }
            else
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
