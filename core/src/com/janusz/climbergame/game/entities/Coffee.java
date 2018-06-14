package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.shared.DefComponents;

public class Coffee extends AbstractItem
{
    public Coffee(int starting_x, int velocity)
    {
        super(EntityTextures.get().coffee, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.COFFEE_WIDTH, Const.COFFEE_HEIGHT);
        this.setSize(Const.COFFEE_WIDTH, Const.COFFEE_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(55 * delta);
    }

    @Override
    public void triggerEffect()
    {
        Player.instance().coffeeBoost();
        GameScreen.stage.addActor(new BouncingText("ENERGIZED", DefComponents.LABEL_STYLE,
                Effect.GOOD));
        IndicatorController.instance().addCoffeeIndicator();
        GameSound.instance().playCoffeeBoost();
    }
}
