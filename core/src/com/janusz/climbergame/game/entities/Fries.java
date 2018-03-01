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
 * Created by Janusz on 2018-02-13.
 */

public class Fries extends AbstractItem
{
    /**
     * Konstruktor. Inicjalizacja obiektu i bounds
     *
     * @param text       - textura użyta w obiekcie
     * @param starting_x - początkowy X
     * @param starting_y - początkowy Y
     * @param width      - szerokość
     * @param height     - wysokość
     * @param velocity   - prędkość
     */
    public Fries(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    public void triggerEffect()
    {
        Player.instance().makePlayerFat();
        GameScreen.stage.addActor(new BouncingText("FAT", DefComponents.LABEL_STYLE,
                Effect.BAD));
        IndicatorController.instance().addFatIndicator();
        GameSound.instance().playFatFries();
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(45 * delta);
    }



}
