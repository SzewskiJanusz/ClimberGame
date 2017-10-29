package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-12.
 */

public class LianaTile extends Image
{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 120;

    public static int STARTING_X;
    public static int STARTING_Y = ClimberGame.HEIGHT;


    public LianaTile(int starting_x)
    {
        super(new Texture("liana.png"));

        this.STARTING_X = starting_x;

        this.setOrigin(WIDTH, HEIGHT);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public LianaTile(int starting_x,int starting_y)
    {
        super(new Texture("liana.png"));

        this.STARTING_X = starting_x;
        this.STARTING_Y = starting_y;

        this.setOrigin(WIDTH, HEIGHT);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        this.setPosition(STARTING_X, STARTING_Y);
    }

    public void moveLianaTile(float delta)
    {
        this.setY(this.getY() - delta * 250);
    }

    public void stickLianaToAnother(LianaTile destination)
    {
        this.setY(destination.getY() + HEIGHT);
    }

}


