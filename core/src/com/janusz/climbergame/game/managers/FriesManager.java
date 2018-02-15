package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Coffee;
import com.janusz.climbergame.game.entities.Fries;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class FriesManager extends AbstractManager
{
    private final Texture texture = new Texture("fries.png");

    public FriesManager()
    {
        super(Const.FRIES_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.FRIES_BOTTOM_RANGE ,Const.FRIES_UPPER_RANGE );
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Fries c = new Fries
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.FRIES_WIDTH,
                        Const.FRIES_HEIGHT,
                        Const.FRIES_BASE_VELOCITY + GameScreen.levelVelocity
                );
        c.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(c);
    }
}
