package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.shared.DefComponents;

import java.util.Random;

public class Apple extends AbstractItem
{
    public Apple(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().apple, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.APPLE_WIDTH, Const.APPLE_HEIGHT);
        this.setSize(Const.APPLE_WIDTH, Const.APPLE_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(30,300);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(direction * rotation * delta); // obracanie z każdą klatką
    }

    @Override
    public void triggerEffect()
    {
        playGameState.scoreMgr.ScoreLogic.addToScore(500);
        playGameState.gameScreen.stage.addActor(new BouncingText("+500", DefComponents.LABEL_STYLE,
                Effect.GOOD, playGameState.player.getCoords()));
        GameSound.instance().playGood();
    }
}
