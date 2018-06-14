package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Tequila;
import com.janusz.climbergame.game.screens.GameScreen;

public class TequilaManager extends AbstractManager
{
    public TequilaManager()
    {
        super(Const.TEQUILA_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TEQUILA_BOTTOM_RANGE ,Const.TEQUILA_UPPER_RANGE );
    }
    @Override
    public Tequila build(int x)
    {
        return new Tequila
                (
                        x,
                        Const.TEQUILA_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
