package com.janusz.climbergame.game.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.janusz.climbergame.game.managers.EnergyBar;
import com.janusz.climbergame.game.entities.animations.PlayerAnimation;
import com.janusz.climbergame.game.environment.EntireLiana;

/**
 * Player class.
 */

public class Player extends Actor implements IPlayer
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 110;

    public static final int STARTING_X = 380;
    public static final int STARTING_Y = 50;

    public static int place;
    public static PlayerState playerState;

    private PlayerAnimation playerAnimation;

    private Rectangle bounds;
    private float time;
    private TextureRegion currentFrame;


    public Player()
    {
        playerAnimation = new PlayerAnimation();
        playerState = PlayerState.CLIMBING_LIANA;
        this.setSize(WIDTH,HEIGHT);
        bounds = new Rectangle(STARTING_X, STARTING_Y, WIDTH, HEIGHT);
        this.setPosition(STARTING_X, STARTING_Y);

        place = 2;
    }

    @Override
    public void act(float delta)
    {
        time += delta;
        currentFrame = playerAnimation.getActualFrame(time);
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        batch.draw(currentFrame,getX(), getY(), WIDTH, HEIGHT);
    }

    // Override for easier changing coordinates of actor and bounds
    @Override
    public void setX(float x)
    {
        this.setPosition(x, getY());
        getBounds().x = x;
    }

    @Override
    public void setY(float y)
    {
        this.setPosition(getX(), y);
        getBounds().y = y;
    }


    public void jumpLeft()
    {
        if (place > 0)
        {
            if (place == 1 && EnergyBar.actualEnergy <= 0)
                return;

            playerState = place == 1 ? PlayerState.FLYING_WALL : PlayerState.FLYING_LEFT;
            place--;
        }
    }

    public void jumpRight()
    {
        if (place < 3)
        {
            place++;
            playerState = PlayerState.FLYING_RIGHT;
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public boolean catchLiana()
    {
        int lianaSize = EntireLiana.get().getSize();
        for (int i = 0 ; i < lianaSize ; i++)
        {
            switch(place)
            {
                case 1: return Intersector.overlaps(EntireLiana.get().
                        getLianaTileFromFirst(i).getBounds(),bounds);
                case 2: return Intersector.overlaps(EntireLiana.get().
                        getLianaTileFromSecond(i).getBounds(),bounds);
                case 3: return Intersector.overlaps(EntireLiana.get().
                        getLianaTileFromThird(i).getBounds(),bounds);
                default: return false;
            }
        }
        return false;
    }
}
