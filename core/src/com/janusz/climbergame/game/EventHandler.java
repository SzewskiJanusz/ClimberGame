package com.janusz.climbergame.game;

import com.badlogic.gdx.InputAdapter;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-09-18.
 */

public class EventHandler extends InputAdapter
{
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {

        if (isFingerOnLeft(screenX))
        {
            GameScreen.player.jumpLeft();
        }
        else
        {
            GameScreen.player.jumpRight();
        }

        return true;
    }

    private boolean isFingerOnLeft(int screenX)
    {
        return GameScreen.player.getX() > screenX;
    }


}
