package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;

/**
 * Created by Janusz on 2017-09-20.
 */

public class Anvil extends AbstractItem
{
    public Anvil(int starting_x, int velocity)
    {
        super(EntityTextures.get().anvil, starting_x, velocity);
        this.setName("bad");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.ANVIL_WIDTH, Const.ANVIL_HEIGHT);
        this.setSize(Const.ANVIL_WIDTH, Const.ANVIL_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void triggerEffect()
    {
        GameScreen.deathAnimation = true;
        GameSound.instance().playDeath();
    }

    protected void doMovement(float delta)
    {
        this.rotateBy(20 * delta);
    }

}
