package com.janusz.climbergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.texts.TapImage;
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

        GameScreen.bananaMgr.startTimer();
        GameScreen.anvilMgr.startTimer();
        GameScreen.stoneMgr.startTimer();
        GameScreen.coffeeMgr.startTimer();
        GameScreen.tequilaMgr.startTimer();
    
        TapToStartLabel.instance().remove();
        TapImage.instance().remove();
        GameScreen.stage.addActor(ScoreManager.getInstance().ScoreLabel);
        GameScreen.stage.addActor(Player.instance());

        return true;
    }
}
