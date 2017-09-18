package com.janusz.climbergame.screens;

import com.badlogic.gdx.InputAdapter;

/**
 * Created by Janusz on 2017-09-18.
 */

public class EventHandler extends InputAdapter
{
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {

        if (GameScreen.player.getX() > screenX)
        {

        }
        else
        {
            
        }

        return true;
    }
}
