package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-01-19.
 */

public class TapImage extends Image
{
    private static TapImage ins;

    public static TapImage instance()
    {
        if (ins == null)
        {
            ins = new TapImage();
        }

        return ins;
    }

    private TapImage()
    {
        super(new Texture("tap.png"));
        this.setSize(Const.TAP_WIDTH, Const.TAP_HEIGHT);
        this.setPosition(Const.TAP_STARTING_X, Const.TAP_STARTING_Y);
    }
}
