package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Bartek on 2017-10-20.
 *
 * Banan
 */
public class Banana extends AbstractItem
{
    public Banana(int starting_x, int velocity)
    {
        super(EntityTextures.get().banana, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.BANANA_WIDTH, Const.BANANA_HEIGHT);
        this.setSize(Const.BANANA_WIDTH, Const.BANANA_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(360 * delta); // obracanie z każdą klatką
    }

    @Override
    public void triggerEffect()
    {
        ScoreManager.getInstance().ScoreLogic.addToScore(500);
        GameScreen.stage.addActor(new BouncingText("+500", DefComponents.LABEL_STYLE,
                Effect.GOOD));
        GameSound.instance().playGood();
    }
}
