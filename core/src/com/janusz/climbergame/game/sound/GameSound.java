package com.janusz.climbergame.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by Janusz on 2018-03-01.
 */

public class GameSound
{
    private Sound deathSound;
    private Sound fruitSound;
    private Sound friesSound;
    private Sound coffeeSound;
    private Sound treasureSound;
    private Sound lvlUpSound;


    private static GameSound ins;

    public static GameSound instance()
    {
        if (ins == null)
        {
            ins = new GameSound();
        }

        return ins;
    }

    private GameSound()
    {
    }

    public void prepareSounds()
    {
        deathSound = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
        fruitSound = Gdx.audio.newSound(Gdx.files.internal("getgood.wav"));
        friesSound = Gdx.audio.newSound(Gdx.files.internal("getfries.wav"));
        coffeeSound = Gdx.audio.newSound(Gdx.files.internal("getcoffee.wav"));
        treasureSound = Gdx.audio.newSound(Gdx.files.internal("gettreasure.wav"));
        lvlUpSound = Gdx.audio.newSound(Gdx.files.internal("levelup.mp3"));
    }

    public void playDeath()
    {
        deathSound.play();
    }

    public void playGood()
    {
        fruitSound.play();
    }

    public void playFatFries()
    {
        friesSound.play();
    }

    public void playCoffeeBoost()
    {
        coffeeSound.play();
    }

    public void playTreasure()
    {
        treasureSound.play();
    }

    public void playLevelUp()
    {
        lvlUpSound.play();
    }

    public void dispose(){
        ins = null;
    }
}
