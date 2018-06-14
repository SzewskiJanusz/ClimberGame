package com.janusz.climbergame.game.bosses;


import java.util.ArrayList;
import java.util.List;

public class Level
{
    private int items;
    private List<ItemWave> waves;

    public Level(int waveCount)
    {
        waves = new ArrayList<ItemWave>();
        for (int i = 0; i < waveCount; i++)
        {
            waves.add(new ItemWave(3, false));
        }
    }

    public Level(int waveCount, int[] directions)
    {

    }

    public Level(int waveCount, int[] directions, int waveSize)
    {

    }
}
