package com.janusz.climbergame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-16.
 */

public class Wall extends Image
{

    public static final int WIDTH = 300;
    public static final int HEIGHT = ClimberGame.HEIGHT;

    public static final int STARTING_X = 0;
    public static final int STARTING_Y = 0;

    public Wall(boolean spawnOnEntireScreen)
    {
        super(new Texture("stonewall.png"));

        this.setOrigin(0, 0);
        this.setSize(WIDTH, HEIGHT);

        // starting position
        if (spawnOnEntireScreen)
        {
            this.setPosition(STARTING_X, STARTING_Y + 100);
        }
        else
        {
            this.setPosition(STARTING_X, STARTING_Y + HEIGHT);
        }

    }

    public void moveWall(float delta)
    {
        this.setY(this.getY() - delta * 250);
    }

    public void stickToWall(Wall destination)
    {
        this.setY(destination.getY() + HEIGHT);
    }

}
