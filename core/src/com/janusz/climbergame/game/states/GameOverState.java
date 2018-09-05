package com.janusz.climbergame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.janusz.climbergame.shared.factories.ButtonFactory;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.game.screens.SaveScoreScreen;
import com.janusz.climbergame.menu.screens.MenuScreen;
import com.janusz.climbergame.shared.DefComponents;

public class GameOverState implements GameState
{
    private PlayGameState playGameState;
    private ClimberGame game;


    public GameOverState(PlayGameState pgs, ClimberGame game)
    {
        this.playGameState = pgs;
        this.game = game;
        init();
    }

    @Override
    public void tick(float delta)
    {
        update(delta);
    }

    @Override
    public void update(float delta)
    {

    }

    public void init()
    {
        Table gameOverSummary = initTable();
        int actualScore = playGameState.scoreMgr.ScoreLogic.getScore();
        Preferences prefs = Gdx.app.getPreferences("My Preferences2");
        int bestScore = 0;
        if (prefs.contains("highscore"))
             bestScore = prefs.getInteger("highscore");
        String scoreToShowInSummary;
        Label newRecordText;
        if (actualScore > bestScore)
        {
            putNewHighscoreInDB(prefs, actualScore);
            scoreToShowInSummary = String.valueOf(actualScore);
            newRecordText = new Label("NEW RECORD!", DefComponents.LABEL_STYLE);
        }
        else
        {
            scoreToShowInSummary = String.valueOf(bestScore);
            newRecordText = new Label("", DefComponents.LABEL_STYLE);
        }

        newRecordText.setFontScale(0.2f);

        gameOverSummary.add(createAndGetGameOverLabel()).width(ClimberGame.WIDTH).colspan(3).row();
        gameOverSummary.add(createAndGetYourFinalScoreTextLabel());
        gameOverSummary.add(new Label("", DefComponents.LABEL_STYLE));
        gameOverSummary.add(createAndGetYourBestScoreTextLabel()).row();
        gameOverSummary.add(createAndGetYourFinalScoreLabel());
        gameOverSummary.add(new Label("", DefComponents.LABEL_STYLE));
        gameOverSummary.add(createAndGetYourBestScoreLabel(scoreToShowInSummary)).row();
        gameOverSummary.add(newRecordText).row();
        gameOverSummary.add(createAndGetMainMenuButton()).fill().pad(5);
        gameOverSummary.add(createAndGetUploadScoreButton()).fill().pad(5);
        gameOverSummary.add(createAndGetTryAgainButton()).fill().pad(5);
        playGameState.gameScreen.stage.addActor(gameOverSummary);
    }

    private void putNewHighscoreInDB(Preferences prefs, int actualScore)
    {
        prefs.putInteger("highscore", actualScore);
        prefs.flush();
    }

    private TextButton createAndGetTryAgainButton()
    {
        TextButton tryAgain = ButtonFactory.createButton("AGAIN");
        tryAgain.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                playGameState.gameScreen.stage.dispose();
                playGameState.gameScreen.spriteBatch.dispose();
                GameScreen gs = new GameScreen(game);
                game.setScreen(gs);
            }
        });
        return tryAgain;
    }

    private TextButton createAndGetUploadScoreButton()
    {
        TextButton uploadScore = ButtonFactory.createButton("SCORE");
        uploadScore.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new SaveScoreScreen(game, playGameState.scoreMgr.ScoreLogic.getScore()));
            }
        });
        return uploadScore;
    }

    private TextButton createAndGetMainMenuButton()
    {
        TextButton mainMenu = ButtonFactory.createButton("MENU");
        mainMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                playGameState.gameScreen.stage.dispose();
                game.setScreen(new MenuScreen(game));
            }
        });
        return mainMenu;
    }

    private Label createAndGetYourBestScoreLabel(String textWithBestScore)
    {
        Label yourBestScoreLabel = new Label(textWithBestScore, DefComponents.LABEL_STYLE);
        yourBestScoreLabel.setFontScale(0.5f);
        return yourBestScoreLabel;
    }

    private Label createAndGetYourFinalScoreTextLabel()
    {
        Label yourFinalScoreTextLabel = new Label("SCORE ", DefComponents.LABEL_STYLE);
        yourFinalScoreTextLabel.setFontScale(0.8f);
        return yourFinalScoreTextLabel;
    }

    private Table initTable()
    {
        Table table = new Table();
        table.setFillParent(true);
        table.setY(0);
        return table;
    }

    private Label createAndGetGameOverLabel()
    {
        Label gameOverLabel = new Label("GAME OVER", DefComponents.LABEL_STYLE);
        gameOverLabel.setColor(Color.BLACK);
        gameOverLabel.setFontScale(1.5f,1.5f);
        gameOverLabel.setAlignment(Align.top);
        return gameOverLabel;
    }

    private Label createAndGetYourBestScoreTextLabel()
    {
       Label yourBestScoreTextLabel = new Label("BEST ", DefComponents.LABEL_STYLE);
        yourBestScoreTextLabel.setFontScale(0.8f);
        return yourBestScoreTextLabel;
    }

    private Label createAndGetYourFinalScoreLabel()
    {
        Label yourFinalScoreLabel = new Label(
                String.valueOf(playGameState.scoreMgr.ScoreLogic.getScore()) , DefComponents.LABEL_STYLE);
        yourFinalScoreLabel.setFontScale(0.5f);
        return yourFinalScoreLabel;
    }
}
