package com.janusz.climbergame.menu.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonFactory
{
    private final static int width = 350;
    private final static int height = 65;
    private final static int startingX = 175;

    public static TextButton createButton(String title, TextButton.TextButtonStyle tbs, int startingY)
    {
        TextButton button = new TextButton(title, tbs);
        button.setPosition(startingX, startingY);
        button.setSize(width, height);
        button.setText(title);
        button.getLabel().setFontScale(0.6f);
        button.setY(startingY);
        return button;
    }
}
