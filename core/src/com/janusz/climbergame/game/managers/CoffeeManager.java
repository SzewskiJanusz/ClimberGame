package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Coffee;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefLabel;

/**
 * Created by Janusz on 2017-12-04.
 */

public class CoffeeManager extends AbstractManager<Coffee>
{
    public CoffeeManager()
    {
        super(8);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(7,14);
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(1,4));
        Coffee c = new Coffee(new Texture("coffee.png"), x, ClimberGame.HEIGHT, 30, 75, 250);
        entities.add(c);
        GameScreen.stage.addActor(c);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        Player.instance().coffeeBoost();
        GameScreen.stage.addActor(new BouncingText("ENERGIZED", DefLabel.getDefaultLabelStyle(),
                Effect.DEFAULT));
    }
}
