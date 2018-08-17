package com.janusz.climbergame.game.environment;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.entities.LianaTile;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janusz on 2017-09-18.
 */

public class EntireLiana
{
    public static final int first_liana_x = Const.LIANAMANAGER_FIRST_X;
    public static final int second_liana_x = Const.LIANAMANAGER_SECOND_X;
    public static final int third_liana_x = Const.LIANAMANAGER_THIRD_X;

    // Lists for moving effect
    private List<LianaTile> first_wholeLiana;
    private List<LianaTile> second_wholeLiana;
    private List<LianaTile> third_wholeLiana;

    private GameScreen gameScreen;
    public static int lianaAmount;

    public EntireLiana(GameScreen gs)
    {
        gameScreen = gs;
        first_wholeLiana = new ArrayList<LianaTile>();
        second_wholeLiana = new ArrayList<LianaTile>();
        third_wholeLiana = new ArrayList<LianaTile>();

        createStartingLianas();
        createLianaTile();
        lianaAmount = first_wholeLiana.size();
    }

    private void createStartingLianas()
    {
        for (int i = 0 ; i < 5 ; i++)
        {
            LianaTile a = new LianaTile(first_liana_x, Const.LIANATILE_HEIGHT * i, MathUtils.random(0,1));
            LianaTile b = new LianaTile(second_liana_x, Const.LIANATILE_HEIGHT * i, MathUtils.random(0,1));
            LianaTile c = new LianaTile(third_liana_x, Const.LIANATILE_HEIGHT * i, MathUtils.random(0,1));
            a.toFront();
            b.toFront();
            c.toFront();
            first_wholeLiana.add(a);
            second_wholeLiana.add(b);
            third_wholeLiana.add(c);

            gameScreen.stage.addActor(a);
            gameScreen.stage.addActor(b);
            gameScreen.stage.addActor(c);
        }
    }

    private void createLianaTile()
    {
        LianaTile l = new LianaTile(first_liana_x, MathUtils.random(0,1));
        first_wholeLiana.add(l);
        gameScreen.stage.addActor(l);

        LianaTile l1 = new LianaTile(second_liana_x, MathUtils.random(0,1));
        second_wholeLiana.add(l1);
        gameScreen.stage.addActor(l1);

        LianaTile l2 = new LianaTile(third_liana_x, MathUtils.random(0,1));
        third_wholeLiana.add(l2);
        gameScreen.stage.addActor(l2);
    }


    public void moveAllLianasDown(float delta)
    {
        first_wholeLiana.get(0).moveDown(delta);
        second_wholeLiana.get(0).moveDown(delta);
        third_wholeLiana.get(0).moveDown(delta);

        for (int i = 1 ; i < first_wholeLiana.size() ;i ++)
        {
            first_wholeLiana.get(i).stickLianaToAnother(first_wholeLiana.get(i-1));
            second_wholeLiana.get(i).stickLianaToAnother(second_wholeLiana.get(i-1));
            third_wholeLiana.get(i).stickLianaToAnother(third_wholeLiana.get(i-1));
        }

        checkIfNeedCreateTile();
        checkIfNeedDisposeLastTile();
    }

    private void checkIfNeedCreateTile()
    {
        int lastTile = first_wholeLiana.size() - 1;

        if (first_wholeLiana.get(lastTile).getY() + Const.LIANATILE_HEIGHT <= LianaTile.STARTING_Y )
        {
            createLianaTile();
        }
    }

    private void checkIfNeedDisposeLastTile()
    {
        if (first_wholeLiana.get(0).getY() + Const.LIANATILE_HEIGHT <= 0)
        {
            disposeLastLianaTile();
        }
    }


    private void disposeLastLianaTile()
    {
        first_wholeLiana.get(0).remove();
        first_wholeLiana.remove(0);

        second_wholeLiana.get(0).remove();
        second_wholeLiana.remove(0);

        third_wholeLiana.get(0).remove();
        third_wholeLiana.remove(0);
    }

    public LianaTile getLianaTileFromFirst(int i)
    {
        return first_wholeLiana.get(i);
    }

    public LianaTile getLianaTileFromSecond(int i)
    {
        return second_wholeLiana.get(i);
    }

    public LianaTile getLianaTileFromThird(int i)
    {
        return third_wholeLiana.get(i);
    }

    public int getSize()
    {
        return first_wholeLiana.size();
    }

    public void shake(int place, int previous)
    {
        for (int i = 0 ; i < first_wholeLiana.size() ; i++)
        {
            switch(place)
            {
                case 1: first_wholeLiana.get(i).shakeAfterJump(i, place - previous); break;
                case 2: second_wholeLiana.get(i).shakeAfterJump(i, place - previous); break;
                case 3: third_wholeLiana.get(i).shakeAfterJump(i, place - previous); break;
            }
        }
    }
}
