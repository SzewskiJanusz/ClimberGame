package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.JungleBackground;
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
    private TextField tf;
    private Toast toast = null;
    private boolean displayToast = false;
    private JungleBackground background;
    private Toast.ToastFactory toastFactory = new Toast.ToastFactory.Builder()
            .font(DefComponents.textFont)
            .build();

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
        background = new JungleBackground();
        stage.addActor(background);
        background.toBack();
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
        final TextButton tb = new TextButton("OK",DefComponents.TEXTBUTTON_STYLE);
        tb.setPosition(360,100);
        tb.setSize(100,50);
        tb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if (tf.getText().length() > 0 && tf.getText().length() < 10)
                {
                    NetClientPost ncp = new NetClientPost();
                    try
                    {
                        ncp.addScoreToServer(String.valueOf(ScoreManager.getInstance().ScoreLogic.getScore()),
                                tf.getText());
                        toast = toastFactory.create("Score added", Toast.Length.LONG);
                        displayToast = true;
                        tb.setVisible(false);
                    }catch(IOException e)
                    {
                        toast = toastFactory.create("Can't connect to server", Toast.Length.LONG);
                        displayToast = true;
                    }
                }
                else
                {
                    toast = toastFactory.create("NICK MAX LENGTH IS 10 CHARACTERS ", Toast.Length.SHORT);
                    displayToast = true;
                }
            }
        });

        TextButton cancel = new TextButton("MENU",DefComponents.TEXTBUTTON_STYLE);
        cancel.setPosition(200,100);
        cancel.setSize(100,50);
        cancel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                stage.dispose();
                game.setScreen(new MenuScreen(game));
            }
        });

        stage.addActor(tb);
        stage.addActor(cancel);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
        if (displayToast)
            toast.render(delta);
    }
}
