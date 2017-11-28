package com.janusz.climbergame.game.managers.score;

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

    private int points;

    public ScoreLabel(CharSequence text, LabelStyle style)
    {
        super(text, style);
        this.setFontScale(3,4);
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        this.setText(String.valueOf(points));
    }
}
