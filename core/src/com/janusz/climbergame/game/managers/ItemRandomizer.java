package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.entities.AbstractItem;

public class ItemRandomizer
{
    private int direction;

    public ItemRandomizer()
    {
        direction = MathUtils.random(1,3);
    }

    public ItemRandomizer(int dir)
    {
        direction = dir;
    }

    public AbstractItem randomGoodItem()
    {
        int nmb = MathUtils.random(1,6);
        switch(nmb)
        {
            case 1: return new BananaManager().build(direction);
            case 2: return new AppleManager().build(direction);
            case 3: return new CarrotManager().build(direction);
            case 4: return new GrapesManager().build(direction);
            case 5: return new PearManager().build(direction);
            case 6: return new CoffeeManager().build(direction);
        }
        return null;
    }

    public AbstractItem randomBadItem()
    {
        int nmb = MathUtils.random(1,3);
        switch(nmb)
        {
            case 1: return new AnvilManager().build(direction);
            case 2: return new StoneManager().build(direction);
            case 3: return new TrashcanManager().build(direction);
        }
        return null;
    }
}
