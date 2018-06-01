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
 * Created by Janusz on 2017-12-14.
 *
 * Indicator of drop when drunk
 */
public class DrunkIndicator extends Image
{
    Label label;
    
    public DrunkIndicator()
    {
        super(new Texture("drop.png"));
        this.setSize(Const.DRUNKINDICATOR_WIDTH,Const.DRUNKINDICATOR_HEIGHT);
        this.setPosition(Const.DRUNKINDICATOR_X, Const.DRUNKINDICATOR_Y);

        label = new Label("", DefComponents.LABEL_STYLE);
        label.setFontScale(0.74f);
        label.setPosition(Const.DRUNKINDICATOR_X,
                Const.DRUNKINDICATOR_Y - Const.SPACE_BETWEEN_INDICATOR_AND_LABEL);
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (Player.drunk)
        {
            double displayedTime = BigDecimal.valueOf
                    (
                            Const.TEQUILA_EFFECT_TIME - Player.instance().drunkTime)
                    .setScale(1, RoundingMode.HALF_UP
                    )
                    .doubleValue();
            label.setText(String.valueOf(displayedTime));
            if (Player.instance().drunkTime >= Const.TEQUILA_EFFECT_TIME)
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
