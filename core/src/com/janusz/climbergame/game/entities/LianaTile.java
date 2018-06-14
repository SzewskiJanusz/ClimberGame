package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;

public class LianaTile extends Image
{
    public static int STARTING_Y = ClimberGame.HEIGHT;
    private int velocity;
    // Obszar kolizji
    protected Rectangle bounds;

    public LianaTile(int starting_x)
    {
        super(EntityTextures.get().lianatile);
        this.velocity = Const.LIANATILE_VELOCITY;
        this.setPosition(starting_x, STARTING_Y);
        setSize(Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, STARTING_Y, Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
    }

    public LianaTile(int starting_x,int starting_y)
    {
        super(EntityTextures.get().lianatile);
        this.velocity = Const.LIANATILE_VELOCITY;
        this.setPosition(starting_x, starting_y);
        setSize(Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, starting_y, Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
    }

    public void stickLianaToAnother(LianaTile destination)
    {
        this.setY(destination.getY() + Const.LIANATILE_HEIGHT);
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


