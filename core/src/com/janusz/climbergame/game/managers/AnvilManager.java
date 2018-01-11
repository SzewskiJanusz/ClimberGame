package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;


public class AnvilManager extends AbstractManager<Anvil>
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
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Anvil a = new Anvil
        (
            Const.ANVIL_TEXTURE,
            x,
            ClimberGame.HEIGHT,
            Const.ANVIL_WIDTH,
            Const.ANVIL_HEIGHT,
            Const.ANVIL_BASE_VELOCITY
        );
        entities.add(a);
        GameScreen.stage.addActor(a);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        GameScreen.gameOver = true;
    }
}
