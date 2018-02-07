package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Apple;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-02-07.
 */

public class AppleManager extends GoodManager<Apple>
{
    private final Texture texture = new Texture("apple.png");


    public AppleManager()
    {
        super(Const.APPLE_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.APPLE_BOTTOM_RANGE,Const.APPLE_UPPER_RANGE);
    }

    @Override
    protected void spawnEntity()
    {
        // zwrócenie X na widoku wylosowanego miejsca
        int x = selectPlace(MathUtils.random(2,4));
        // stworzenie banana
        Apple a = new Apple
                (
                        texture,
                        x,
                        ClimberGame.HEIGHT,
                        Const.APPLE_WIDTH,
                        Const.APPLE_HEIGHT,
                        Const.APPLE_BASE_VELOCITY + (int)(GameScreen.difficultyTimer * Const.GOOD_TIMER_RATIO)
                );
        entities.add(a);                // dodanie jabłka do listy
        GameScreen.stage.addActor(a);   // dodanie jabłka do sceny
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        ScoreManager.getInstance().ScoreLogic.addToScore(500);  // efekt dodanie punktów
        GameScreen.stage.addActor(new BouncingText("+500", DefComponents.LABEL_STYLE,
                Effect.GOOD));                                  // skaczący napis oznaczający
        //          'zjedzenie' jabłka
    }
}
