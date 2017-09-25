package com.janusz.climbergame.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-25.
 */

public class EnergyBar extends Image
{
    public EnergyBar()
    {
        super(new Texture("energy_frame.jpg"));

        this.setSize(ClimberGame.WIDTH / 2, 65);
        this.setOrigin(this.getWidth() / 2 , this.getHeight() / 2);
        this.setPosition(ClimberGame.WIDTH / 3 , ClimberGame.HEIGHT - 10 - this.getHeight());
    }

}
