package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.player.Player;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.screens.SaveScoreScreen;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.scoreclient.ServerConnection;
import com.sun.corba.se.spi.activation.Server;

import java.io.FileOutputStream;


public class GameOverManager
{
    private Label gameOverLabel;
    private Label yourFinalScoreTextLabel;
    private Label yourBestScoreTextLabel;
    private Label yourBestScoreLabel;
    private Label yourFinalScoreLabel;
    private TextButton mainMenu;
    private TextButton uploadScore;

    private Table table;
    private ClimberGame game;


    public GameOverManager(ClimberGame game)
    {
        this.game = game;
    }

    private void createGameOverLabels()
    {
        initTable();

        gameOverLabel = new Label("GAME OVER", DefComponents.LABEL_STYLE);
        gameOverLabel.setColor(Color.BLACK);
        gameOverLabel.setFontScale(5f,5f);

        yourFinalScoreTextLabel = new Label("SCORE ", DefComponents.LABEL_STYLE);
        yourFinalScoreTextLabel.setFontScale(2.5f);


        yourFinalScoreLabel = new Label(
                String.valueOf(ScoreManager.getInstance().ScoreLogic.getScore()) , DefComponents.LABEL_STYLE);
        yourFinalScoreLabel.setFontScale(2.5f);


        yourBestScoreTextLabel = new Label("BEST ", DefComponents.LABEL_STYLE);
        yourBestScoreTextLabel.setFontScale(2.5f);

        String actualScoreText = ScoreManager.getInstance().ScoreLabel.getText().toString();
        int actualScore = Integer.parseInt(actualScoreText);

        Preferences prefs = Gdx.app.getPreferences("My Preferences");
        String bestText = prefs.getString("highscore");
        int bestInt;
        if (bestText.isEmpty())
        {
            bestInt = 0;
        }
        else
        {
            bestInt = Integer.parseInt(bestText);
        }

        String textToShow;
        Label newRecordText = null;
        if (actualScore > bestInt)
        {
            prefs.putString("highscore",actualScoreText);
            prefs.flush();
            textToShow = actualScoreText;
            newRecordText = new Label("NEW RECORD!", DefComponents.LABEL_STYLE);
        }
        else
        {
            textToShow = bestText;
        }


        yourBestScoreLabel = new Label(textToShow, DefComponents.LABEL_STYLE);
        yourBestScoreLabel.setFontScale(2.5f);

        mainMenu = new TextButton("OK",DefComponents.TEXTBUTTON_STYLE);

        mainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen.stage.dispose();
                GameScreen.deathAnimation = false;
                game.setScreen(new MenuScreen(game));
            }
        });

        uploadScore = new TextButton("SCORE",DefComponents.TEXTBUTTON_STYLE);

        uploadScore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen.stage.dispose();
                GameScreen.deathAnimation = false;
                game.setScreen(new SaveScoreScreen(game));
            }
        });

        Label separator = new Label("", DefComponents.LABEL_STYLE);
        separator.setFontScale(5f);


        table.add(gameOverLabel).width(250).row();
        table.add(yourFinalScoreTextLabel).width(250);
        table.add(yourBestScoreTextLabel).width(50).padLeft(70).row();
        table.add(yourFinalScoreLabel).width(150).padLeft(30);
        table.add(yourBestScoreLabel).width(100).padLeft(70).row();
        if (newRecordText == null)
            table.add(separator).row();
        else
            table.add(newRecordText).width(50).row();
        table.add(mainMenu).width(150);
        table.add(uploadScore).width(150);
        table.add(new Label("", DefComponents.LABEL_STYLE)).width(150);

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
