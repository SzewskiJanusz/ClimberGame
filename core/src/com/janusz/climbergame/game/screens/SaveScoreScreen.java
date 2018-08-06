package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.GameBackground;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.Toast;
import com.janusz.climbergame.shared.scoreclient.NetClientPost;

import java.io.IOException;

/**
 * Created by Janusz on 2017-12-07.
 */

public class SaveScoreScreen extends AbstractScreen
{
    private TextField nickInput;
    private TextButton submitScoreBtn;
    private Toast toast = null;
    private boolean displayToast = false;
    private Toast.ToastFactory toastFactory = new Toast.ToastFactory.Builder()
            .font(DefComponents.textFont)
            .build();
    private int actualScore;

    public SaveScoreScreen(ClimberGame game, int actScore)
    {
        super(game);
        actualScore = actScore;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void init()
    {
        submitScoreBtn = new TextButton("OK",DefComponents.TEXTBUTTON_STYLE);
        submitScoreBtn.getLabel().setFontScale(0.4f);
        submitScoreBtn.addListener(new SubmitScoreButtonListener());

        TextButton cancel = new TextButton("MENU",DefComponents.TEXTBUTTON_STYLE);
        cancel.getLabel().setFontScale(0.4f);
        cancel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                stage.dispose();
                game.setScreen(new MenuScreen(game));
            }
        });

        Table UIelements = new Table();
        Label l = new Label("ENTER NICKNAME", DefComponents.LABEL_STYLE);
        l.setFontScale(0.8f);
        UIelements.add(l).colspan(2);
        UIelements.row();
        nickInput = new TextField("", DefComponents.TEXTFIELD_SCORE_STYLE);
        nickInput.setColor(Color.BLACK);
        UIelements.add(nickInput).colspan(2).fill();
        UIelements.row();
        UIelements.add(cancel).fill().pad(50);
        UIelements.add(submitScoreBtn).fill().pad(50);
        UIelements.setFillParent(true);
        stage.addActor(UIelements);
        initBackground();
    }

    private void initBackground()
    {
        GameBackground background = new GameBackground();
        stage.addActor(background);
        background.toBack();
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
        stage.act();
        if (displayToast)
            toast.render(delta);
    }

    private class SubmitScoreButtonListener extends ClickListener
    {
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            String toastOutput;
            if (nickInput.getText().length() > 3 && nickInput.getText().length() < 10)
            {
                NetClientPost ncp = new NetClientPost();
                try
                {
                    String score = String.valueOf(actualScore);
                    String name = nickInput.getText();
                    ncp.addScoreToServer(score, name);
                    toastOutput = "SCORE ADDED";
                    submitScoreBtn.setVisible(false);
                }
                catch(IOException e)
                {
                    toastOutput = "Can't connect to server";
                }
            }
            else
            {
                toastOutput = "NICK MAX LENGTH IS 3-10 CHARACTERS ";
            }
            toast = toastFactory.create(toastOutput, Toast.Length.SHORT);
            displayToast = true;
        }
    }
}
