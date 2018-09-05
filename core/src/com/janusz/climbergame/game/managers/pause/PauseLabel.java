package com.janusz.climbergame.game.managers.pause;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

class PauseLabel extends Label
{
    private int STARTING_X = 200;
    private int STARTING_Y = 200;


    public PauseLabel(CharSequence text, LabelStyle style)
    {
        super(text, style);
        this.setFontScale(0.9f);
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
    }
}
