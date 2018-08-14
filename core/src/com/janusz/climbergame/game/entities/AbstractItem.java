package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.states.PlayGameState;

/**
 * Klasa abstrakcyjnego itemu. Zawiera podstawowe metody opisujące obiekt:
 */
public abstract class AbstractItem extends Image
{
    protected Rectangle bounds;
    protected int velocity;
    protected PlayGameState playGameState;
    protected int rotation;
    protected int direction;

    public abstract void triggerEffect();

    protected abstract void doMovement(float delta);

    public AbstractItem(PlayGameState pgs, Texture text, int starting_x, int velocity)
    {
        super(text);
        this.playGameState = pgs;
        this.velocity = 230 + velocity;
        this.setPosition(starting_x, ClimberGame.HEIGHT);
        direction = MathUtils.randomSign();
    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        bounds.y = y;
    }

    @Override
    public void act(float delta)
    {
        super.act(delta);
        checkIfNeedDispose();
        moveDown(delta);
        doMovement(delta);
        System.out.println("ACT");
    }

    private void checkIfNeedDispose()
    {
        if (getY() + getHeight() < 0)
        {
            playGameState.entities.remove(this);
            this.remove();
        }
    }

    private void moveDown(float delta)
    {
        this.setY(this.getY() - velocity * delta);
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
