package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-01-15.
 */

public class TapToStartLabel extends Label
{
    private static TapToStartLabel ins;

    public static TapToStartLabel instance()
    {
        if (ins == null)
        {
            ins = new TapToStartLabel("TAP TO START GAME", DefComponents.LABEL_STYLE);
        }

        return ins;
    }



    private TapToStartLabel(CharSequence text, Label.LabelStyle style)
    {
        super(text, style);
        this.setFontScale(3,4);
        this.setX(Const.TAPLABEL_STARTING_X);
        this.setY(Const.TAPLABEL_STARTING_Y);
    }



    @Override
    public void act(float delta)
    {
        super.act(delta);
    }
}
