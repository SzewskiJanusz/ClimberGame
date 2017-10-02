package com.janusz.climbergame.menu.buttons;


import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Janusz on 2017-10-02.
 */

public abstract class AbstractTextButton extends TextButton
{

    protected int width;
    protected int height;
    protected int startingX;

    public AbstractTextButton(String title, TextButtonStyle tbs, int startingY)
    {
        super(title, tbs);
        init();
        setPosition(startingX, startingY);
        setSize(width, height);
        setText(title);
        getLabel().setFontScale(5);
    }

    protected void init()
    {
        width = 900;
        height = 150;
        startingX = 600;
    }

}
