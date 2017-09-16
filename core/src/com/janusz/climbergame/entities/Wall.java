package com.janusz.climbergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-09-16.
 */

public class Wall extends Image
{

    public static final int WIDTH = 100;
    public static final int HEIGHT = Gdx.graphics.getHeight();

    public static final int STARTING_X = 0;
    public static final int STARTING_Y = 0;

    public Wall()
    {
        super(new Texture("stonewall.png"));

        this.setOrigin(0, 0);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);

    }

}
