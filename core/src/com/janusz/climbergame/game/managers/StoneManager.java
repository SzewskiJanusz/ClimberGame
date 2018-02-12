package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Stone;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-12-14.
 */

public class StoneManager extends AbstractManager
{
    private final Texture texture = new Texture("stone.png");
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
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Stone a = new Stone
        (
            texture,
            x,
            ClimberGame.HEIGHT,
            Const.STONE_WIDTH,
            Const.STONE_HEIGHT,
            Const.STONE_BASE_VELOCITY + (int)(GameScreen.levelVelocity*1.5)
        );
        a.setName("bad");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(a);
    }
}
