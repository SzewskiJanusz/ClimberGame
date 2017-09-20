package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Banana extends Image
{

    private Rectangle bounds;

    private final int width = 100;
    private final int height = 75;

    private final int velocity = 215;

    public Banana(Texture text, int starting_x)
    {
        super(text);
        this.setPosition(starting_x, ClimberGame.HEIGHT);
        setSize(width,height);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT, width, height);
    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        bounds.y = y;
    }

    public void update(float delta)
    {
        moveDown(delta);
        doMovement(delta);
    }

    private void moveDown(float delta)
    {
        this.setY(this.getY() - velocity * delta);
    }

    private void doMovement(float delta)
    {
        this.rotateBy(360 * delta);
    }


    public Rectangle getBounds()
    {
        return bounds;
    }
}
