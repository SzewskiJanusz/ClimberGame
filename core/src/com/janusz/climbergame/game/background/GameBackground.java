package com.janusz.climbergame.game.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-10-11.
 */

public class GameBackground extends Image
{

    public GameBackground()
    {
        super(new Texture("gameBackground.png"));
        this.setPosition(0, 0);
        setSize(ClimberGame.WIDTH, ClimberGame.HEIGHT);
    }

}
