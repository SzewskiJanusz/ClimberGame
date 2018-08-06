package com.janusz.climbergame.game.managers;

import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.managers.pause.PauseController;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.game.texts.LevelTexts;
import com.janusz.climbergame.shared.DefComponents;

public class HUD
{
    public PauseController pauseControl;
    public IndicatorController indicatorControl;
    public LevelTexts lvlTexts;
    public PlayGameState playGameState;

    public HUD(PlayGameState pgs)
    {
        this.playGameState = pgs;
        pauseControl = new PauseController(playGameState);
        pauseControl.pauseButton.setVisible(true);
        indicatorControl = new IndicatorController(playGameState);
        lvlTexts = new LevelTexts(String.valueOf(1), DefComponents.LABEL_STYLE);
        addLevelTexts();
        addPauseControls();
    }

    private void addLevelTexts()
    {
        playGameState.gameScreen.stage.addActor(lvlTexts.levelNumber);
        playGameState.gameScreen.stage.addActor(lvlTexts.levelText);
    }

    private void addPauseControls()
    {
        playGameState.gameScreen.stage.addActor(pauseControl.pauseButton);
        playGameState.gameScreen.stage.addActor(pauseControl.pauseLabel);
    }
}
