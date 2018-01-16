package com.janusz.climbergame.game.texts;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-01-15.
 */

public class TapToStartLabel extends Label
{
    private final int STARTING_X = ClimberGame.WIDTH/3;
    private final int STARTING_Y = ClimberGame.HEIGHT/2;

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
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
    }




    @Override
    public void act(float delta)
    {
        super.act(delta);
    }
}
