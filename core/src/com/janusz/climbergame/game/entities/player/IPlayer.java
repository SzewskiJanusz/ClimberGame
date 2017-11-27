package com.janusz.climbergame.game.entities.player;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Janusz on 2017-11-27.
 */

interface IPlayer
{
    void jumpLeft();
    void jumpRight();
    Rectangle getBounds();
    boolean catchLiana();
}
