package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.shared.DefComponents;

public class Hotdog extends AbstractItem
{
    public Hotdog(PlayGameState pgs, int starting_x, int velocity)
    {
        super(pgs, EntityTextures.get().hotdog, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.HOTDOG_WIDTH, Const.HOTDOG_HEIGHT);
        this.setSize(Const.HOTDOG_WIDTH, Const.HOTDOG_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(20,90);
    }

    @Override
    public void triggerEffect()
    {
        playGameState.player.makePlayerFat();
        playGameState.gameScreen.stage.addActor(new BouncingText("FAT", DefComponents.LABEL_STYLE,
                Effect.BAD, playGameState.player.getCoords()));
        playGameState.hud.indicatorControl.addFatIndicator();
        GameSound.instance().playFatFries();
    }
}
