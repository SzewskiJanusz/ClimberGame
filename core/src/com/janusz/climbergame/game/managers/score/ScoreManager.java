package com.janusz.climbergame.game.managers.score;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-11-28.
 */

public class ScoreManager
{
    public com.janusz.climbergame.game.managers.score.ScoreLabel ScoreLabel;
    public ScoreLogic ScoreLogic;

    // Singleton
    private static ScoreManager scoreManager;

    public static ScoreManager getInstance()
    {
        if(scoreManager == null) {
            scoreManager = new ScoreManager();
        }
        return scoreManager;
    }
    //
    private ScoreManager()
    {
        ScoreLogic = new ScoreLogic();
        ScoreLabel = new ScoreLabel(String.valueOf(ScoreLogic.getScore()), DefComponents.LABEL_STYLE);
    }


    public void update()
    {
        passPointsToLabel();
    }

    private void passPointsToLabel()
    {
        ScoreLabel.setPoints(ScoreLogic.getScore());
    }
}
