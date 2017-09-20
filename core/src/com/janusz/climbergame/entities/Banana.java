package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Banana extends Image
{


    public Banana(Texture text, int starting_x)
    {
        super(text);
        this.setPosition(starting_x, 300);
        setSize(100,75);
    }
}
