package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Pear;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class PearManager extends AbstractManager
{
    public PearManager()
    {
        super(Const.PEAR_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.PEAR_BOTTOM_RANGE,Const.PEAR_UPPER_RANGE);
    }

    @Override
    public Pear build(int x)
    {
        return new Pear
                (
                        x,
                        Const.PEAR_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
