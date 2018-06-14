package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.screens.GameScreen;


public class BananaManager extends AbstractManager
{
    public BananaManager()
    {
        super(Const.BANANA_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.BANANA_BOTTOM_RANGE,Const.BANANA_UPPER_RANGE);
    }

    @Override
    public Banana build(int x)
    {
        return new Banana
                (
                        x,
                        Const.BANANA_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
