package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Watermelon;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-12.
 */

public class WatermelonManager extends AbstractManager
{

    private final Texture texture = new Texture("watermelon.png");

    public WatermelonManager()
    {
        super(Const.WATERMELON_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.WATERMELON_BOTTOM_RANGE,Const.WATERMELON_UPPER_RANGE);
    }

    @Override
    public void createEntityAndAddToQueue()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Watermelon w = build(x);
        w.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(w);
    }

    @Override
    public Watermelon build(int x)
    {
        return new Watermelon
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.WATERMELON_WIDTH,
                        Const.WATERMELON_HEIGHT,
                        Const.WATERMELON_BASE_VELOCITY + + GameScreen.levelVelocity
                );
    }
}
