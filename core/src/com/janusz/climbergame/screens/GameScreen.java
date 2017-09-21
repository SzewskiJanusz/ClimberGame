package com.janusz.climbergame.screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.Anvil;
import com.janusz.climbergame.entities.Banana;
import com.janusz.climbergame.entities.Player;
import com.janusz.climbergame.entities.Wall;
import com.janusz.climbergame.environment.EntireLiana;
import com.janusz.climbergame.environment.EntireWall;

import java.util.ArrayList;
import java.util.List;


public class GameScreen extends AbstractScreen
{


    private EntireLiana el;
    private EntireWall ew;

    public static Player player;

    private List<Banana> bananas;

    private List<Anvil> anvils;

    private float bananaSpawnTime;

    private float anvilSpawnTime;


    public GameScreen(ClimberGame game)
    {
        super(game);

        randomizeBananaSpawnTime();

        randomizeAnvilSpawnTime();
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
        int x = selectPlace(MathUtils.random(2,4));
        Anvil a = new Anvil(new Texture("anvil.png"), x);
        anvils.add(a);
        stage.addActor(a);
        randomizeAnvilSpawnTime();
    }

    private void randomizeAnvilSpawnTime()
    {
        anvilSpawnTime = MathUtils.random(6,12);
    }

    private void spawnBanana()
    {
        int x = selectPlace(MathUtils.random(1,4));
        Banana b = new Banana(new Texture("banana.png"), x);
        bananas.add(b);
        stage.addActor(b);
        randomizeBananaSpawnTime();
    }

    private int selectPlace(int place)
    {
        switch (place)
        {
            case 1: return Wall.WIDTH;
            case 2: return EntireLiana.first_liana_x;
            case 3: return EntireLiana.second_liana_x;
            case 4: return EntireLiana.third_liana_x;
            default: return 0;
        }
    }



    private void randomizeBananaSpawnTime()
    {
        bananaSpawnTime = MathUtils.random(3,7);
    }

    protected void init()
    {
        initPlayer();
        el = new EntireLiana();
        ew = new EntireWall();
        bananas = new ArrayList<Banana>();
        anvils = new ArrayList<Anvil>();

    }

    private void initPlayer()
    {
        player = new Player();
        stage.addActor(player);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update(delta);


        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private boolean checkCollisionWithBanana(int i)
    {
        boolean playerEatBanana = Intersector.overlaps(bananas.get(i).getBounds(), player.getBounds());

        return playerEatBanana;
    }



    private void movePlayer()
    {
        switch(player.place)
        {
            case 0: player.setX(Wall.WIDTH); break;
            case 1: player.setX(EntireLiana.first_liana_x); break;
            case 2: player.setX(EntireLiana.second_liana_x); break;
            case 3: player.setX(EntireLiana.third_liana_x); break;
            default: throw new IllegalArgumentException("Error in player place. (" + player.place +")");
        }
    }

    private void update(float delta)
    {
        stage.act();
        player.toFront();
        movePlayer();

        ew.moveWallDown(delta);
        el.moveAllLianasDown(delta);

        updateAllBananas(delta);
        updateAllAnvils(delta);

    }

    private void updateAllAnvils(float delta)
    {

        for (int i = 0 ; i < anvils.size() ; i++)
        {
            if (checkCollisionwithAnvil(i))
            {
                anvils.get(i).remove();
                anvils.remove(i);
                break;
            }
        }

        for(Anvil a : anvils)
        {
            a.update(delta);
        }
    }

    private boolean checkCollisionwithAnvil(int i)
    {
        boolean playerHitAnvil = Intersector.overlaps(anvils.get(i).getBounds(), player.getBounds());

        return playerHitAnvil;

    }

    private void updateAllBananas(float delta)
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

}



