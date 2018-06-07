package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Satellite;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-03-03.
 */

public class SatelliteManager extends AbstractManager
{
    private final Texture texture = new Texture("satellite.png");

    public SatelliteManager()
    {
        super(Const.SATELLITE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.SATELLITE_BOTTOM_RANGE,Const.SATELLITE_UPPER_RANGE);
    }

    @Override
    public void createEntityAndAddToQueue()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Satellite a = build(x);
        a.setName("bad");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(a);
    }

    @Override
    public Satellite build(int x)
    {
        return new Satellite
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.SATELLITE_WIDTH,
                        Const.SATELLITE_HEIGHT,
                        Const.SATELLITE_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
                );
    }
}
