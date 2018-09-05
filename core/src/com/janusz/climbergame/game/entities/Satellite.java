package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;

public class Satellite extends AbstractItem
{
    public Satellite(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().satelite, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.SATELLITE_WIDTH, Const.SATELLITE_HEIGHT);
        this.setSize(Const.SATELLITE_WIDTH, Const.SATELLITE_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void triggerEffect()
    {
        playGameState.deathAnimation = true;
        GameSound.instance().playDeath();
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(-10 * delta); // obracanie z każdą klatką
    }
}
