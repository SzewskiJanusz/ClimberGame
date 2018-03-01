package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-11-29.
 */

public class Tequila extends AbstractItem
{

    public Tequila(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(20 * delta);
    }

    @Override
    public void triggerEffect()
    {
        GameScreen.stage.addActor(new BouncingText("DRUNK", DefComponents.LABEL_STYLE,
                Effect.BAD));
        Player.drunk = true;
        IndicatorController.instance().addDrunkIndicator();
        GameSound.instance().playFatFries();
    }
}
