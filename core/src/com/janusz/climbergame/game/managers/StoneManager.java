package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Stone;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-12-14.
 */

public class StoneManager extends BadManager<Stone>
{
    /**
     * Initialize List and timer
     */
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
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Stone a = new Stone
        (
            Const.STONE_TEXTURE,
            x,
            ClimberGame.HEIGHT,
            Const.STONE_WIDTH,
            Const.STONE_HEIGHT,
            Const.STONE_BASE_VELOCITY
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
