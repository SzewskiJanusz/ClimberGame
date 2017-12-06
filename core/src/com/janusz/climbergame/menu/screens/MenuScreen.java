package com.janusz.climbergame.menu.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.EventHandler;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.ExitButton;
import com.janusz.climbergame.menu.buttons.OptionsButton;
import com.janusz.climbergame.menu.buttons.StartGameButton;
import com.janusz.climbergame.menu.buttons.TopScoresButton;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.game.screens.GameScreen;

/**
 * Created by Janusz on 2017-09-27.
 */

public class MenuScreen extends AbstractScreen
{

    private Title title;

    private TextButton.TextButtonStyle bs;

    private StartGameButton startGame;
    private OptionsButton options;
    private TopScoresButton topScores;
    private ExitButton exit;


    public MenuScreen(final ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void init()
    {
        initTitle();

        initButtonStyle();

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


    private void initButtonStyle()
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = new BitmapFont();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
    }

    private void initExit()
    {
        exit = new ExitButton("EXIT", bs, 10);
        stage.addActor(exit);
    }

    private void initTopScores()
    {
        topScores = new TopScoresButton("TOP SCORES", bs, 110);
        topScores.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new TopScoreScreen(game));
            }
        });
        stage.addActor(topScores);
    }

    private void initOptions()
    {
        options = new OptionsButton("OPTIONS", bs, 210);
        options.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new OptionsScreen(game));
            }
        });
        stage.addActor(options);
    }

    private void initStartGame()
    {
        startGame = new StartGameButton("START", bs, 310 );
        startGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.input.setInputProcessor(new EventHandler());
                game.setScreen(new GameScreen(game));
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

        spriteBatch.end();
    }
}
