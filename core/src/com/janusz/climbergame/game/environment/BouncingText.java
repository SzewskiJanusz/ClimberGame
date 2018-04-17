package com.janusz.climbergame.game.environment;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.game.entities.player.Player;

/**
 * Created by Janusz on 2017-11-30.
 */

public class BouncingText extends Label
{
    // Time for when text is visible (sec)
    private final float timeOfVisibility = 0.7f;
    private float timeElapsed = 0;

    public BouncingText(CharSequence text, LabelStyle style, Effect effect)
    {
        super(text, style);

        setFontScale(0.4f);
        this.setX(Player.instance().getX());
        this.setY(Player.instance().getY());

        switch(effect)
        {
            case GOOD:
                this.setColor(Color.GREEN);
                break;
            case DEFAULT:
                this.setColor(Color.WHITE);
                break;
            case BAD:
                this.setColor(Color.RED);
                break;
            default:
                break;
        }
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        timeElapsed += delta;
        if (timeOfVisibility < timeElapsed)
        {
            this.remove();
            timeElapsed = 0;
        }
        else
        {
            moveBy(0,5);
        }
    }
}
