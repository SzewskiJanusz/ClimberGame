package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-12-04.
 */

public class Coffee extends AbstractItem
{
    public Coffee(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(55 * delta);
    }

    @Override
    public void triggerEffect()
    {
        Player.instance().coffeeBoost();
        GameScreen.stage.addActor(new BouncingText("ENERGIZED", DefComponents.LABEL_STYLE,
                Effect.GOOD));
        IndicatorController.instance().addCoffeeIndicator();
    }
}
