package com.janusz.climbergame.game.pause;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Janusz on 2018-01-21.
 */

public class PauseButton extends ImageButton
{

    private final int STARTING_X = 60;
    private final int STARTING_Y = 375;
    private final int WIDTH = 75;
    private final int HEIGHT = 75;

    public PauseButton(Drawable image)
    {
        super(image);
        this.setPosition(STARTING_X, STARTING_Y);
        this.setSize(WIDTH,HEIGHT);
        this.toFront();
    }
}
