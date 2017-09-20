package com.janusz.climbergame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Anvil extends Image
{

    public Anvil(Texture text, int starting_x)
    {
        super(text);
        this.setPosition(starting_x, ClimberGame.HEIGHT);
        setSize(175,125);
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void update(float delta)
    {
        checkIfNeedDispose();
        moveDown(delta);
        doMovement(delta);
    }

    private void moveDown(float delta)
    {
        this.setY(this.getY() - 500 * delta);
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
}
