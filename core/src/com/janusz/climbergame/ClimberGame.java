package com.janusz.climbergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.janusz.climbergame.game.screens.SaveScoreScreen;
import com.janusz.climbergame.game.screens.SplashScreen;
import com.janusz.climbergame.menu.screens.MenuScreen;

public class ClimberGame extends Game {

	public static int WIDTH = 700;
	public static int HEIGHT = 480;

	private boolean paused;

	@Override
	public void create()
	{
		setScreen(new MenuScreen(this));
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

