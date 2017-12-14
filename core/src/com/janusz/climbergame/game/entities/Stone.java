package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Janusz on 2017-12-14.
 */

public class Stone extends AbstractItem
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
    public Stone(Texture text, int starting_x, int starting_y, int width, int height, int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(5 * delta);
    }
}
