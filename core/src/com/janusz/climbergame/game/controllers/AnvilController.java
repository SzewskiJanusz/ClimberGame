package com.janusz.climbergame.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Anvil;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;


public class AnvilController
{

    private List<Anvil> anvils;
    private float anvilSpawnTime;


    public AnvilController()
    {
        anvils = new ArrayList<Anvil>();
        randomizeAnvilSpawnTime();


        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               spawnAnvil();
                           }
                       }
                , 5       //    (delay)
                , anvilSpawnTime
        );
    }

    private void spawnAnvil()
    {
        int x = GameScreen.selectPlace(MathUtils.random(2,4));
        Anvil a = new Anvil(new Texture("anvil.png"), x, ClimberGame.HEIGHT, 80, 55, 500);
        anvils.add(a);
        GameScreen.stage.addActor(a);
        randomizeAnvilSpawnTime();
    }

    private void randomizeAnvilSpawnTime()
    {
        anvilSpawnTime = MathUtils.random(6,12);
    }

    public void updateAllAnvils(float delta)
    {

        for (int i = 0 ; i < anvils.size() ; i++)
        {
            if (checkCollisionWithAnvil(i))
            {
                anvils.get(i).remove();
                anvils.remove(i);
                GameScreen.gameOver = true;
                break;
            }
        }

        for(Anvil a : anvils)
        {
            a.update(delta);
        }
    }

    private boolean checkCollisionWithAnvil(int i)
    {
        return Intersector.overlaps(
                anvils.get(i).getBounds(),
                GameScreen.player.getBounds()
        );
    }

}
