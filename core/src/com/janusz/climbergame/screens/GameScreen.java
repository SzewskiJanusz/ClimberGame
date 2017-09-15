package com.janusz.climbergame.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.Player;


/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    private Player player;

    public GameScreen (ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initPlayer();
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
        update();


        spriteBatch.begin();

        stage.draw();

        spriteBatch.end();
    }

    private void update()
    {
        stage.act();
    }
}

