package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.graphics.Texture;
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

    public Banana(Texture text,int starting_x, int starting_y, int width, int height,int velocity)
    {
        super(text, starting_x, starting_y, width, height, velocity);
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
