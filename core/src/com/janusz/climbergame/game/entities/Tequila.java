package com.janusz.climbergame.game.entities;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.EntityTextures;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.shared.DefComponents;

public class Tequila extends AbstractItem
{
    public Tequila(PlayGameState gs, int starting_x, int velocity)
    {
        super(gs, EntityTextures.get().tequila, starting_x, velocity);
        this.setName("good");
        bounds = new Rectangle(starting_x, ClimberGame.HEIGHT,
                Const.TEQUILA_WIDTH, Const.TEQUILA_HEIGHT);
        this.setSize(Const.TEQUILA_WIDTH, Const.TEQUILA_HEIGHT);
        this.setOrigin(getWidth() / 2, getHeight() / 2);
        rotation = MathUtils.random(15,50);
    }

    @Override
    protected void doMovement(float delta)
    {
        this.rotateBy(direction * rotation * delta); // obracanie z każdą klatką
    }

    @Override
    public void triggerEffect()
    {
        playGameState.gameScreen.stage.addActor(new BouncingText("DRUNK", DefComponents.LABEL_STYLE,
                Effect.BAD, playGameState.player.getCoords()));
        playGameState.player.makePlayerDrunk();
        playGameState.hud.indicatorControl.addDrunkIndicator();
        GameSound.instance().playFatFries();
    }
}
