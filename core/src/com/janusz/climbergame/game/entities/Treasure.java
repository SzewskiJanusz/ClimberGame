package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-02-13.
 */

public class Treasure extends AbstractItem
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
    public Treasure(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    public void triggerEffect()
    {
        ScoreManager.getInstance().ScoreLogic.addToScore(2000);
        GameScreen.stage.addActor(new BouncingText("+2000", DefComponents.LABEL_STYLE,
                Effect.GOOD));
        GameSound.instance().playTreasure();

    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(15 * delta); // obracanie z każdą klatką
    }
}
