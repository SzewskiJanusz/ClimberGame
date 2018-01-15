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
        if (Player.instance().playerState == PlayerState.CLIMBING_LIANA)
        {
            Vector2 actorScreenPos = translateLocalToStageCoordinates();

            if (isFingerOnLeft(screenX, actorScreenPos.x))
            {
                if (!Player.drunk)
                    Player.instance().jumpLeft();
                else
                    Player.instance().jumpRight();
            }
            else
            {
                if (!Player.drunk)
                    Player.instance().jumpRight();
                else
                    Player.instance().jumpLeft();
            }
        }
        return true;
    }

    private Vector2 translateLocalToStageCoordinates()
    {
        Vector2 actorStagePos = Player.instance().localToStageCoordinates(new Vector2(Player.instance().getWidth() / 2,0));
        return Player.instance().getStage().stageToScreenCoordinates(actorStagePos);
    }

    private boolean isFingerOnLeft(int screenX, float playerOnScreenX)
    {
        return playerOnScreenX > screenX;
    }


}
