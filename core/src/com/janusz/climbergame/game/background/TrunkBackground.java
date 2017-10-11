package com.janusz.climbergame.game.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-10-11.
 */

public class TrunkBackground extends Image
{

    private float timer;

    public TrunkBackground()
    {
        super(new Texture("trunk.png"));
        this.setPosition(200, 0);
        setSize(450, ClimberGame.HEIGHT);
    }

    public void moveDown(float delta)
    {
        if (timer >= 1)
        {
            this.setY(getY() - 1);
            timer = 0;
        }

        timer += delta;
    }

}
