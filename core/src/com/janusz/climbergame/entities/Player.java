package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-09-12.
 */

public class Player extends Image
{
    private static final int WIDTH = 200;
    private static final int HEIGHT = 300;

    private static final int STARTING_X = 100;
    private static final int STARTING_Y = 500;


    public Player()
    {
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH / 2, HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);

    }
}
