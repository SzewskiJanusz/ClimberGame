package com.janusz.climbergame.menu.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.shared.factories.ButtonFactory;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.GameBackground;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.sound.GameSound;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.Toast;
import com.janusz.climbergame.shared.scoreclient.NetClientGet;
import com.janusz.climbergame.shared.scoreclient.Score;

import java.io.IOException;
import java.util.List;

public class MenuScreen extends AbstractScreen
{
    private Toast toast;
    private Toast.ToastFactory toastFactory;
    private boolean displayToast;
    private float timeToDelayBackButton;

    public MenuScreen(final ClimberGame game)
    {
        super(game);
        Gdx.input.setInputProcessor(stage);
        timeToDelayBackButton = 0f;
    }

    public MenuScreen(final ClimberGame game, float delayTime)
    {
        super(game);
        Gdx.input.setInputProcessor(stage);
        timeToDelayBackButton = delayTime;
    }

    @Override
    protected void init()
    {
        initGameBackground();
        initTitle();
        initStartGame();
        initAbout();
        initTopScores();
        initExit();
    }

    private void initGameBackground()
    {
        GameBackground gameBackground = new GameBackground();
        gameBackground.toBack();
        stage.addActor(gameBackground);
    }

    private void initTitle()
    {
        Image title = new Image(new Texture("title.png"));
        title.setPosition(365 , 420 ,1);
        title.setSize(520,110);
        stage.addActor(title);
    }

    private void initExit()
    {
        TextButton exit = ButtonFactory.createButton("EXIT", 40);
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
        TextButton topScores = ButtonFactory.createButton("TOP SCORES", 130);
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

    private void initAbout()
    {
        TextButton about = ButtonFactory.createButton("ABOUT", 220);
        about.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new InstructionScreen(game));
            }
        });
        stage.addActor(about);
    }

    private void initStartGame()
    {
        TextButton startGame = ButtonFactory.createButton("START", 320);
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen gs = new GameScreen(game);
                game.setScreen(gs);
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
        GameSound.dispose();
        stage.dispose();
        spriteBatch.dispose();
    }
}
