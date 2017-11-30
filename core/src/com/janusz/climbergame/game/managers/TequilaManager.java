package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.EventHandler;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.entities.Tequila;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefLabel;

/**
 * Created by Janusz on 2017-11-29.
 */

public class TequilaManager extends AbstractManager<Tequila>
{
    /**
     * Initialize List and timer
     */
    public TequilaManager()
    {
        super(3);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(9,18);
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(1,4));
        Tequila t = new Tequila(new Texture("tequila.png"), x, ClimberGame.HEIGHT, 45, 107, 250);
        entities.add(t);
        GameScreen.stage.addActor(t);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        GameScreen.stage.addActor(new BouncingText("DRUNK", DefLabel.getDefaultLabelStyle(),
                Effect.BAD));
        Player.drunk = true;
    }
}
