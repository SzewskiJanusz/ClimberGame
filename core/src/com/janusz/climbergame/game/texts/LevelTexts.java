package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Janusz on 2018-03-03.
 */

public class LevelTexts
{
    public Label levelNumber;
    public Label levelText;
    private Label.LabelStyle style;

    public LevelTexts(CharSequence text, Label.LabelStyle style)
    {
        this.style = style;
        initNumberLabel(text);
        initTextLabel();
    }

    private void initNumberLabel(CharSequence text)
    {
        levelNumber = new Label(text, style);
        levelNumber.setColor(new Color(130,143,3,255));
        levelNumber.setPosition(70, 230);
        levelNumber.setFontScale(0.5f);//3.5f
    }

    private void initTextLabel()
    {
        levelText = new Label("LEVEL", style);
        levelText.setColor(new Color(130,143,3,255));
        levelText.setPosition(20, 290);
        levelText.setFontScale(0.5f);//3.5f
    }

    public void remove()
    {
        levelNumber.remove();
        levelText.remove();
    }
}
