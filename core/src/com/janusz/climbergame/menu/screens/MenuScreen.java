package com.janusz.climbergame.menu.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.BeginningHandler;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.managers.queue.QueueManager;
import com.janusz.climbergame.game.pause.PauseController;
import com.janusz.climbergame.game.screens.ModeSelectScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.game.texts.TapImage;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.ButtonFactory;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.Toast;
import com.janusz.climbergame.shared.scoreclient.NetClientGet;
import com.janusz.climbergame.shared.scoreclient.Score;

import java.io.IOException;
import java.util.List;

/**
 * Created by Janusz on 2017-09-27.
 */

public class MenuScreen extends AbstractScreen
{

    private Title title;

    private JungleBackground gameBackground;

    private Toast toast;
    private Toast.ToastFactory toastFactory;
    private boolean displayToast;
    private float timeToDelayBackButton;

    public MenuScreen(final ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
        gameBackground = new JungleBackground();
        stage.addActor(gameBackground);
        gameBackground.toBack();
        timeToDelayBackButton = 0f;
    }

    public MenuScreen(final ClimberGame game, float delayTime)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
        gameBackground = new JungleBackground();
        stage.addActor(gameBackground);
        gameBackground.toBack();
        timeToDelayBackButton = delayTime;
    }

    @Override
    protected void init()
    {
        initTitle();
        initStartGame();
        initOptions();
        initTopScores();
        initExit();
    }

    private void initTitle()
    {
        title = new Title();
        stage.addActor(title);
    }

    private void initExit()
    {
        TextButton exit = ButtonFactory.createButton("EXIT", DefComponents.TEXTBUTTON_STYLE, 40);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                dispose();
                Gdx.app.exit();
                System.exit(0);
            }
        });
        stage.addActor(exit);
    }

    private void initTopScores()
    {
        TextButton topScores = ButtonFactory.createButton("TOP SCORES", DefComponents.TEXTBUTTON_STYLE, 130);
        topScores.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                NetClientGet ncg = new NetClientGet();
                List<Score> scores;
                try
                {
                    scores = ncg.getScoresFromServer();
                    game.setScreen(new TopScoreScreen(game, scores));
                }
                catch(IOException ex)
                {
                    toastFactory = new Toast.ToastFactory.Builder()
                            .font(DefComponents.textFont)
                            .build();
                    toast = toastFactory.create("Can't connect to server", Toast.Length.LONG);
                    displayToast = true;
                }
            }
        });
        stage.addActor(topScores);
    }

    private void initOptions()
    {
        TextButton about = ButtonFactory.createButton("ABOUT", DefComponents.TEXTBUTTON_STYLE, 220);
        about.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new AboutScreen(game));
            }
        });
        stage.addActor(about);
    }

    private void initStartGame()
    {
        TextButton startGame = ButtonFactory.createButton("START", DefComponents.TEXTBUTTON_STYLE, 320);
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
//                GameScreen gs = new GameScreen(game);
                ModeSelectScreen select = new ModeSelectScreen(game);
               // Gdx.input.setInputProcessor(new BeginningHandler(gs));
                game.setScreen(select);
            }
        });
        stage.addActor(startGame);
    }



    @Override
    public void render(float delta)
    {
        super.render(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            if (timeToDelayBackButton <= 0)
            {
                dispose();
                Gdx.app.exit();
                System.exit(0);
            }
        }
        if (timeToDelayBackButton > 0)
        {
            timeToDelayBackButton -= delta;
        }

        spriteBatch.begin();
        stage.draw();

        if (displayToast){
            toast.render(delta);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        TapImage.ins = null;
        Player.player = null;
        PauseController.dispose();
        GameSound.dispose();
        QueueManager.dispose();
        IndicatorController.indControl = null;
        EntireLiana.dispose();
        stage.dispose();
        spriteBatch.dispose();
    }
}
