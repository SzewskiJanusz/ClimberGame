package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.BeginningHandler;
import com.janusz.climbergame.game.GameMode;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.ButtonFactory;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2018-05-26.
 */

public class ModeSelectScreen extends AbstractScreen
{
    private Title title;
    private JungleBackground gameBackground;

    public ModeSelectScreen(ClimberGame game)
    {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void init()
    {
        initBackground();
        initTitle();
        initButtons();
    }

    private void initBackground()
    {
        gameBackground = new JungleBackground();
        stage.addActor(gameBackground);
    }

    private void initTitle()
    {
        title = new Title();
        stage.addActor(title);
    }

    private void initButtons()
    {
        initNormalButton();
        initHardcoreButton();
        initBackButton();
    }

    private void initNormalButton()
    {
        TextButton normal = ButtonFactory.createButton
                ("NORMAL", DefComponents.TEXTBUTTON_STYLE, 320);
        normal.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen gs = new GameScreen(game, GameMode.NORMAL);
                Gdx.input.setInputProcessor(new BeginningHandler(gs));
                game.setScreen(gs);
            }
        });
        stage.addActor(normal);
    }

    private void initHardcoreButton()
    {
        TextButton hardcore = ButtonFactory.createButton
                ("HARDCORE", DefComponents.TEXTBUTTON_STYLE, 220);
        hardcore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
//                GameScreen gs = new GameScreen(game, GameMode.HARDCORE);
//                Gdx.input.setInputProcessor(new BeginningHandler(gs));
//                game.setScreen(gs);
            }
        });
        stage.addActor(hardcore);
    }

    private void initBackButton()
    {
        TextButton back = ButtonFactory.createButton("BACK", DefComponents.TEXTBUTTON_STYLE, 40);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(back);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }
}
