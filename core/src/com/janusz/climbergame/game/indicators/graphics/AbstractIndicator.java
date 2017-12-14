package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-12-14.
 */

public abstract class AbstractIndicator extends Image
{
    public AbstractIndicator(Texture text)
    {
        super(text);
        this.setSize(75,100);
    }

}
