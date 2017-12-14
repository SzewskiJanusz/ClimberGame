package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Bartek on 2017-10-20.
 *
 * Banan
 */
public class Banana extends AbstractItem
{

    public Banana(Texture text,int starting_x, int starting_y, int width, int height,int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    protected void doMovement(float delta)
    {
        this.rotateBy(360 * delta); // obracanie z każdą klatką
    }
}
