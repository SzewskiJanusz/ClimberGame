package com.janusz.climbergame.game.score;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-11-28.
 */

public class ScoreLabel extends Label
{

    private final int STARTING_X = 450;
    private final int STARTING_Y = ClimberGame.HEIGHT - 65;

    public ScoreLabel(CharSequence text, LabelStyle style)
    {
        super(text, style);
        this.setFontScale(1);
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
    }
}
