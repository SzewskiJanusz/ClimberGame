package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.screens.SaveScoreScreen;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.scoreclient.ServerConnection;
import com.sun.corba.se.spi.activation.Server;


public class GameOverManager
{
    private Label gameOverLabel;
    private Label yourFinalScoreTextLabel;
    private Label yourBestScoreTextLabel;
    private Label yourBestScoreLabel;
    private Label yourFinalScoreLabel;
    private TextButton mainMenu;
    private TextButton uploadScore;

    private TextButton.TextButtonStyle bs;
    private Table table;
    private ClimberGame game;


    public GameOverManager(ClimberGame game)
    {
        this.game = game;
    }

    private void createGameOverLabels()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;

        initTable();

        gameOverLabel = new Label("GAME OVER", ls);
        gameOverLabel.setFontScale(5f);

        yourFinalScoreTextLabel = new Label("SCORE ", ls);
        yourFinalScoreTextLabel.setFontScale(2.5f);


        yourFinalScoreLabel = new Label(
                String.valueOf(ScoreManager.getInstance().ScoreLogic.getScore()) , ls);
        yourFinalScoreLabel.setFontScale(2.5f);


        yourBestScoreTextLabel = new Label("BEST ", ls);
        yourBestScoreTextLabel.setFontScale(2.5f);

        yourBestScoreLabel = new Label("1000", ls);
        yourBestScoreLabel.setFontScale(2.5f);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = new BitmapFont();
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");

        mainMenu = new TextButton("OK",bs);

        mainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen.stage.dispose();
                game.setScreen(new MenuScreen(game));
            }
        });

        uploadScore = new TextButton("SCORE",bs);

        uploadScore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new SaveScoreScreen(game));
            }
        });

        table.add(gameOverLabel).width(150).row();
        table.add(yourFinalScoreTextLabel).width(150);
        table.add(yourFinalScoreLabel).width(100);
        table.add(yourBestScoreTextLabel).width(100);
        table.add(yourBestScoreLabel).width(100).row();
        table.add(mainMenu).width(150);
        table.add(new Label("", ls)).width(150);
        table.add(uploadScore).width(150);
    }

    private void initTable()
    {
        table = new Table();
        table.setFillParent(true);
        table.setY(50);
        GameScreen.stage.addActor(table);
    }

    /*
                getters
                    |
                    v
    */

    public Table getTable()
    {
        createGameOverLabels();
        Gdx.input.setInputProcessor(GameScreen.stage);
        return table;
    }
}
