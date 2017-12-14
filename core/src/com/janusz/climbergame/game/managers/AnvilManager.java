package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;


public class AnvilManager extends AbstractManager<Anvil>
{

    public AnvilManager()
    {
        super(5);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(1,7);
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Anvil a = new Anvil(new Texture("anvil.png"), x, ClimberGame.HEIGHT, 80, 55, 300);
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
