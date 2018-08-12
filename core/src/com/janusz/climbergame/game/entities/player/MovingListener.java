package com.janusz.climbergame.game.entities.player;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.janusz.climbergame.game.managers.pause.PauseController;

public class MovingListener extends InputListener
{
    Player player;
    PauseController pauseController;

    public MovingListener(Player player, PauseController pauseC)
    {
        this.player = player;
        this.pauseController = pauseC;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        if (pauseController.pauseClicked)
        {
            pauseController.pauseClicked = false;
        }
        else if (player.playerState == PlayerState.CLIMBING_LIANA && !pauseController.isGamePaused())
        {
            if (isFingerOnLeft(x, player.getX() + player.getWidth() / 2) ^ Player.drunk)
            {
                player.jumpLeft();
            }
            else
            {
                player.jumpRight();
            }
        }
        return true;
    }

    private boolean isFingerOnLeft(float screenX, float playerOnScreenX)
    {
        return playerOnScreenX > screenX;
    }
}
