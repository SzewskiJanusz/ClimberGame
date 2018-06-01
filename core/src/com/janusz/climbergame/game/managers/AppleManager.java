package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Apple;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-07.
 */

public class AppleManager extends AbstractManager
{
    private final Texture texture = new Texture("apple.png");

    public AppleManager()
    {
        super(Const.APPLE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.APPLE_BOTTOM_RANGE,Const.APPLE_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Apple a = new Apple
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.APPLE_WIDTH,
                        Const.APPLE_HEIGHT,
                        Const.APPLE_BASE_VELOCITY + + GameScreen.levelVelocity
                );
        a.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(a);
    }


}
