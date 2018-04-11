package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;

/**
 * Created by Janusz on 2017-12-06.
 */

public class AboutScreen extends AbstractScreen
{

    public AboutScreen(ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            game.setScreen(new MenuScreen(game,1f));
        }
        stage.draw();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
        spriteBatch.dispose();
        game.dispose();
    }

    @Override
    protected void init()
    {
        TextButton backToMenu = initButton();
        stage.addActor(backToMenu);

        LabelFactory lblFactory = new LabelFactory();
        Label about = lblFactory.createLabel("ABOUT",250,400,4.5f);
        stage.addActor(about);

        Label title = lblFactory.createLabel("FAST CLIMBER",230,310,2.5f);
        stage.addActor(title);

        Label version = lblFactory.createLabel("VERSION 1.0",290,280,1.5f);
        stage.addActor(version);

        Label designedIn = lblFactory.createLabel("DESIGNED IN POLAND",230,200,2f);
        stage.addActor(designedIn);

        Label contact = lblFactory.createLabel("CONTACT",290,150,1.5f);
        stage.addActor(contact);

        Label email = lblFactory.createLabel("ALEX.JANUSZEWSKI@INTERIA.PL",235,120,1.3f);
        stage.addActor(email);
    }


    private TextButton initButton()
    {
        TextButton tb = new TextButton("MENU", DefComponents.TEXTBUTTON_STYLE);
        tb.setPosition(260,50);
        tb.setWidth(200);
        tb.addListener(new MenuBackButtonListener());
        return tb;
    }

    private class MenuBackButtonListener extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            game.setScreen(new MenuScreen(game));
        }
    }

    private class LabelFactory
    {
        LabelFactory() {}
        Label createLabel(String text, int x, int y, float fontScale)
        {
            Label l = new Label(text,DefComponents.LABEL_STYLE_WHITE);
            l.setPosition(x,y);
            l.setFontScale(fontScale);
            l.setColor(Color.WHITE);
            return l;
        }
    }


}
