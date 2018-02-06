package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;


public class AnvilManager extends BadManager<Anvil>
{
    private final Texture texture = new Texture("anvil.png");
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
            texture,
            x,
            ClimberGame.HEIGHT,
            Const.ANVIL_WIDTH,
            Const.ANVIL_HEIGHT,
            Const.ANVIL_BASE_VELOCITY + (int)(GameScreen.difficultyTimer * Const.BAD_TIMER_RATIO)
        );
        entities.add(a);
        GameScreen.stage.addActor(a);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        GameScreen.deathAnimation = true;
    }
}
