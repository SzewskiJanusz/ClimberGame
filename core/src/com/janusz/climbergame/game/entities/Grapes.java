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

/**
 * Created by Janusz on 2018-02-13.
 */

public class Grapes extends AbstractItem
{
    public Grapes(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().grapes, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.GRAPES_WIDTH, Const.GRAPES_HEIGHT);
        this.setSize(Const.FRIES_WIDTH, Const.FRIES_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(10,250);
    }

    @Override
    public void triggerEffect()
    {
        int score = 400 + playGameState.difficultyControl.level * 100;
        playGameState.scoreMgr.ScoreLogic.addToScore(score);
        playGameState.gameScreen.stage.addActor(new BouncingText("+"+score, DefComponents.LABEL_STYLE,
                Effect.GOOD, playGameState.player.getCoords()));
        GameSound.instance().playGood();
    }
}
