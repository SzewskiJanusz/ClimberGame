package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.Banana;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.managers.energy.EnergyManager;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefLabel;


public class BananaManager extends AbstractManager<Banana>
{
    public BananaManager()
    {
        super(3);
    }

    @Override
    protected void randomizeSpawnTime()
    {
        entitySpawnTime = MathUtils.random(3,7);
    }

    @Override
    protected void spawnEntity()
    {
        int x = selectPlace(MathUtils.random(1,4));
        Banana b = new Banana(new Texture("banana.png"), x, ClimberGame.HEIGHT, 45, 30, 215);
        entities.add(b);
        GameScreen.stage.addActor(b);
        randomizeSpawnTime();
    }

    @Override
    protected void triggerEffect()
    {
        EnergyManager.getInstance().energyBar.addEnergy();
        ScoreManager.getInstance().ScoreLogic.addToScore(500);
        GameScreen.stage.addActor(new BouncingText("+500", DefLabel.getDefaultLabelStyle(),
                Effect.GOOD));
    }
}
