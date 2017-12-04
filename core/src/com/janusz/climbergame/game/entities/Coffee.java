package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Janusz on 2017-12-04.
 */

public class Coffee extends AbstractItem
{
    public Coffee(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(55 * delta);
    }
}
