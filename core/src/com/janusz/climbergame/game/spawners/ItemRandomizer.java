package com.janusz.climbergame.game.spawners;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.entities.Apple;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.entities.Carrot;
import com.janusz.climbergame.game.entities.Coffee;
import com.janusz.climbergame.game.entities.Fries;
import com.janusz.climbergame.game.entities.Grapes;
import com.janusz.climbergame.game.entities.Hamburger;
import com.janusz.climbergame.game.entities.Hotdog;
import com.janusz.climbergame.game.entities.Mango;
import com.janusz.climbergame.game.entities.Peach;
import com.janusz.climbergame.game.entities.Pear;
import com.janusz.climbergame.game.entities.Pineapple;
import com.janusz.climbergame.game.entities.Pomegranate;
import com.janusz.climbergame.game.entities.Satellite;
import com.janusz.climbergame.game.entities.Stone;
import com.janusz.climbergame.game.entities.Tequila;
import com.janusz.climbergame.game.entities.Trashcan;
import com.janusz.climbergame.game.entities.Treasure;
import com.janusz.climbergame.game.entities.Watermelon;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.states.PlayGameState;

public class ItemRandomizer
{

    private PlayGameState playGameState;

    public ItemRandomizer(PlayGameState pgs)
    {
        this.playGameState = pgs;
    }

    public AbstractItem getRandomGoodItem()
    {
        int nmb = MathUtils.random(1,11);
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        switch(nmb)
        {
            case 1: return new Banana(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 2: return new Apple(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 3: return new Carrot(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 4: return new Grapes(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 5: return new Pear(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 6: return new Pineapple(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 7: return new Mango(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 8: return new Peach(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 9: return new Pomegranate(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 10: return new Watermelon(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 11:
                if (MathUtils.random(0,2) == 0)
                    return new Treasure(playGameState, x, 75 + playGameState.difficultyControl.levelVelocity);
                else return new Banana(playGameState, x, playGameState.difficultyControl.levelVelocity);
            default: throw new IllegalArgumentException("Randomized number: "+nmb);
        }
    }

    public AbstractItem getCoffeeWithRandomDirection()
    {
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        return new Coffee(playGameState, x, playGameState.difficultyControl.levelVelocity);
    }

    public AbstractItem getFatItemWithRandomDirection()
    {
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        int chooseItem = MathUtils.random(0,2);
        switch (chooseItem)
        {
            case 0 : return new Fries(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 1 : return new Hotdog(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 2 : return new Hamburger(playGameState, x, playGameState.difficultyControl.levelVelocity);
            default: throw new IllegalArgumentException("RandomFatError");
        }
    }

    public AbstractItem getTequilaWithRandomDirection()
    {
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        return new Tequila(playGameState, x, playGameState.difficultyControl.levelVelocity);
    }

    public AbstractItem getTreasureWithRandomDirection()
    {
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        return new Treasure(playGameState, x, playGameState.difficultyControl.levelVelocity);
    }

    public AbstractItem getRandomBadItem()
    {
        int nmb = MathUtils.random(1,4);
        int direction = MathUtils.random(2,4);
        int x = selectPlace(direction);
        switch(nmb)
        {
            case 1: return new Anvil(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 2: return new Stone(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 3: return new Trashcan(playGameState, x, playGameState.difficultyControl.levelVelocity);
            case 4:
                if (MathUtils.random(0,4) == 0)
                    return new Satellite(playGameState, x, 250 + playGameState.difficultyControl.levelVelocity);
                else return new Stone(playGameState, x, playGameState.difficultyControl.levelVelocity);
            default: throw new IllegalArgumentException("Randomized number: "+nmb);
        }
    }

    private int selectPlace(int place)
    {
        switch (place)
        {
            case 2: return EntireLiana.first_liana_x;
            case 3: return EntireLiana.second_liana_x;
            case 4: return EntireLiana.third_liana_x;
            default: throw new IllegalArgumentException("Place is: "+place);
        }
    }
}
