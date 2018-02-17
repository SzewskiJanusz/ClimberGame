package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.entities.Treasure;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-02-13.
 */

public class TreasureManager extends AbstractManager
{
    private final Texture texture = new Texture("treasure.png");

    public TreasureManager()
    {
        super(Const.TREASURE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TREASURE_BOTTOM_RANGE,Const.TREASURE_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Treasure b = new Treasure
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.TREASURE_WIDTH,
                        Const.TREASURE_HEIGHT,
                        Const.TREASURE_BASE_VELOCITY + GameScreen.levelVelocity
                );
        b.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(b);
    }
}
