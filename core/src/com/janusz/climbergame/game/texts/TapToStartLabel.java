package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2018-01-15.
 */

public class TapToStartLabel extends Label
{
    private final int STARTING_X = ClimberGame.WIDTH/2;
    private final int STARTING_Y = ClimberGame.HEIGHT/2;

    public TapToStartLabel(CharSequence text, Label.LabelStyle style)
    {
        super(text, style);
        this.setFontScale(3,4);
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
    }




    @Override
    public void act(float delta)
    {
        super.act(delta);
    }
}
