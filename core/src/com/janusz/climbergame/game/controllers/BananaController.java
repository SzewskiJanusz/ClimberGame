package com.janusz.climbergame.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.AbstractItem;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.List;


public class BananaController extends AbstractController<Banana>
{
    public BananaController()
    {
        super(3);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(3,7);
    }

    @Override
    protected void spawnEntity()
    {
        int x = GameScreen.selectPlace(MathUtils.random(1,4));
        Banana b = new Banana(new Texture("banana.png"), x, ClimberGame.HEIGHT, 45, 30, 215);
        entities.add(b);
        GameScreen.stage.addActor(b);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        EnergyBar.get().addEnergy();
    }
}
