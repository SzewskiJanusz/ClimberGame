package com.janusz.climbergame.shared.factories;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.janusz.climbergame.shared.DefComponents;

public class ButtonFactory
{
    private final static int width = 350;
    private final static int height = 65;
    private final static int startingX = 175;

    public static TextButton createButton(String title, int startingY)
    {
        TextButton button = new TextButton(title, DefComponents.TEXTBUTTON_STYLE);
        button.setPosition(startingX, startingY);
        button.setSize(width, height);
        button.setText(title);
        button.getLabel().setFontScale(0.6f);
        button.setY(startingY);
        return button;
    }

    public static TextButton createButton(String title)
    {
        TextButton button = new TextButton(title, DefComponents.TEXTBUTTON_STYLE);
        button.getLabel().setFontScale(0.4f);
        return button;
    }


}