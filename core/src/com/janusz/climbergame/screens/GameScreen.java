package com.janusz.climbergame.screens;


import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.entities.Liana;


/**
 * Created by Janusz on 2017-08-31.
 */

public class GameScreen extends AbstractScreen
{

    private Liana liana;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    protected void init()
    {
        initLiana();
    }

    private void initLiana()
    {
        liana = new Liana();
        stage.addActor(liana);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        update();
        liana.moveLianaDown(delta);

        spriteBatch.begin();

        stage.draw();

        spriteBatch.end();
    }

    private void update()
    {
        stage.act();
    }


}



