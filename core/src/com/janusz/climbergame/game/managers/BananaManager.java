package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Bartek on 2017-11-04
 *
 * Menadżer bananów
 */
public class BananaManager extends GoodManager<Banana>
{
    private final Texture texture = new Texture("banana.png");

    public BananaManager()
    {
        super(Const.BANANA_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.BANANA_BOTTOM_RANGE,Const.BANANA_UPPER_RANGE);
    }

    @Override
    protected void spawnEntity()
    {
        // zwrócenie X na widoku wylosowanego miejsca
        int x = selectPlace(MathUtils.random(2,4));
        // stworzenie banana
        Banana b = new Banana
        (
            texture,
            x,
            ClimberGame.HEIGHT,
            Const.BANANA_WIDTH,
            Const.BANANA_HEIGHT,
            Const.BANANA_BASE_VELOCITY + (int)(GameScreen.difficultyTimer * Const.GOOD_TIMER_RATIO)
        );
        entities.add(b);                // dodanie banana do listy
        GameScreen.stage.addActor(b);   // dodanie banana do sceny
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        ScoreManager.getInstance().ScoreLogic.addToScore(500);  // efekt dodanie punktów
        GameScreen.stage.addActor(new BouncingText("+500", DefComponents.LABEL_STYLE,
                Effect.GOOD));                                  // skaczący napis oznaczający
                                                                //          'zjedzenie' banana
    }
}
