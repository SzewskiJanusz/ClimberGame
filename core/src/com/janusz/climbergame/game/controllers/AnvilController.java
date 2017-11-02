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


public class AnvilController extends AbstractController<Anvil>
{

    public AnvilController()
    {
        super(5);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(4,6);
    }

    @Override
    protected void spawnEntity()
    {
        int x = GameScreen.selectPlace(MathUtils.random(2,4));
        Anvil a = new Anvil(new Texture("anvil.png"), x, ClimberGame.HEIGHT, 80, 55, 500);
        entities.add(a);
        GameScreen.stage.addActor(a);
        randomizeSpawnTime();
    }
}
