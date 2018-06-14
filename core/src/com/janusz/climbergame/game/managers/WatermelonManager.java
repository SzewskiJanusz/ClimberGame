package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Watermelon;
import com.janusz.climbergame.game.screens.GameScreen;


public class WatermelonManager extends AbstractManager
{
    public WatermelonManager()
    {
        super(Const.WATERMELON_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.WATERMELON_BOTTOM_RANGE,Const.WATERMELON_UPPER_RANGE);
    }

    @Override
    public Watermelon build(int x)
    {
        return new Watermelon
                (
                        x,
                        Const.WATERMELON_BASE_VELOCITY + + GameScreen.levelVelocity
                );
    }
}
