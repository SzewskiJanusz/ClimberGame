package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Anvil extends AbstractItem
{

    public Anvil(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    public void moveDown(float delta)
    {
        this.setY(this.getY() - velocity * delta);
    }

    protected void doMovement(float delta)
    {
        this.rotateBy(20 * delta);
    }

}
