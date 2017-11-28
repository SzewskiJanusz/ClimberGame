package com.janusz.climbergame.game.score;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Janusz on 2017-11-28.
 */

public class ScoreManager
{
    public ScoreLabel ScoreLabel;
    public ScoreLogic ScoreLogic;

    public ScoreManager()
    {
        ScoreLogic = new ScoreLogic();
        ScoreLabel = new ScoreLabel(String.valueOf(ScoreLogic.getScore()), getDefaultLabelStyle());
    }

    private Label.LabelStyle getDefaultLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;
        return ls;
    }
}
