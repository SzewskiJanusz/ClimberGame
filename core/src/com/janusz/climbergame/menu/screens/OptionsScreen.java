package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.AbstractScreen;

/**
 * Created by Janusz on 2017-12-06.
 */

public class OptionsScreen extends AbstractScreen
{
    private TextButton.TextButtonStyle bs;

    public OptionsScreen(ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void init()
    {
        initButtonStyle();
        Slider.SliderStyle st = new Slider.SliderStyle();
        Slider s = new Slider(0,3,1,false,st);
        s.setValue(0);
        s.setPosition(100,200);
        s.setSize(50,200);
        stage.addActor(s);
    }
    private void initButtonStyle()
    {
        Slider.SliderStyle s = new Slider.SliderStyle();

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = new BitmapFont();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
    }
}
