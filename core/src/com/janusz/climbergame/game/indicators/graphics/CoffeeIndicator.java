package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.shared.DefComponents;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Janusz on 2018-01-13.
 */

public class CoffeeIndicator extends Image
{
    Label label;

    public CoffeeIndicator()
    {
        super(Const.COFFEEINDICATOR_TEXTURE);
        this.setSize(Const.COFFEEINDICATOR_WIDTH,Const.COFFEEINDICATOR_HEIGHT);
        this.setPosition(Const.COFFEEINDICATOR_X, Const.COFFEEINDICATOR_Y);

        label = new Label("", DefComponents.LABEL_STYLE);
        label.setFontScale(3.7f);
        label.setPosition(Const.COFFEEINDICATOR_X,
                Const.COFFEEINDICATOR_Y - Const.SPACE_BETWEEN_INDICATOR_AND_LABEL);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (Player.caffeinated)
        {
            double displayedTime = BigDecimal.valueOf
                    (
                            Const.COFFEE_EFFECT_TIME - Player.instance().coffeeTime)
                    .setScale(1, RoundingMode.HALF_UP
                    )
                    .doubleValue();
            label.setText(String.valueOf(displayedTime));
            if (Player.instance().coffeeTime >= Const.COFFEE_EFFECT_TIME)
            {
                this.remove();
                label.remove();
            }
        }
    }
}
