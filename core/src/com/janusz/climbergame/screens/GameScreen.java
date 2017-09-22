package com.janusz.climbergame.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private Label gameOverLabel;

    private Label yourFinalScoreTextLabel;

    private Label yourBestScoreTextLabel;

    private Label yourBestScoreLabel;

    private Label yourFinalScoreLabel;

    private Button mainMenu;

    private Button tryAgain;

    private boolean gameOver;



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
        createGameOverLabels();

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

        checkIfGameOver();

    }

    private void checkIfGameOver()
    {
        if (gameOver)
        {
            stage.addActor(gameOverLabel);
            stage.addActor(yourFinalScoreTextLabel);
            stage.addActor(yourFinalScoreLabel);
            stage.addActor(yourBestScoreTextLabel);
            stage.addActor(yourBestScoreLabel);
        }
    }

    private void createGameOverLabels()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;

        gameOverLabel = new Label("GAME OVER", ls);
        gameOverLabel.setFontScale(5);
        gameOverLabel.setPosition(ClimberGame.WIDTH / 2 - gameOverLabel.getWidth(),
                ClimberGame.HEIGHT / 2 + 3 * gameOverLabel.getHeight() , 1);


        yourFinalScoreTextLabel = new Label("SCORE ", ls);
        yourFinalScoreTextLabel.setFontScale(3);
        yourFinalScoreTextLabel.setPosition(ClimberGame.WIDTH / 2 - gameOverLabel.getWidth() +
                yourFinalScoreTextLabel.getWidth(),
                ClimberGame.HEIGHT / 2 , 1);

        yourFinalScoreLabel = new Label("0", ls);
        yourFinalScoreLabel.setFontScale(4);
        yourFinalScoreLabel.setPosition(ClimberGame.WIDTH / 2 - gameOverLabel.getWidth() +
                        yourFinalScoreLabel.getWidth(),
                ClimberGame.HEIGHT / 2  - 2 * gameOverLabel.getHeight(), 1);

        yourBestScoreTextLabel = new Label("BEST ", ls);
        yourBestScoreTextLabel.setFontScale(3);
        yourBestScoreTextLabel.setPosition(ClimberGame.WIDTH / 2 - gameOverLabel.getWidth() +
                        yourBestScoreTextLabel.getWidth(),
                ClimberGame.HEIGHT / 2 - 4 * gameOverLabel.getHeight()
                , 1);

        yourBestScoreLabel = new Label("1000", ls);
        yourBestScoreLabel.setFontScale(4);
        yourBestScoreLabel.setPosition(ClimberGame.WIDTH / 2 - gameOverLabel.getWidth() +
                        yourBestScoreLabel.getWidth(),
                ClimberGame.HEIGHT / 2 - 6 * gameOverLabel.getHeight(), 1);



    }


    private void updateAllAnvils(float delta)
    {

        for (int i = 0 ; i < anvils.size() ; i++)
        {
            if (checkCollisionwithAnvil(i))
            {
                anvils.get(i).remove();
                anvils.remove(i);
                gameOver = true;
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



