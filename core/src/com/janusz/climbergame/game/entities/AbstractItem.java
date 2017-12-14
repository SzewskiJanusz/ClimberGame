package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-22.
 *
 * Klasa abstrakcyjnego obiektu. Zawiera podstawowe metody opisujące obiekt:
 *
 * doMovement(float delta): poruszenie obiektem
 * Konstruktor: podstawowe cechy nadawane obiektowi(rozmiar, pozycja, prędkość), inicjalizacja bounds
 * setY: nadpisana metody w celu objęcia bounds
 * moveDown(float delta): poruszenie obiektu w dół (symulacja grawitacji)
 * update(float delta): odświeżenie obiektu
 * checkIfNeedDispose: usuń obiekt z pamięci, gdy nie jest widoczny w widoku
 * getBounds: getter bounds
 */
public abstract class AbstractItem extends Image
{
    // Obszar kolizji
    protected Rectangle bounds;
    // Prędkość obiektu
    protected int velocity;

    /**
     * Poruszenie obiektem podczas opadania
     * @param delta - czas jednej klatki
     */
    protected abstract void doMovement(float delta);

    /**
     * Konstruktor. Inicjalizacja obiektu i bounds
     * @param text - textura użyta w obiekcie
     * @param starting_x - początkowy X
     * @param starting_y - początkowy Y
     * @param width - szerokość
     * @param height - wysokość
     * @param velocity - prędkość
     */
    public AbstractItem(Texture text,int starting_x, int starting_y, int width, int height,int velocity)
    {
        super(text);
        this.velocity = velocity;
        this.setPosition(starting_x, starting_y);
        setSize(width,height);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, starting_y, width, height);
    }

    /**
     * Nadpisana metoda w celu objęcia bounds
     * @param y - współrzędna Y do ustawienia
     */
    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        bounds.y = y;
    }

    /**
     * Symulacja grawitacji dla obiektu
     * @param delta - czas jednej klatki
     */
    public void moveDown(float delta)
    {
        this.setY(this.getY() - velocity * delta);
    }


    /**
     * Odświeżenie obiektu
     * @param delta - czas jednej klatki
     */
    public void update(float delta)
    {
        checkIfNeedDispose();
        moveDown(delta);
        doMovement(delta);

    }

    /**
     * Sprawdzenie czy obiekt jest poza widokiem i usunięcie go
     */
    protected void checkIfNeedDispose()
    {
        if (getY() < 0)
        {
            this.remove();
        }
    }

    /**
     * Getter bounds
     * @return bounds
     */
    public Rectangle getBounds()
    {
        return bounds;
    }

}
