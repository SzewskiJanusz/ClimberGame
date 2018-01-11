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

public class TequilaManager extends AbstractManager<Tequila>
{
    private DrunkIndicator drunkInd;
    /**
     * Initialize List and timer
     */
    public TequilaManager()
    {
        super(Const.TEQUILA_DELAY_SPAWN);
        drunkInd = new DrunkIndicator();
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
            Const.TEQUILA_TEXTURE,
            x,
            ClimberGame.HEIGHT,
            Const.TEQUILA_WIDTH,
            Const.TEQUILA_HEIGHT,
            Const.TEQUILA_BASE_VELOCITY + (int)(GameScreen.difficultyTimer)*2
        );
        entities.add(t);
        GameScreen.stage.addActor(t);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        GameScreen.stage.addActor(new BouncingText("DRUNK", DefComponents.getDefaultLabelStyle(),
                Effect.BAD));
        Player.drunk = true;
        IndicatorController.instance().add(drunkInd,Const.TEQUILA_EFFECT_TIME);
        IndicatorController.instance().placeIndicators();
    }
}
