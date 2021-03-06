package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;

public class Stone extends AbstractItem
{
    public Stone(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().stone, starting_x, velocity);
        this.setName("bad");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.STONE_WIDTH, Const.STONE_HEIGHT);
        this.setSize(Const.STONE_WIDTH, Const.STONE_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(5 * delta);
    }

    @Override
    public void triggerEffect()
    {
        playGameState.deathAnimation = true;
        GameSound.instance().playDeath();
    }
}
