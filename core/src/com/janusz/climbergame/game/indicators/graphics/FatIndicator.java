package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.shared.DefComponents;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Janusz on 2018-02-15.
 */

public class FatIndicator extends Image
{
    Label label;

    public FatIndicator()
    {
        super(new Texture("fatindicator.png"));
        this.setSize(Const.FATINDICATOR_WIDTH,Const.FATINDICATOR_HEIGHT);
        this.setPosition(Const.COFFEEINDICATOR_X, Const.COFFEEINDICATOR_Y);

        label = new Label("", DefComponents.LABEL_STYLE);
        label.setFontScale(0.74f);
        label.setPosition(Const.COFFEEINDICATOR_X,
                Const.COFFEEINDICATOR_Y - Const.SPACE_BETWEEN_INDICATOR_AND_LABEL);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (Player.fat)
        {
            double displayedTime = BigDecimal.valueOf
                    (
                            Const.FAT_EFFECT_TIME - Player.instance().fatTime)
                    .setScale(1, RoundingMode.HALF_UP
                    )
                    .doubleValue();
            label.setText(String.valueOf(displayedTime));
            if (Player.instance().fatTime >= Const.FAT_EFFECT_TIME)
            {
                this.remove();
                label.remove();
            }
        }
    }

    public void dispose()
    {
        this.remove();
        label.remove();
    }
}

