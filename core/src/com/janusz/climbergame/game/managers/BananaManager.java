package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.energy.EnergyManager;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Bartek on 2017-11-04
 *
 * Menadżer bananów
 */
public class BananaManager extends AbstractManager<Banana>
{
    public BananaManager()
    {
        super(3);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(3,7);
    }

    @Override
    protected void spawnEntity()
    {
        // zwrócenie X na widoku wylosowanego miejsca
        int x = selectPlace(MathUtils.random(1,4));
        // stworzenie banana
        Banana b = new Banana(new Texture("banana.png"), x, ClimberGame.HEIGHT, 45, 30, 215);
        entities.add(b);                // dodanie banana do listy
        GameScreen.stage.addActor(b);   // dodanie banana do sceny
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        EnergyManager.getInstance().energyBar.addEnergy();      // efekt dodania energii
        ScoreManager.getInstance().ScoreLogic.addToScore(500);  // efekt dodanie punktów
        GameScreen.stage.addActor(new BouncingText("+500", DefComponents.getDefaultLabelStyle(),
                Effect.GOOD));                                  // skaczący napis oznaczający
                                                                //          'zjedzenie' banana
    }
}
