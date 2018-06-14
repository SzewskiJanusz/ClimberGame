package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;


public class AnvilManager extends AbstractManager
{
    public AnvilManager()
    {
        super(Const.ANVIL_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.ANVIL_BOTTOM_RANGE,Const.ANVIL_UPPER_RANGE);
    }

    @Override
    public Anvil build(int x)
    {
        return new Anvil(x, Const.ANVIL_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5));
    }
}
