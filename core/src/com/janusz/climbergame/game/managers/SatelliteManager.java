package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Satellite;
import com.janusz.climbergame.game.screens.GameScreen;

public class SatelliteManager extends AbstractManager
{
    public SatelliteManager()
    {
        super(Const.SATELLITE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.SATELLITE_BOTTOM_RANGE,Const.SATELLITE_UPPER_RANGE);
    }

    @Override
    public Satellite build(int x)
    {
        return new Satellite
                (
                        x,
                        Const.SATELLITE_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
                );
    }
}
