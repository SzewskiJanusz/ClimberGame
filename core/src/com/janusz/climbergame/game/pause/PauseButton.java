package com.janusz.climbergame.game.pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.screens.GameScreen;

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

    public float stageXAndWidth;
    public float stageYAndHeight;

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
        Vector2 stageCoords = localToStageCoordinates(new Vector2(0,0));
        Vector2 stageSize = localToStageCoordinates(new Vector2(75,75));
        stageX = stageCoords.x;
        stageY = stageCoords.y;
        stageWidth = stageSize.x;
        stageHeight = stageSize.y;
        stageXAndWidth = stageX + stageWidth;
        stageYAndHeight = stageY + stageHeight;
        this.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                if (event.getListenerActor().equals(PauseButton.instance()) )
                {
                    if (GameScreen.paused)
                    {
                        GameScreen.paused = false;
                        Timer.instance().start();
                    } else
                    {
                        GameScreen.paused = true;
                        Timer.instance().stop();
                    }
                }
                return true;
            }
        });
    }
}
