package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Coffee;
import com.janusz.climbergame.game.screens.GameScreen;

public class CoffeeManager extends AbstractManager
{
    public CoffeeManager()
    {
        super(Const.COFFEE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.COFFEE_BOTTOM_RANGE ,Const.COFFEE_UPPER_RANGE );
    }

    @Override
    public Coffee build(int x)
    {
        return new Coffee
                (
                        x,
                        Const.COFFEE_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
