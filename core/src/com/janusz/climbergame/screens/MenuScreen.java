package com.janusz.climbergame.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-27.
 */

public class MenuScreen extends AbstractScreen
{

    private Label title;

    private TextButton startGame;
    private TextButton options;
    private TextButton bestScores;
    private TextButton exit;




    private int buttonWidth = 900;
    private int starting_x = 600;
    private int buttonHeight = 150;



    public MenuScreen(ClimberGame game)
    {
        super(game);
    }

    @Override
    protected void init()
    {
        initLabel();

        initStartGame();
        initOptions();
        initBestScores();
        initExit();
    }

    private void initLabel()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;

        title = new Label("FAST CLIMBER", ls);
        title.setFontScale(12, 10);
        title.setPosition(500 , 950 ,1);
        stage.addActor(title);

    }

    private void initExit()
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle bs = new TextButton.TextButtonStyle();
        bs.font = new BitmapFont();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");

        exit = new TextButton("EXIT", bs);
        exit.getLabel().setFontScale(5, 5);
        exit.setWidth(900);
        exit.setHeight(150);
        exit.setPosition(600 ,50);
        stage.addActor(exit);

    }

    private void initBestScores()
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle bs = new TextButton.TextButtonStyle();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
        bs.font = new BitmapFont();

        bestScores = new TextButton("BEST SCORES", bs);
        bestScores.getLabel().setFontScale(5, 5);
        bestScores.setWidth(900);
        bestScores.setHeight(150);
        bestScores.setPosition(600 ,250);
        stage.addActor(bestScores);

    }

    private void initOptions()
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle bs = new TextButton.TextButtonStyle();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
        bs.font = new BitmapFont();

        options = new TextButton("OPTIONS", bs);
        options.getLabel().setFontScale(5, 5);
        options.setWidth(900);
        options.setHeight(150);
        options.setPosition(600 ,450);
        stage.addActor(options);
    }

    private void initStartGame()
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle bs = new TextButton.TextButtonStyle();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
        bs.font = new BitmapFont();

        startGame = new TextButton("START", bs);
        startGame.getLabel().setFontScale(5, 5);
        startGame.setWidth(900);
        startGame.setHeight(150);
        startGame.setPosition(600 ,650);
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
