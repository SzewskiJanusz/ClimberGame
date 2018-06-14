package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Apple;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-07.
 */

public class AppleManager extends AbstractManager
{
    public AppleManager()
    {
        super(Const.APPLE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.APPLE_BOTTOM_RANGE,Const.APPLE_UPPER_RANGE);
    }

    @Override
    public Apple build(int x)
    {
        return new Apple
                (
                        x,
                        Const.APPLE_BASE_VELOCITY + + GameScreen.levelVelocity
                );
    }
}
