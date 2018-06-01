package com.janusz.climbergame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
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
    private GameScreen screen;

    public BeginningHandler(GameScreen scr)
    {
        this.screen = scr;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        Gdx.input.setInputProcessor(GameScreen.stage);
        GameScreen.onBeginning = false;
        int firstTimer = MathUtils.random(1,6);

        switch (firstTimer)
        {
            case 1: screen.bananaMgr.startTimer(3); break;
            case 2: screen.anvilMgr.startTimer(3); break;
            case 3: screen.coffeeMgr.startTimer(3); break;
            case 4: screen.tequilaMgr.startTimer(3); break;
            case 5: screen.appleMgr.startTimer(3); break;
            case 6: screen.stoneMgr.startTimer(3); break;
        }
        if (firstTimer != 1) screen.bananaMgr.startTimer(MathUtils.random(4,10));
        if (firstTimer != 2) screen.anvilMgr.startTimer(MathUtils.random(4,10));
        if (firstTimer != 6) screen.stoneMgr.startTimer(MathUtils.random(4,10));
        if (firstTimer != 3) screen.coffeeMgr.startTimer(MathUtils.random(4,10));
        if (firstTimer != 4) screen.tequilaMgr.startTimer(MathUtils.random(4,10));
        if (firstTimer != 5) screen.appleMgr.startTimer(MathUtils.random(4,10));

        GameScreen.stage.addActor(screen.lvlTexts.levelNumber);
        GameScreen.stage.addActor(screen.lvlTexts.levelText);
        TapToStartLabel.instance().remove();
        TapImage.instance().remove();
        GameScreen.stage.addActor(ScoreManager.getInstance().ScoreLabel);
        GameScreen.stage.addActor(Player.instance());

        return true;
    }
}
