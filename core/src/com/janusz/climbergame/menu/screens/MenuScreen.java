package com.janusz.climbergame.menu.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.BeginningHandler;
import com.janusz.climbergame.game.background.JungleBackground;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.indicators.graphics.IndicatorController;
import com.janusz.climbergame.game.texts.TapImage;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.ExitButton;
import com.janusz.climbergame.menu.buttons.OptionsButton;
import com.janusz.climbergame.menu.buttons.StartGameButton;
import com.janusz.climbergame.menu.buttons.TopScoresButton;
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

    private StartGameButton startGame;
    private OptionsButton about;
    private TopScoresButton topScores;
    private ExitButton exit;

    private JungleBackground gameBackground;

    private Toast toast;
    private Toast.ToastFactory toastFactory;
    private boolean displayToast;

    public MenuScreen(final ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
        gameBackground = new JungleBackground();
        stage.addActor(gameBackground);
        gameBackground.toBack();
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
        exit = new ExitButton("EXIT", DefComponents.TEXTBUTTON_STYLE, 10);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                TapImage.ins = null;
                Player.player = null;
                IndicatorController.indControl = null;
                dispose();
                Gdx.app.exit();
            }
        });
        stage.addActor(exit);
    }

    private void initTopScores()
    {
        topScores = new TopScoresButton("TOP SCORES", DefComponents.TEXTBUTTON_STYLE, 110);
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
        about = new OptionsButton("ABOUT", DefComponents.TEXTBUTTON_STYLE, 210);
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
        startGame = new StartGameButton("START", DefComponents.TEXTBUTTON_STYLE, 310 );
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen gs = new GameScreen(game);
                Gdx.input.setInputProcessor(new BeginningHandler(gs));
                game.setScreen(gs);
            }
        });
        stage.addActor(startGame);
    }



    @Override
    public void render(float delta)
    {
        super.render(delta);

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
        stage.dispose();
        spriteBatch.dispose();
    }
}
