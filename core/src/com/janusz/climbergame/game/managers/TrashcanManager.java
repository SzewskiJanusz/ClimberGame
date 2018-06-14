package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Trashcan;
import com.janusz.climbergame.game.screens.GameScreen;

public class TrashcanManager extends AbstractManager
{
    public TrashcanManager()
    {
        super(Const.TRASHCAN_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TRASHCAN_BOTTOM_RANGE,Const.TRASHCAN_UPPER_RANGE);
    }

    @Override
    public Trashcan build(int x)
    {
        return new Trashcan
                (
                        x,
                        Const.TRASHCAN_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
                );
    }
}
