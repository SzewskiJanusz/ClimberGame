package com.janusz.climbergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-09-12.
 */

public class LianaTile extends Image
{
    public static final int WIDTH = 100;
    public static final int HEIGHT = 300;

    public static int STARTING_X = 200;
    public static final int STARTING_Y = Gdx.graphics.getHeight();


    public LianaTile(int starting_x)
    {
        super(new Texture("liana.png"));

        this.STARTING_X = starting_x;

        this.setOrigin(WIDTH, HEIGHT);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void moveLianaTile(float delta)
    {
        this.setY(this.getY() - delta * 250);
    }

}


