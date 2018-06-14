package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Treasure;
import com.janusz.climbergame.game.screens.GameScreen;

public class TreasureManager extends AbstractManager
{
    public TreasureManager()
    {
        super(Const.TREASURE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TREASURE_BOTTOM_RANGE,Const.TREASURE_UPPER_RANGE);
    }

    @Override
    public Treasure build(int x)
    {
        return new Treasure
                (
                        x,
                        Const.TREASURE_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
