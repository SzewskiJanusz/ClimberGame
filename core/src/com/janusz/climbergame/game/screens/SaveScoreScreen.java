package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.menu.screens.OptionsScreen;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.scoreclient.ServerConnection;

/**
 * Created by Janusz on 2017-12-07.
 */

public class SaveScoreScreen extends AbstractScreen
{
    private TextField tf;

    public SaveScoreScreen(ClimberGame game)
    {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void init()
    {
        initLabel();
        initTextfield();
        initButton();
    }

    private void initLabel()
    {
        Label l = new Label("ENTER NICKNAME", DefComponents.LABEL_STYLE);
        l.setFontScale(4f);
        l.setPosition(125,300);
        stage.addActor(l);
    }

    private void initTextfield()
    {
        tf = new TextField("", DefComponents.TEXTFIELD_STYLE);
        tf.setPosition(250,200);
        tf.setSize(200,50);
        tf.debug();
        stage.addActor(tf);
    }

    private void initButton()
    {
        TextButton tb = new TextButton("OK",DefComponents.TEXTBUTTON_STYLE);
        tb.setPosition(310,100);
        tb.setSize(100,50);
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if (tf.getText().length() > 0 && tf.getText().length() < 10)
                {
                    new ServerConnection().sendScoreToServer(tf.getText(),
                            ScoreManager.getInstance().ScoreLogic.getScore());

                    game.setScreen(new MenuScreen(game));
                }
            }
        });

        stage.addActor(tb);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
    }
}
