package com.janusz.climbergame.game.environment;

import com.janusz.climbergame.game.entities.Wall;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-09-18.
 *
 * Wall that moves.
 *
 * List named „walls” contains all wall pieces which are moved to bottom.
 *
 */

public class EntireWall
{
    // Walls for moving effect
    private List<Wall> walls;

    public EntireWall()
    {
        walls = new ArrayList<Wall>();

        // Starter wall. Spawns on entire height of screen
        Wall w = new Wall(true);
        walls.add(w);
        GameScreen.stage.addActor(w);
    }

    public void moveWallDown(float delta)
    {
        walls.get(0).moveWall(delta);

        for (int i = 1 ; i < walls.size() ; i++)
        {
            walls.get(i).stickToWall(walls.get(i-1));
        }

        checkIfNeedCreateWall();
        checkIfNeedDisposeWall();
    }

    private void checkIfNeedCreateWall()
    {
        int lastWall = walls.size() - 1;

        if (walls.get(lastWall).getY() <= Wall.STARTING_Y )
        {
            createNewWall();
        }
    }

    private void createNewWall()
    {
        Wall w = new Wall(false);
        walls.add(w);
        GameScreen.stage.addActor(w);
    }

    private void checkIfNeedDisposeWall()
    {
        if (walls.get(0).getY() + Wall.HEIGHT <= Wall.STARTING_Y)
        {
            disposeLastWall();
        }
    }

    private void disposeLastWall()
    {
        walls.get(0).remove();
        walls.remove(0);
    }

}
