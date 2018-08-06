package com.janusz.climbergame.game.spawners;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.states.PlayGameState;

import java.util.ArrayList;
import java.util.List;


public class ItemWave
{
    private List<AbstractItem> items;
    private PlayGameState playGameState;


    public ItemWave(PlayGameState gs)
    {
        items = new ArrayList<AbstractItem>();
        playGameState = gs;
    }

    public void loadWave(int length)
    {
        ItemRandomizer ir = new ItemRandomizer(playGameState);
        for(int i = 0 ; i < length ; i++)
        {
            boolean good = MathUtils.random(0, 1) == 1;
            if (good)
            {
                items.add(ir.getRandomGoodItem());
            }
            else
            {
                items.add(ir.getRandomBadItem());
            }
        }
    }

    public List<AbstractItem> getWave()
    {
        return items;
    }
}
