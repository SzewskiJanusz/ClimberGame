package com.janusz.climbergame.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.entities.player.PlayerState;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-09-18.
 */

public class EventHandler extends InputAdapter
{
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if (Player.playerState == PlayerState.CLIMBING_LIANA ||
                Player.playerState == PlayerState.CLIMBING_WALL)
        {
            Vector2 actorScreenPos = translateLocalToStageCoordinates();

            if (isFingerOnLeft(screenX, actorScreenPos.x))
            {
                if (!Player.drunk)
                    GameScreen.player.jumpLeft();
                else
                    GameScreen.player.jumpRight();
            }
            else
            {
                if (!Player.drunk)
                    GameScreen.player.jumpRight();
                else
                    GameScreen.player.jumpLeft();
            }
        }
        return true;
    }

    private Vector2 translateLocalToStageCoordinates()
    {
        Vector2 actorStagePos = GameScreen.player.localToStageCoordinates(new Vector2(GameScreen.player.getWidth() / 2,0));
        return GameScreen.player.getStage().stageToScreenCoordinates(actorStagePos);
    }

    private boolean isFingerOnLeft(int screenX, float playerOnScreenX)
    {
        return playerOnScreenX > screenX;
    }


}
