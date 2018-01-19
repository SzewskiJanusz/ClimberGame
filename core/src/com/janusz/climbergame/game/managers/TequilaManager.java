package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.Tequila;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.indicators.graphics.DrunkIndicator;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-11-29.
 */

public class TequilaManager extends GoodManager<Tequila>
{
    /**
     * Initialize List and timer
     */
    private final Texture texture = new Texture("tequila.png");

    public TequilaManager()
    {
        super(Const.TEQUILA_DELAY_SPAWN);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(Const.TEQUILA_BOTTOM_RANGE ,Const.TEQUILA_UPPER_RANGE );
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(2,4));
        Tequila t = new Tequila
        (
            texture,
            x,
            ClimberGame.HEIGHT,
            Const.TEQUILA_WIDTH,
            Const.TEQUILA_HEIGHT,
            Const.TEQUILA_BASE_VELOCITY + (int)(GameScreen.difficultyTimer * Const.BAD_TIMER_RATIO)
        );
        entities.add(t);
        GameScreen.stage.addActor(t);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        GameScreen.stage.addActor(new BouncingText("DRUNK", DefComponents.LABEL_STYLE,
                Effect.BAD));
        Player.drunk = true;
        IndicatorController.instance().addDrunkIndicator();
    }
}
