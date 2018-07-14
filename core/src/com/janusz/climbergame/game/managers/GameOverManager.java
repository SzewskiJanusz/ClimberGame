package com.janusz.climbergame.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.BeginningHandler;
import com.janusz.climbergame.game.managers.score.ScoreManager;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.screens.SaveScoreScreen;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.DefComponents;


public class GameOverManager
{
    private Label gameOverLabel;
    private Label yourFinalScoreTextLabel;
    private Label yourBestScoreTextLabel;
    private Label yourBestScoreLabel;
    private Label yourFinalScoreLabel;
    private TextButton mainMenu;
    private TextButton uploadScore;
    private TextButton tryAgain;

    private Table table;
    private ClimberGame game;


    public GameOverManager(ClimberGame game)
    {
        this.game = game;
    }

    private void createGameOverLabels()
    {
        initTable();
        table.debug();
        table.setFillParent(true);

        gameOverLabel = new Label("GAME OVER", DefComponents.LABEL_STYLE);
        gameOverLabel.setColor(Color.BLACK);
        gameOverLabel.setFontScale(1.5f,1.5f);
        gameOverLabel.setAlignment(Align.top);

        yourFinalScoreTextLabel = new Label("SCORE ", DefComponents.LABEL_STYLE);
        yourFinalScoreTextLabel.setFontScale(0.8f);


        yourFinalScoreLabel = new Label(
                String.valueOf(ScoreManager.getInstance().ScoreLogic.getScore()) , DefComponents.LABEL_STYLE);
        yourFinalScoreLabel.setFontScale(0.5f);


        yourBestScoreTextLabel = new Label("BEST ", DefComponents.LABEL_STYLE);
        yourBestScoreTextLabel.setFontScale(0.8f);

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
            newRecordText.setFontScale(0.2f);
        }
        else
        {
            textToShow = bestText;
        }


        yourBestScoreLabel = new Label(textToShow, DefComponents.LABEL_STYLE);
        yourBestScoreLabel.setFontScale(0.5f);

        mainMenu = new TextButton("MENU",DefComponents.TEXTBUTTON_STYLE);
        mainMenu.getLabel().setFontScale(0.4f);

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
        uploadScore.getLabel().setFontScale(0.4f);
        uploadScore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                GameScreen.stage.dispose();
                GameScreen.deathAnimation = false;
                game.setScreen(new SaveScoreScreen(game));
            }
        });

        tryAgain = new TextButton("AGAIN", DefComponents.TEXTBUTTON_STYLE);
        tryAgain.getLabel().setFontScale(0.4f);
        tryAgain.addListener(new ClickListener(){
        @Override
        public void clicked(InputEvent event, float x, float y)
        {
            GameScreen.stage.dispose();
        //    GameScreen gs = new GameScreen(game);
        //    Gdx.input.setInputProcessor(new BeginningHandler(gs));
        //    game.setScreen(new GameScreen(game));
        }
    });

        Label separator = new Label("", DefComponents.LABEL_STYLE);
        separator.setFontScale(0.2f);

        table.add(gameOverLabel).width(ClimberGame.WIDTH).colspan(3).row();
        table.add(yourFinalScoreTextLabel);
        table.add(new Label("", DefComponents.LABEL_STYLE));
        table.add(yourBestScoreTextLabel).row();
        table.add(yourFinalScoreLabel);
        table.add(new Label("", DefComponents.LABEL_STYLE));
        table.add(yourBestScoreLabel).row();
        if (newRecordText == null)
            table.add(separator).row();
        else
            table.add(newRecordText).row();
        table.add(mainMenu).fill().pad(5);
        table.add(uploadScore).fill().pad(5);
        table.add(tryAgain).fill().pad(5);
    }

    private void initTable()
    {
        table = new Table();
        table.setFillParent(true);
        table.setY(0);
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
