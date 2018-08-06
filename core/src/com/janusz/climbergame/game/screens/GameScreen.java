package com.janusz.climbergame.game.screens;

import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.states.BeginningState;
import com.janusz.climbergame.game.states.GameState;

public class GameScreen extends com.janusz.climbergame.shared.AbstractScreen
{
    private GameState gameState;
    private long start = System.currentTimeMillis();
    private final int FPS = 60;

    public GameScreen(ClimberGame game)
    {
        super(game);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        gameState.tick(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
        sleep(FPS);
    }

    public void setState(GameState gs)
    {
        this.gameState = gs;
    }

    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
        spriteBatch.dispose();
    }

    protected void init()
    {
        this.setState(new BeginningState(this, game));
    }

    private void sleep(int fps)
    {
        long diff = System.currentTimeMillis() - start;
        long targetDelay = 1000/fps;
        if (diff < targetDelay) {
            try
            {
                Thread.sleep(targetDelay - diff);
            }
            catch (InterruptedException e)
            {
                //Do nothing
            }
        }
        start = System.currentTimeMillis();
    }
}



