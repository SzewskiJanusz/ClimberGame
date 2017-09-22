package com.janusz.climbergame.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.entities.Banana;
import com.janusz.climbergame.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;


public class BananaController
{

    private List<Banana> bananas;
    private float bananaSpawnTime;

    public BananaController()
    {
        bananas = new ArrayList<Banana>();
        randomizeBananaSpawnTime();
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               spawnBanana();
                           }
                       }
                , 3       //    (delay)
                , bananaSpawnTime
        );

    }

    private void randomizeBananaSpawnTime()
    {
        bananaSpawnTime = MathUtils.random(3,7);
    }

    public void updateAllBananas(float delta)
    {

        for (int i = 0 ; i < bananas.size() ; i++)
        {
            if (checkCollisionWithBanana(i))
            {
                bananas.get(i).remove();
                bananas.remove(i);
                break;
            }
        }

        for(Banana b : bananas)
        {
            b.update(delta);
        }

    }

    private boolean checkCollisionWithBanana(int i)
    {
        boolean playerEatBanana = Intersector.overlaps(bananas.get(i).getBounds(),
                GameScreen.player.getBounds());

        return playerEatBanana;
    }

    private void spawnBanana()
    {
        int x = GameScreen.selectPlace(MathUtils.random(1,4));
        Banana b = new Banana(new Texture("banana.png"), x);
        bananas.add(b);
        GameScreen.stage.addActor(b);
        randomizeBananaSpawnTime();
    }

}
