package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Carrot;
import com.janusz.climbergame.game.screens.GameScreen;

public class CarrotManager extends AbstractManager
{
    public CarrotManager()
    {
        super(Const.CARROT_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.CARROT_BOTTOM_RANGE,Const.CARROT_UPPER_RANGE);
    }

    @Override
    public Carrot build(int x)
    {
        return new Carrot
                (
                        x,
                        Const.CARROT_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
