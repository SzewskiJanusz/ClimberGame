package com.janusz.climbergame.game.pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;


/**
 * Created by Janusz on 2018-02-05.
 *
 * Contains pause button and label management with functions
 */

public class PauseController
{
    public PauseButton pauseButton;
    public PauseLabel pauseLabel;

    private static PauseController ins;

    public static PauseController instance()
    {
        if (ins == null)
        {
            ins = new PauseController();
        }

        return ins;
    }

    public void showLabel()
    {
        pauseLabel.toFront();
    }

    public void hideLabel()
    {
        pauseLabel.toBack();
    }


    private PauseController()
    {
        createButton();
        createLabel();
    }

    private void createLabel()
    {
        String text = "PAUSED";
        pauseLabel = new PauseLabel(text, DefComponents.LABEL_STYLE);
    }

    private void createButton()
    {
        Texture pauseTexture = new Texture(Gdx.files.internal("pausebtn.png"));
        TextureRegion pauseTextureRegion = new TextureRegion(pauseTexture);
        TextureRegionDrawable pauseTexRegionDrawable = new TextureRegionDrawable(pauseTextureRegion);
        pauseButton = new PauseButton(pauseTexRegionDrawable);
        pauseButton.addListener(new PauseListener());
        pauseButton.toFront();
    }

    /**
     * Listener for pause button
     */
    private class PauseListener extends InputListener
    {
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
        {
            if (event.getListenerActor().equals(pauseButton) )
            {
                if (GameScreen.paused)
                {
                    GameScreen.paused = false;
                    Timer.instance().start();
                    pauseLabel.remove();
                } else
                {
                    GameScreen.paused = true;
                    Timer.instance().stop();
                    GameScreen.stage.addActor(pauseLabel);
                    pauseLabel.toFront();
                }
            }
            return true;
        }
    }
}
