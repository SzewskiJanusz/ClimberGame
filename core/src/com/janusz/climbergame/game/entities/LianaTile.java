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
    private boolean shaking;
    private final float biggerShakeTimer = 0.05f;
    private final float smallerShakeTimer = 0.15f;
    private final float returnShakeTimer = 0.2f;
    private float shakeTimer;
    private int powerOfShake;
    private int originalX;
    private int shakingDirection;

    public LianaTile(int starting_x, int lianatype)
    {
        super(lianatype == 0 ? EntityTextures.get().lianatile1 :
                        EntityTextures.get ().lianatile2
        );
        this.originalX = starting_x;
        this.velocity = Const.LIANATILE_VELOCITY;
        this.setPosition(starting_x, STARTING_Y);
        setSize(Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
        setOrigin(getWidth() / 2, getHeight() / 2);
        bounds = new Rectangle(starting_x, STARTING_Y, Const.LIANATILE_WIDTH ,Const.LIANATILE_HEIGHT);
    }

    public LianaTile(int starting_x,int starting_y, int lianatype)
    {
        super(lianatype == 0 ? EntityTextures.get().lianatile1 : EntityTextures.get().lianatile2);
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

    @Override
    public void act(float delta)
    {
        super.act(delta);
        if (shaking)
        {
            if (shakeTimer <= biggerShakeTimer)
            {
                this.rotateBy(2*powerOfShake*delta*shakingDirection);
                this.moveBy(shakingDirection*(11 - powerOfShake), 0);
            }
            else if (shakeTimer <= smallerShakeTimer)
            {
                this.rotateBy(-3*powerOfShake*delta*shakingDirection);
                this.moveBy(-(11 - powerOfShake)*shakingDirection, 0);
            }
            else if (shakeTimer <= returnShakeTimer)
            {
                this.rotateBy(powerOfShake*delta*shakingDirection);
                this.moveBy((11 - powerOfShake)*shakingDirection, 0);
            }
            else
            {
                shaking = false;
                shakeTimer = 0;
                this.setX(originalX);
                this.setRotation(0);
            }
            shakeTimer += delta;
        }
    }

    public void shakeAfterJump(int i, int direction)
    {
        shaking = true;
        powerOfShake = i;
        shakingDirection = direction;
    }
}


