package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Stone;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-12-14.
 */

public class StoneManager extends AbstractManager<Stone>
{
    /**
     * Initialize List and timer
     */
    public StoneManager()
    {
        super(3);
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
        Stone a = new Stone(new Texture("stone.png"), x, ClimberGame.HEIGHT, 55, 80, 300);
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
