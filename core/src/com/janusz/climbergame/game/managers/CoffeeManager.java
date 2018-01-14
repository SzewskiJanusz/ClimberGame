package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Coffee;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-12-04.
 */

public class CoffeeManager extends GoodManager<Coffee>
{
    public CoffeeManager()
    {
        super(Const.COFFEE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.COFFEE_BOTTOM_RANGE ,Const.COFFEE_UPPER_RANGE );
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Coffee c = new Coffee
        (
                Const.COFFEE_TEXTURE,
                x,
                ClimberGame.HEIGHT,
                Const.COFFEE_WIDTH,
                Const.COFFEE_HEIGHT,
                Const.COFFEE_BASE_VELOCITY
        );
        entities.add(c);
        GameScreen.stage.addActor(c);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        Player.instance().coffeeBoost();
        GameScreen.stage.addActor(new BouncingText("ENERGIZED", DefComponents.getDefaultLabelStyle(),
                Effect.DEFAULT));
        IndicatorController.instance().addCoffeeIndicator();
    }
}
