package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-12-14.
 */

public abstract class AbstractIndicator extends Image
{
    // in seconds
    private int time;


    public AbstractIndicator(Texture text, int t)
    {
        super(text);
        this.setSize(75,100);
        time = t;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);

        if (time <= 0)
        {
            this.remove();
        }

        time -= delta;
    }
}
