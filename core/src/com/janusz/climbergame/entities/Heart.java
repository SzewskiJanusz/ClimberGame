package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-21.
 */

public class Heart extends Image
{

    public final int x = 400;
    public final int y = 400;

    public int width;
    public int height;


    public Heart(Texture text)
    {
        super(text);
        width = 100;
        height = 75;
        this.setPosition(x, y);
        setSize(width,height);
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

}
