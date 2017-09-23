package com.janusz.climbergame.controllers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;


public class GameOverController
{
    private Label gameOverLabel;
    private Label yourFinalScoreTextLabel;
    private Label yourBestScoreTextLabel;
    private Label yourBestScoreLabel;
    private Label yourFinalScoreLabel;
    private Button mainMenu;
    private Button tryAgain;

    public GameOverController()
    {
        createGameOverLabels();
    }

    private void createGameOverLabels()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;

        gameOverLabel = new Label("GAME OVER", ls);
        getGameOverLabel().setFontScale(5);
        getGameOverLabel().setPosition(ClimberGame.WIDTH / 2 - getGameOverLabel().getWidth(),
                ClimberGame.HEIGHT / 2 + 3 * getGameOverLabel().getHeight() , 1);


        yourFinalScoreTextLabel = new Label("SCORE ", ls);
        getYourFinalScoreTextLabel().setFontScale(3);
        getYourFinalScoreTextLabel().setPosition(ClimberGame.WIDTH / 2 - getGameOverLabel().getWidth() +
                        getYourFinalScoreTextLabel().getWidth(),
                ClimberGame.HEIGHT / 2 , 1);

        yourFinalScoreLabel = new Label("0", ls);
        getYourFinalScoreLabel().setFontScale(4);
        getYourFinalScoreLabel().setPosition(ClimberGame.WIDTH / 2 - getGameOverLabel().getWidth() +
                        getYourFinalScoreLabel().getWidth(),
                ClimberGame.HEIGHT / 2  - 2 * getGameOverLabel().getHeight(), 1);

        yourBestScoreTextLabel = new Label("BEST ", ls);
        getYourBestScoreTextLabel().setFontScale(3);
        getYourBestScoreTextLabel().setPosition(ClimberGame.WIDTH / 2 - getGameOverLabel().getWidth() +
                        getYourBestScoreTextLabel().getWidth(),
                ClimberGame.HEIGHT / 2 - 4 * getGameOverLabel().getHeight()
                , 1);

        yourBestScoreLabel = new Label("1000", ls);
        getYourBestScoreLabel().setFontScale(4);
        getYourBestScoreLabel().setPosition(ClimberGame.WIDTH / 2 - getGameOverLabel().getWidth() +
                        getYourBestScoreLabel().getWidth(),
                ClimberGame.HEIGHT / 2 - 6 * getGameOverLabel().getHeight(), 1);


    }

    /*
                getters
                    |
                    v
    */

    public Label getGameOverLabel()
    {
        return gameOverLabel;
    }

    public Label getYourFinalScoreTextLabel()
    {
        return yourFinalScoreTextLabel;
    }

    public Label getYourBestScoreTextLabel()
    {
        return yourBestScoreTextLabel;
    }

    public Label getYourBestScoreLabel()
    {
        return yourBestScoreLabel;
    }

    public Label getYourFinalScoreLabel()
    {
        return yourFinalScoreLabel;
    }

    public Button getMainMenu()
    {
        return mainMenu;
    }

    public Button getTryAgain()
    {
        return tryAgain;
    }
}
