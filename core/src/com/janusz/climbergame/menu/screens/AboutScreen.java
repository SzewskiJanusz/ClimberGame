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
        Label about = lblFactory.createLabel("ABOUT",250,400,0.9f);
        stage.addActor(about);

        Label title = lblFactory.createLabel("FAST CLIMBER",30,310,0.5f);
        stage.addActor(title);

        Label version = lblFactory.createLabel("VERSION 1.2",90,280,0.3f);
        stage.addActor(version);

        Label designedIn = lblFactory.createLabel("DESIGNED IN POLAND",320,220,0.4f);
        stage.addActor(designedIn);

        Label contact = lblFactory.createLabel("CONTACT",380,170,0.3f);
        stage.addActor(contact);

        Label email = lblFactory.createLabel("ALEX.JANUSZEWSKI@INTERIA.PL",325,140,0.25f);
        stage.addActor(email);
    }


    private TextButton initButton()
    {
        TextButton tb = new TextButton("MENU", DefComponents.TEXTBUTTON_STYLE);
        tb.setPosition(260,50);
        tb.setSize(175,65);
        tb.getLabel().setFontScale(0.6f);
        tb.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new MenuScreen(game));
            }
        }
        );
        return tb;
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
