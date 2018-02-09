package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;

/**
 * Created by Janusz on 2017-09-12.
 */

public class LianaTile extends Image
{
    public static final int WIDTH = Const.LIANATILE_WIDTH;
    public static final int HEIGHT = Const.LIANATILE_HEIGHT;

    public static int STARTING_Y = ClimberGame.HEIGHT;
    private int velocity;
    // Obszar kolizji
    protected Rectangle bounds;

    public LianaTile(int starting_x)
    {
        super(new Texture("liana.png"));
        this.velocity = Const.LIANATILE_VELOCITY;
        this.setPosition(starting_x, STARTING_Y);
        setSize(WIDTH,HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, STARTING_Y, WIDTH,HEIGHT);
    }

    public LianaTile(int starting_x,int starting_y)
    {
        super(new Texture("liana.png"));
        this.velocity = Const.LIANATILE_VELOCITY;
        this.setPosition(starting_x, starting_y);
        setSize(WIDTH,HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, starting_y, WIDTH,HEIGHT);
    }

    public void stickLianaToAnother(LianaTile destination)
    {
        this.setY(destination.getY() + HEIGHT);
        bounds.y = getY();
    }

    public void moveDown(float delta)
    {
        this.setY(this.getY() - delta * velocity);
    }
    public Rectangle getBounds()
    {
        return bounds;
    }
}


