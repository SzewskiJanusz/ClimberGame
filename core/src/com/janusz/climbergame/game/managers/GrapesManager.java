package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.entities.Grapes;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class GrapesManager extends AbstractManager
{
    private final Texture texture = new Texture("grapes.png");

    public GrapesManager()
    {
        super(Const.GRAPES_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.GRAPES_BOTTOM_RANGE,Const.GRAPES_UPPER_RANGE);
    }

    @Override
    public void createEntityAndAddToQueue()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Grapes b = build(x);
        b.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(b);
    }

    @Override
    public Grapes build(int x)
    {
        return new Grapes
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.GRAPES_WIDTH,
                        Const.GRAPES_HEIGHT,
                        Const.GRAPES_BASE_VELOCITY + GameScreen.levelVelocity
                );
    }
}
