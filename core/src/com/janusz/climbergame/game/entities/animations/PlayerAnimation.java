package com.janusz.climbergame.game.entities.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.player.Player;

/**
 * Created by Janusz on 2017-11-01.
 */

public class PlayerAnimation
{
    private Animation animation;
    private TextureRegion[] frames;
    private Texture climbingStrip;
    private TextureRegion flyingLeft;
    private TextureRegion flyingRight;
    private TextureRegion dying;

    private int singleFrameWidth = Const.FRAME_WIDTH;
    private int singleFrameHeight = Const.FRAME_HEIGHT;

    public PlayerAnimation()
    {
        init();
    }

    /**
     * Gets current frame based on time and state of player
     * @param time -
     * @return
     */
    public TextureRegion getActualFrame(float time)
    {
        switch (Player.instance().playerState)
        {
            case CLIMBING_LIANA: return animation.getKeyFrame(time, true);
            case FLYING_LEFT: return flyingLeft;
            case FLYING_RIGHT: return flyingRight;
            case DYING: return dying;
        }
        return null;
    }

    public TextureRegion getStartingFrame()
    {
        return animation.getKeyFrame(0, true);
    }

    private void init()
    {
        climbingStrip = new Texture("climbing-strip.png");
        int imageAmount = climbingStrip.getWidth() / singleFrameWidth ;

        initFrames(imageAmount);

        animation = new Animation(1f/imageAmount,frames);

        initFlyingTextures();
    }

    private void initFrames(int imageAmount)
    {
        TextureRegion[][] tmpFrames =
                TextureRegion.split(climbingStrip,singleFrameWidth,singleFrameHeight);


        frames = new TextureRegion[imageAmount];

        for(int i = 0 ; i < 14 ; i++)
        {
            frames[i] = tmpFrames[0][i];
        }
    }

    private void initFlyingTextures()
    {
        flyingLeft = new TextureRegion(new Texture("Ljump.png"));
        flyingRight = new TextureRegion(new Texture("Rjump.png"));
        dying = new TextureRegion(new Texture("death.png"));
    }
}
