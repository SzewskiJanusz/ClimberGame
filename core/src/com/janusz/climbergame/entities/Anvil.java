package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Anvil extends Image
{

    private Rectangle bounds;

    private final int width = 175;
    private final int height = 125;

    private final int velocity = 500;

    public Anvil(Texture text, int starting_x)
    {
        super(text);
        this.setPosition(starting_x, ClimberGame.HEIGHT);
        setSize(width,height);
        setOrigin(getWidth() / 2, getHeight() / 2);

        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT, width, height);
    }

    public void update(float delta)
    {
        checkIfNeedDispose();
        moveDown(delta);
        doMovement(delta);
    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        bounds.y = y;
    }

    private void moveDown(float delta)
    {
        this.setY(this.getY() - velocity * delta);
    }

    private void doMovement(float delta)
    {
        this.rotateBy(20 * delta);
    }


    private void checkIfNeedDispose()
    {
        if (getY() < 0)
        {
            addAction(Actions.removeActor());
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
