package com.janusz.climbergame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.DefComponents;

public class ClimberGame extends Game implements AdService
{

	public static int WIDTH = 700;
	public static int HEIGHT = 480;

	private boolean paused;

	public AdService adService;

	public ClimberGame(AdService ads){
		adService=ads;
	}

	@Override
	public void create()
	{
		DefComponents.createFonts();
        DefComponents.prepareStyles();
		Gdx.input.setCatchBackKey(true);
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

	@Override
	public void dispose()
	{
		super.dispose();
		GameSound.dispose();
		EntityTextures.get().dispose();
		DefComponents.dispose();
	}

	@Override
	public boolean isInterstitialLoaded()
	{
		return false;
	}

	@Override
	public void showInterstitial()
	{}

	@Override
	public void loadIntersitialAd()
	{}
}

