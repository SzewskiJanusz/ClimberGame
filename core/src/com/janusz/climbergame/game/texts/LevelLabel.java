package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Janusz on 2018-03-03.
 */

public class LevelLabel extends Label
{
    public LevelLabel(CharSequence text, LabelStyle style)
    {
        super(text, style);
        initLabel();
    }

    private void initLabel()
    {
        this.setColor(new Color(130,143,3,255));
        this.setPosition(70, 290);
        this.setFontScale(0.7f);//3.5f
    }
}
