package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.shared.DefComponents;

public class Coffee extends AbstractItem
{
    public Coffee(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().coffee, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.COFFEE_WIDTH, Const.COFFEE_HEIGHT);
        this.setSize(Const.COFFEE_WIDTH, Const.COFFEE_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(10,90);
    }

    @Override
    public void triggerEffect()
    {
        playGameState.player.coffeeBoost();
        playGameState.gameScreen.stage.addActor(new BouncingText("ENERGIZED", DefComponents.LABEL_STYLE,
                Effect.GOOD, playGameState.player.getCoords()));
        playGameState.hud.indicatorControl.addCoffeeIndicator();
        GameSound.instance().playCoffeeBoost();
    }
}
