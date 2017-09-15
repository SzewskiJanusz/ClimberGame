package com.janusz.climbergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-09-12.
 */

public class Liana extends Image
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 300;

    private static final int STARTING_X = 200;
    private static final int STARTING_Y = Gdx.graphics.getHeight();


    public Liana()
    {

        super(new Texture("liana.png"));
        // bottom-left corner for easy check if need to create another one
        this.setOrigin(WIDTH, HEIGHT);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);

    }

    public void moveLianaDown(float delta)
    {
        this.setY(this.getY() - delta * 250);
    }
}


