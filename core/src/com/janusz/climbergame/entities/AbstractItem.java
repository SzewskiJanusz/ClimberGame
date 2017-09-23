package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-22.
 */

public abstract class AbstractItem extends Image
{

    protected Rectangle bounds;
    protected int velocity;

    protected abstract void moveDown(float delta);
    protected abstract void doMovement(float delta);

    public AbstractItem(Texture text,int starting_x, int starting_y, int width, int height,int velocity)
    {
        super(text);
        this.velocity = velocity;
        this.setPosition(starting_x, starting_y);
        setSize(width,height);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, starting_y, width, height);

    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        bounds.y = y;
    }

    public void update(float delta)
    {
        checkIfNeedDispose();
        moveDown(delta);
        doMovement(delta);

    }

    protected void checkIfNeedDispose()
    {
        if (getY() < 0)
        {
            this.remove();
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

}
