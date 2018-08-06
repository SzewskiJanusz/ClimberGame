package com.janusz.climbergame.game.managers.pause;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
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
    private com.janusz.climbergame.game.states.PlayGameState playGameState;
    private boolean paused;
    public boolean pauseClicked;


    public void showLabel()
    {
        pauseLabel.toFront();
    }

    public void hideLabel()
    {
        pauseLabel.toBack();
    }

    public boolean isGamePaused()
    {
        return paused;
    }

    public PauseController(com.janusz.climbergame.game.states.PlayGameState playGameState)
    {
        this.pauseClicked = false;
        this.playGameState = playGameState;
        createButton();
        createLabel();
    }

    public void resumeGame()
    {
        paused = false;
        Timer.instance().start();
        pauseLabel.setVisible(false);
    }

    public void pauseGame()
    {
        paused = true;
        Timer.instance().stop();
        pauseLabel.setVisible(true);
        pauseLabel.toFront();
    }

    private void createLabel()
    {
        String text = "PAUSED";
        pauseLabel = new PauseLabel(text, DefComponents.LABEL_STYLE);
        pauseLabel.setVisible(false);
    }

    private void createButton()
    {
        Texture pauseTexture = new Texture(Gdx.files.internal("pausebtn.png"));
        TextureRegion pauseTextureRegion = new TextureRegion(pauseTexture);
        TextureRegionDrawable pauseTexRegionDrawable = new TextureRegionDrawable(pauseTextureRegion);
        pauseButton = new PauseButton(pauseTexRegionDrawable);
        pauseButton.addListener(new PauseListener());
        pauseButton.toFront();
        pauseButton.setVisible(false);
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
                if (paused)
                {
                    resumeGame();
                }
                else
                {
                    pauseGame();
                }
            }
            return true;
        }
    }

}
