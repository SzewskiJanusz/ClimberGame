package com.janusz.climbergame.game.entities.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.managers.energy.EnergyBar;
import com.janusz.climbergame.game.entities.animations.PlayerAnimation;
import com.janusz.climbergame.game.environment.EntireLiana;

/**
 * Player class.
 */

public class Player extends Actor implements IPlayer
{
    public static final int WIDTH = 80;
    public static final int HEIGHT = 110;

    public static final int STARTING_X = EntireLiana.second_liana_x;
    public static final int STARTING_Y = 50;

    public int place;
    // PlayerState ENUM. Used for determine actual activity of player
    public PlayerState playerState;
    public static boolean drunk;

    // Animation class
    private PlayerAnimation playerAnimation;

    private Rectangle bounds;
    // Animation counter
    private float time;
    private float drunkTime;
    private float coffeeTime;
    private TextureRegion currentFrame;
    private Vector2 velocity;

    private static Player player;
    private boolean caffeinated;

    private Player()
    {
        playerAnimation = new PlayerAnimation();
        playerState = PlayerState.CLIMBING_LIANA;
        this.setSize(WIDTH,HEIGHT);
        bounds = new Rectangle(STARTING_X, STARTING_Y, WIDTH, HEIGHT);
        reset();
    }

    public void reset()
    {
        this.setPosition(STARTING_X, STARTING_Y);
        this.setX(STARTING_X);
        this.setY(STARTING_Y);
        velocity = new Vector2(6.5f, -4);
        place = 2;
    }

    public static Player instance()
    {
        if (player == null)
            player = new Player();

        return player;
    }

    @Override
    public void act(float delta)
    {
        time += delta;
        currentFrame = playerAnimation.getActualFrame(time);

        if (drunk)
        {
            if (drunkTime >= Const.TEQUILA_EFFECT_TIME)
            {
                drunkTime = 0;
                drunk = false;
            }
            drunkTime += delta;
        }

        if (caffeinated)
        {
            if (coffeeTime >= Const.COFFEE_EFFECT_TIME)
            {
                coffeeTime = 0;
                caffeinated = false;
                velocity.x = 6.5f;
            }
            coffeeTime += delta;
        }
        movePlayer(delta);
        toFront();
    }


    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX(), getY(), WIDTH, HEIGHT);
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
        if (place > 1)
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

    public void movePlayer(float delta)
    {
        if (playerState != PlayerState.CLIMBING_LIANA)
            setState(delta);

        if (Player.instance().playerState == PlayerState.FLYING_WALL)
        {
            velocity.y += delta * 10;

            setX(getX() - velocity.x);
            setY(getY() - velocity.y);
        }
    }

    private void setState(float delta)
    {
        if (catchLiana())
        {
            playerState = PlayerState.CLIMBING_LIANA;
            setPlayerOnLiana();
        }
        else
        {
            velocity.y += delta * 10;
            int sign = (playerState == PlayerState.FLYING_RIGHT ? 1 : -1);

            setX(getX() + sign*velocity.x);
            setY(getY() - velocity.y);
        }
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public boolean catchLiana()
    {
        int lianaSize = EntireLiana.get().getSize();
        // Check only 3 last liana tiles
        for (int i = lianaSize - 3 ; i < lianaSize ; i++)
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

    private void setPlayerOnLiana()
    {
        switch(place)
        {
            case 1: setX(EntireLiana.first_liana_x - 16); break;
            case 2: setX(EntireLiana.second_liana_x - 16); break;
            case 3: setX(EntireLiana.third_liana_x - 16); break;
            default: throw new IllegalArgumentException("Error in player place. (" + place +")");
        }
        setY(Player.STARTING_Y);
        velocity.y = -4;
    }

    public void coffeeBoost()
    {
        velocity.x = 10;
        coffeeTime = 0;
        caffeinated = true;
    }
}
