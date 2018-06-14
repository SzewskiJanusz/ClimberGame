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


public class Fries extends AbstractItem
{
    private final int width = 80;
    private final int height = 55;

    public Fries(int starting_x, int velocity)
    {
        super(EntityTextures.get().fries, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.FRIES_WIDTH, Const.FRIES_HEIGHT);
        this.setSize(Const.FRIES_WIDTH, Const.FRIES_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void triggerEffect()
    {
        Player.instance().makePlayerFat();
        GameScreen.stage.addActor(new BouncingText("FAT", DefComponents.LABEL_STYLE,
                Effect.BAD));
        IndicatorController.instance().addFatIndicator();
        GameSound.instance().playFatFries();
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(45 * delta);
    }



}
