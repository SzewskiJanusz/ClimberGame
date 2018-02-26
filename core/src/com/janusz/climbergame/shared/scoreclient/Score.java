package com.janusz.climbergame.shared.scoreclient;

/**
 * Created by Janusz on 2018-02-26.
 */

public class Score
{
    private Long id;
    private Long score;
    private String name;

    public Score() {}

    public Score(Long ide, Long scr, String namee)
    {
        id = ide;
        score = scr;
        name = namee;
    }

    public Long getScore()
    {
        return score;
    }

    public void setScore(Long score)
    {
        this.score = score;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
