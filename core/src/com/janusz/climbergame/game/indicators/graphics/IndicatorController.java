package com.janusz.climbergame.game.indicators.graphics;

import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.states.PlayGameState;

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
    private PlayGameState playGameState;


    public IndicatorController(PlayGameState pgs)
    {
        drunkIndicator = new DrunkIndicator(pgs.player);
        coffeeIndicator = new CoffeeIndicator(pgs.player);
        fatIndicator = new FatIndicator(pgs.player);
        playGameState = pgs;
    }

    public void addDrunkIndicator()
    {
        playGameState.gameScreen.stage.addActor(drunkIndicator);
        playGameState.gameScreen.stage.addActor(drunkIndicator.label);
    }

    public void addCoffeeIndicator()
    {
        Player.fat = false;
        fatIndicator.remove();
        fatIndicator.label.remove();
        playGameState.gameScreen.stage.addActor(coffeeIndicator);
        playGameState.gameScreen.stage.addActor(coffeeIndicator.label);
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
        playGameState.gameScreen.stage.addActor(fatIndicator);
        playGameState.gameScreen.stage.addActor(fatIndicator.label);
    }

    public void dispose()
    {
        drunkIndicator.dispose();
        coffeeIndicator.dispose();
        fatIndicator.dispose();
    }
}
