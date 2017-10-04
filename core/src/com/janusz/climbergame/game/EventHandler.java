package com.janusz.climbergame.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.janusz.climbergame.game.entities.Player;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-09-18.
 */

public class EventHandler extends InputAdapter
{
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {


        Vector2 actorStagePos = GameScreen.player.localToStageCoordinates(new Vector2(0,0));
        Vector2 actorScreenPos = GameScreen.player.getStage().
                stageToScreenCoordinates(actorStagePos);

        System.out.println(screenX +" "+ screenY + "PlayerX = "+
               actorScreenPos.x);

        if (isFingerOnLeft(screenX, actorScreenPos.x))
        {
            GameScreen.player.jumpLeft();
        }
        else
        {
            GameScreen.player.jumpRight();
        }

        return true;
    }

    private boolean isFingerOnLeft(int screenX,float playerOnScreenX)
    {
        return playerOnScreenX > screenX;
    }


}
