package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.player.Player;
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
    private DrunkIndicator drunkIndicator;
    private CoffeeIndicator coffeeIndicator;

    public static IndicatorController indControl;

    private IndicatorController()
    {
        drunkIndicator = new DrunkIndicator();
        coffeeIndicator = new CoffeeIndicator();
    }

    public static IndicatorController instance()
    {
        if (indControl == null)
            indControl = new IndicatorController();

        return indControl;
    }

    public void addDrunkIndicator()
    {
        GameScreen.stage.addActor(drunkIndicator);
        GameScreen.stage.addActor(drunkIndicator.label);
    }

    public void addCoffeeIndicator()
    {
        GameScreen.stage.addActor(coffeeIndicator);
        GameScreen.stage.addActor(coffeeIndicator.label);
    }

    public void update()
    {
        drunkIndicator.toFront();
        coffeeIndicator.toFront();
    }

    public void fatTrigger()
    {

    }
}
