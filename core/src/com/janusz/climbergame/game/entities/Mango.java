package com.janusz.climbergame.game.entities;

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

public class Mango extends AbstractItem
{
    public Mango(PlayGameState pgs, int starting_x, int velocity)
    {
        super(pgs, EntityTextures.get().mango, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.MANGO_WIDTH, Const.MANGO_HEIGHT);
        this.setSize(Const.MANGO_WIDTH, Const.MANGO_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(5,300);
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
