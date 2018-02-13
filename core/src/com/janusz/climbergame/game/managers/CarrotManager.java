package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Carrot;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class CarrotManager extends AbstractManager
{
    private final Texture texture = new Texture("carrot.png");
    /**
     * Initialize List and timer
     */
    public CarrotManager()
    {
        super(Const.CARROT_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.CARROT_BOTTOM_RANGE,Const.CARROT_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Carrot b = new Carrot
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.CARROT_WIDTH,
                        Const.CARROT_HEIGHT,
                        Const.CARROT_BASE_VELOCITY + GameScreen.levelVelocity
                );
        b.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(b);
    }
}
