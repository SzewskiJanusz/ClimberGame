package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.entities.Pear;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class PearManager extends AbstractManager
{
    private final Texture texture = new Texture("pear.png");

    public PearManager()
    {
        super(Const.PEAR_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.PEAR_BOTTOM_RANGE,Const.PEAR_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Pear b = new Pear
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.PEAR_WIDTH,
                        Const.PEAR_HEIGHT,
                        Const.PEAR_BASE_VELOCITY + GameScreen.levelVelocity
                );
        b.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(b);
    }
}
