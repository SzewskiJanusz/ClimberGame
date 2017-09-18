package com.janusz.climbergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-09-18.
 */

public class Player extends Image
{

    public static final int WIDTH = 130;
    public static final int HEIGHT = 210;

    public static final int STARTING_X = 950;
    public static final int STARTING_Y = 100;

    public Player()
    {
        super(new Texture("badlogic.jpg"));

        this.setOrigin(0,0);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(STARTING_X ,STARTING_Y);

    }





}
