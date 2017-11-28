package com.janusz.climbergame.game.managers.energy;

/**
 * Created by Janusz on 2017-11-28.
 */

public class EnergyManager
{
    public EnergyBar energyBar;
    public FrameEnergyBar frameEnergyBar;

    // Singleton
    private static EnergyManager energyManager;

    public static EnergyManager getInstance()
    {
        if (energyManager == null)
        {
            energyManager = new EnergyManager();
        }
        return energyManager;
    }
    //

    public EnergyManager()
    {
        energyBar = new EnergyBar();
        frameEnergyBar = new FrameEnergyBar();
    }
}
