package com.janusz.climbergame.game.managers.score;

import com.janusz.climbergame.shared.DefComponents;

public class ScoreManager
{
    public ScoreLabel ScoreLabel;
    public ScoreLogic ScoreLogic;

    public ScoreManager()
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
