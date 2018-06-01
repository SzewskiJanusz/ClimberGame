package com.janusz.climbergame.game.indicators.graphics;

import com.badlogic.gdx.Game;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-12-14.
 *
 * Contain list of all indicators and place them accordingly of taken effects
 */
public class IndicatorController
{
    private DrunkIndicator drunkIndicator;
    private CoffeeIndicator coffeeIndicator;
    private FatIndicator fatIndicator;

    public static IndicatorController indControl;

    private IndicatorController()
    {
        drunkIndicator = new DrunkIndicator();
        coffeeIndicator = new CoffeeIndicator();
        fatIndicator = new FatIndicator();
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
        Player.fat = false;
        fatIndicator.remove();
        fatIndicator.label.remove();
        GameScreen.stage.addActor(coffeeIndicator);
        GameScreen.stage.addActor(coffeeIndicator.label);
    }

    public void update()
    {
        drunkIndicator.toFront();
        coffeeIndicator.toFront();
        fatIndicator.toFront();
    }

    public void addFatIndicator()
    {
        Player.caffeinated = false;
        coffeeIndicator.remove();
        coffeeIndicator.label.remove();
        GameScreen.stage.addActor(fatIndicator);
        GameScreen.stage.addActor(fatIndicator.label);
    }

    public void dispose()
    {
        drunkIndicator.dispose();
        coffeeIndicator.dispose();
        fatIndicator.dispose();
    }
}
