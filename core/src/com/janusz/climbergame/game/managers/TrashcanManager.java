package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.entities.Trashcan;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2018-03-03.
 */

public class TrashcanManager extends AbstractManager
{
    private final Texture texture = new Texture("trashcan.png");

    public TrashcanManager()
    {
        super(Const.TRASHCAN_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TRASHCAN_BOTTOM_RANGE,Const.TRASHCAN_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Trashcan a = new Trashcan
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.TRASHCAN_WIDTH,
                        Const.TRASHCAN_HEIGHT,
                        Const.TRASHCAN_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
                );
        a.setName("bad");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(a);
    }
}
