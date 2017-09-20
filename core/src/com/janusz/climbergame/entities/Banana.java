package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Banana extends Image
{


    public Banana(Texture text, int starting_x)
    {
        super(text);
        this.setPosition(starting_x, ClimberGame.HEIGHT);
        setSize(100,75);
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void update(float delta)
    {
        moveDown(delta);
        doMovement(delta);
    }

    private void moveDown(float delta)
    {
        this.setY(this.getY() - 215 * delta);
    }

    private void doMovement(float delta)
    {
        this.rotateBy(360 * delta);
    }


}
