package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Fries;
import com.janusz.climbergame.game.screens.GameScreen;

public class FriesManager extends AbstractManager
{
    public FriesManager()
    {
        super(Const.FRIES_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.FRIES_BOTTOM_RANGE ,Const.FRIES_UPPER_RANGE );
    }

    @Override
    public Fries build(int x)
    {
        return new Fries
                (
                        x,
                        Const.FRIES_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
