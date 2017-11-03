package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-12.
 */

public class LianaTile extends AbstractItem
{
    public static final int WIDTH = 50;
    public static final int HEIGHT = 120;


    public static int STARTING_Y = ClimberGame.HEIGHT;


    public LianaTile(int starting_x)
    {
        super(new Texture("liana.png"), starting_x, STARTING_Y, WIDTH , HEIGHT , 250);
    }

    public LianaTile(int starting_x,int starting_y)
    {
        super(new Texture("liana.png"), starting_x, starting_y, WIDTH , HEIGHT , 250);
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

    }
}


