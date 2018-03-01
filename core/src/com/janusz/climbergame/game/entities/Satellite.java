package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;

/**
 * Created by Janusz on 2018-02-13.
 */

public class Satellite extends AbstractItem
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
    public Satellite(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    public void triggerEffect()
    {
        GameScreen.deathAnimation = true;
        GameSound.instance().playDeath();
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(-10 * delta); // obracanie z każdą klatką
    }
}
