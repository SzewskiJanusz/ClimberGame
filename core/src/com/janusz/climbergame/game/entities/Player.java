package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.game.controllers.EnergyBar;

/**
 * Player class.
 */

public class Player extends Actor
{

    public static final int WIDTH = 65;
    public static final int HEIGHT = 90;

    public static final int STARTING_X = 380;
    public static final int STARTING_Y = 50;

    public static int place;

    public Animation animation;

    private Texture text;
    private TextureRegion[] frames;
    // Rectangle for collision
    private Rectangle bounds;
    private float time;
    private TextureRegion currentFrame;


    public Player()
    {
        text = new Texture("climbing-strip.png");
        this.setOrigin(STARTING_X / 2, STARTING_Y / 2);
        this.setSize(WIDTH,HEIGHT);
        TextureRegion[][] tmpFrames = TextureRegion.split(text,260,260);
        frames = new TextureRegion[14];
        int index = 0;

        for(int i = 0 ; i < 14 ; i++)
        {
            frames[index++] = tmpFrames[0][i];
        }

        bounds = new Rectangle(STARTING_X, STARTING_Y, WIDTH, HEIGHT);

        animation = new Animation(1f/14,frames);

        place = 2;
    }

    @Override
    public void act(float delta)
    {
        time += delta;
        currentFrame = animation.getKeyFrame(time, true);
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

            place--;
        }
    }

    public void jumpRight()
    {
        if (place < 3)
        {
            place++;
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
