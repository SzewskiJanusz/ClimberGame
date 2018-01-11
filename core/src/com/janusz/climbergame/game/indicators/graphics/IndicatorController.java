package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

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
    private List<Label> labels;
    private static IndicatorController indControl;

    private IndicatorController()
    {
        indicators = new ArrayList<AbstractIndicator>();
        labels = new ArrayList<Label>();
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
            labels.get(i).setPosition(600,(i+1) * 170);
        }
    }

    public void add(AbstractIndicator ai,int time)
    {
        indicators.add(ai);
        Label l = new Label(String.valueOf(time), DefComponents.getDefaultLabelStyle());
        l.setFontScale(3.7f);
        labels.add(l);
        GameScreen.stage.addActor(ai);
        GameScreen.stage.addActor(l);
    }

}
