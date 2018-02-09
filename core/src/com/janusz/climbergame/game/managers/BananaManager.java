package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-11-04
 */
public class BananaManager extends AbstractManager
{
    private final Texture texture = new Texture("banana.png");

    public BananaManager()
    {
        super(Const.BANANA_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.BANANA_BOTTOM_RANGE,Const.BANANA_UPPER_RANGE);
    }

    @Override
    protected void createEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Banana b = new Banana
        (
            texture,
            x,
            ClimberGame.HEIGHT,
            Const.BANANA_WIDTH,
            Const.BANANA_HEIGHT,
            Const.BANANA_BASE_VELOCITY + (int)(GameScreen.difficultyTimer * Const.GOOD_TIMER_RATIO)
        );
        b.setName("good");
        randomizeSpawnTime();
        QueueManager.instance().addToQueue(b);
    }


}
