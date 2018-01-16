package com.janusz.climbergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.texts.TapToStartLabel;

/**
 * Created by Janusz on 2018-01-16.
 */

public class BeginningHandler extends InputAdapter
{
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Gdx.input.setInputProcessor(new EventHandler());
        GameScreen.onBeginning = false;
        TapToStartLabel.instance().remove();
        return true;
    }
}
