package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Stone;
import com.janusz.climbergame.game.screens.GameScreen;

public class StoneManager extends AbstractManager
{

    public StoneManager()
    {
        super(Const.STONE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.STONE_BOTTOM_RANGE,Const.STONE_UPPER_RANGE);
    }

    @Override
    public Stone build(int x)
    {
        return new Stone
                (
                        x,
                        Const.STONE_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
                );
    }
}
