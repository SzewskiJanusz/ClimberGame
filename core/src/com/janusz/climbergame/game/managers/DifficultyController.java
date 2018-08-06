package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.math.MathUtils;
import com.janusz.climbergame.game.spawners.ItemSpawner;
import com.janusz.climbergame.game.environment.BouncingText;
import com.janusz.climbergame.game.environment.Effect;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.states.PlayGameState;
import com.janusz.climbergame.shared.DefComponents;

public class DifficultyController
{
    public int levelVelocity;
    public float difficultyTimer;
    public int level;
    PlayGameState playGameState;
    public ItemSpawner itemSpawner;


    public DifficultyController(PlayGameState pgs)
    {
        this.playGameState = pgs;
        itemSpawner = new ItemSpawner(playGameState);
        level = 1;
    }

    public void difficultyUpdate(float delta)
    {
        if (isTimeForLvlUp())
        {
            level++;
            playGameState.hud.lvlTexts.levelNumber.setText(String.valueOf(level));
            levelVelocity += 30 + level*3;
            difficultyTimer = 0;
            playGameState.gameScreen.stage.addActor(
                    new BouncingText("LEVEL UP!",
                            DefComponents.LABEL_STYLE,
                            Effect.DEFAULT, playGameState.player.getCoords()));
            GameSound.instance().playLevelUp();
            itemSpawner.spawnWaves(10 + MathUtils.random(0,5) + level);
        }
        else
        {
            difficultyTimer += delta;
        }
    }

    private boolean isTimeForLvlUp()
    {
        return difficultyTimer >= 20 + level;
    }
}
