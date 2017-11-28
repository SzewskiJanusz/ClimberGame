package com.janusz.climbergame.game.score;

/**
 * Created by Janusz on 2017-11-28.
 */

public class ScoreLogic
{
    private int score;

    public ScoreLogic()
    {
        score = 0;
    }

    public void addToScore(int add)
    {
        score += add;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int newScore)
    {
        score = newScore;
    }
}
