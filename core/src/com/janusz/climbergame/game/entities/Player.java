package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.game.controllers.EnergyBar;

/**
 * Player class.
 */

public class Player extends Image
{

    public static final int WIDTH = 65;
    public static final int HEIGHT = 90;

    public static final int STARTING_X = 380;
    public static final int STARTING_Y = 50;

    public static int place;

    // Rectangle for collision
    private Rectangle bounds;

    public Player()
    {
        super(new Texture("badlogic.jpg"));

        this.setOrigin(WIDTH / 2,HEIGHT / 2);
        this.setSize(WIDTH, HEIGHT);

        this.setPosition(STARTING_X ,STARTING_Y);
        bounds = new Rectangle(STARTING_X, STARTING_Y, WIDTH, HEIGHT);

        place = 2;
    }

    // Override for easier changing coordinates of actor and bounds
    @Override
    public void setX(float x)
    {
        this.setPosition(x, getY());
        getBounds().x = x;
    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        getBounds().y = y;
    }


    public void jumpLeft()
    {
        if (place > 0)
        {
            if (place == 1 && EnergyBar.actualEnergy <= 0)
                return;

            place--;
        }
    }

    public void jumpRight()
    {
        if (place < 3)
        {
            place++;
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
