package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;

/**
 * Created by Janusz on 2017-09-12.
 */

public class LianaTile extends AbstractItem
{
    public static final int WIDTH = Const.LIANATILE_WIDTH;
    public static final int HEIGHT = Const.LIANATILE_HEIGHT;

    public static int STARTING_Y = ClimberGame.HEIGHT;


    public LianaTile(int starting_x)
    {
        super(new Texture("liana.png"), starting_x, STARTING_Y, WIDTH , HEIGHT ,
                Const.LIANATILE_VELOCITY);
    }

    public LianaTile(int starting_x,int starting_y)
    {
        super(new Texture("liana.png"), starting_x, starting_y, WIDTH , HEIGHT ,
                Const.LIANATILE_VELOCITY);
    }

    public void stickLianaToAnother(LianaTile destination)
    {
        this.setY(destination.getY() + HEIGHT);
    }

    @Override
    public void moveDown(float delta)
    {
        this.setY(this.getY() - delta * 250);
    }

    @Override
    protected void doMovement(float delta)
    {
        // EMPTY: no movement
    }
}


