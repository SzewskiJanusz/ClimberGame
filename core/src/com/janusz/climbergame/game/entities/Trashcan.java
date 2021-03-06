package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;

public class Trashcan extends AbstractItem
{
    public Trashcan(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().trashcan, starting_x, velocity);
        this.setName("bad");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.TRASHCAN_WIDTH, Const.TRASHCAN_HEIGHT);
        this.setSize(Const.TRASHCAN_WIDTH, Const.TRASHCAN_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(5,50);
    }

    @Override
    public void triggerEffect()
    {
        playGameState.deathAnimation = true;
        GameSound.instance().playDeath();
    }
}
