package com.janusz.climbergame.game.pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2018-01-21.
 */

public class PauseButton extends ImageButton
{

    private final int STARTING_X = 60;
    private final int STARTING_Y = 375;

    public float stageX;
    public float stageY;

    public float stageWidth;
    public float stageHeight;

    private static PauseButton ins;

    public static PauseButton instance()
    {
        if (ins == null){
            Texture pauseTexture = new Texture(Gdx.files.internal("pausebtn.png"));
            TextureRegion pauseTextureRegion = new TextureRegion(pauseTexture);
            TextureRegionDrawable pauseTexRegionDrawable = new TextureRegionDrawable(pauseTextureRegion);
            ins = new PauseButton(pauseTexRegionDrawable);
            ins.toFront();
        }

        return ins;
    }

    private PauseButton(Drawable image)
    {
        super(image);
        this.setPosition(STARTING_X, STARTING_Y);
        this.setSize(75,75);
        Vector2 stageCoords = localToStageCoordinates(new Vector2(STARTING_X,STARTING_Y));
        Vector2 stageSize = localToStageCoordinates(new Vector2(75,75));
        stageX = stageCoords.x;
        stageY = ClimberGame.HEIGHT - stageCoords.y;
        stageWidth = stageSize.x;
        stageHeight = stageSize.y;
    }
}
