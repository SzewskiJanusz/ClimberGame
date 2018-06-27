package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.environment.EntireLiana;

public class ItemRandomizer
{
    private int direction;
    private boolean noDirection;

    public ItemRandomizer()
    {
        direction = MathUtils.random(2,4);
    }

    public ItemRandomizer(boolean noDirection)
    {
        this.noDirection = noDirection;
    }

    public ItemRandomizer(int dir)
    {
        direction = dir;
    }

    public AbstractItem randomGoodItem()
    {
        int nmb = MathUtils.random(1,6);
        if (noDirection)
            direction = MathUtils.random(2,4);

        int x = selectPlace(direction);
        switch(nmb)
        {
            case 1: return new BananaManager().build(x);
            case 2: return new AppleManager().build(x);
            case 3: return new CarrotManager().build(x);
            case 4: return new GrapesManager().build(x);
            case 5: return new PearManager().build(x);
            case 6: return new CoffeeManager().build(x);
        }
        return null;
    }

    public AbstractItem randomBadItem()
    {
        int nmb = MathUtils.random(1,3);

        if (noDirection)
            direction = MathUtils.random(2,4);

        int x = selectPlace(direction);
        switch(nmb)
        {
            case 1: return new AnvilManager().build(x);
            case 2: return new StoneManager().build(x);
            case 3: return new TrashcanManager().build(x);
        }
        return null;
    }

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
}
