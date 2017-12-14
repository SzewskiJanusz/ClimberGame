package com.janusz.climbergame.game.indicators.graphics;

import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-12-14.
 *
 * Contain list of all indicators and place them accordingly of taken effects
 */
public class IndicatorController
{
    private List<AbstractIndicator> indicators;
    private static IndicatorController indControl;

    private IndicatorController()
    {
        indicators = new ArrayList<AbstractIndicator>();
    }

    public static IndicatorController instance()
    {
        if (indControl == null)
            indControl = new IndicatorController();

        return indControl;
    }

    public void placeIndicators()
    {
        for (int i = 0 ; i < indicators.size() ; i++)
        {
            indicators.get(i).setPosition(550,(i+1) * 150);
        }
    }

    public void add(AbstractIndicator ai)
    {
        indicators.add(ai);
        GameScreen.stage.addActor(ai);
    }

}
