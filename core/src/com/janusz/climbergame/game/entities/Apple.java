package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Janusz on 2018-02-07.
 */

public class Apple extends AbstractItem
{

    public Apple(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(160 * delta); // obracanie z każdą klatką
    }
}
