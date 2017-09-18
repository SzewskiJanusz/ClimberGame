package com.janusz.climbergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.janusz.climbergame.screens.EventHandler;
import com.janusz.climbergame.screens.GameScreen;

public class ClimberGame extends Game {

	public static int WIDTH;
	public static int HEIGHT;

	private boolean paused;

	@Override
	public void create()
	{
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
        Gdx.input.setInputProcessor(new EventHandler());
		setScreen(new GameScreen(this));

	}

	/**
	 * Getters and setters
	 *
	 */

	public boolean isPaused()
	{
		return paused;
	}

	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}


}

