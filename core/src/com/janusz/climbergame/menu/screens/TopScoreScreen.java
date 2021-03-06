package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.shared.factories.ButtonFactory;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.scoreclient.Score;

import java.util.List;


/**
 * Created by Janusz on 2017-11-22.
 */

public class TopScoreScreen extends AbstractScreen
{
    private Table scoreTable;
    private Label playerHeader;
    private Label scoreHeader;
    private Label lpHeader;
    private Label[] lblLp;
    private Label[] lblPlayers;
    private Label[] lblScores;

    public TopScoreScreen(ClimberGame game, List<Score> scores)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);

        fillTableWithHeaders();
        fillTableWithScores(scores);
        game.adService.loadIntersitialAd();
    }

    private void fillTableWithHeaders()
    {
        scoreTable.add(lpHeader).width(160);
        scoreTable.add(playerHeader).width(200);
        scoreTable.add(scoreHeader);
        scoreTable.row();
    }

    private void fillTableWithScores(List<Score> scores)
    {
        for (int i = 0 ; i < scores.size() ; i++)
        {
            lblLp[i] = new Label(String.valueOf(i + 1), DefComponents.LABEL_STYLE);
            lblLp[i].setFontScale(0.24f);
            lblPlayers[i] = new Label(scores.get(i).getName(), DefComponents.LABEL_STYLE);
            lblPlayers[i].setFontScale(0.24f);
            lblScores[i] = new Label(String.valueOf(scores.get(i).getScore()), DefComponents.LABEL_STYLE);
            lblScores[i].setFontScale(0.24f);
            scoreTable.add(lblLp[i]).width(120);
            scoreTable.add(lblPlayers[i]).width(120);
            scoreTable.add(lblScores[i]);
            scoreTable.row();
        }
    }

    @Override
    protected void init()
    {
        initTitleAndAddToStage();
        initTable();
        initHeaderLabels();
        initDataLabels();
        initScrollPaneAndAddToStage();
        initBackButtonAndAddToStage();
    }

    private void initTitleAndAddToStage()
    {
        Image title = new Image(new Texture("title.png"));
        title.setPosition(365 , 420 ,1);
        title.setSize(520,110);
        stage.addActor(title);
    }

    private void initTable()
    {
        scoreTable = new Table();
    }

    private void initHeaderLabels()
    {
        playerHeader = new Label("Player", DefComponents.LABEL_STYLE);
        playerHeader.setFontScale(0.6f);
        scoreHeader = new Label("Score", DefComponents.LABEL_STYLE);
        scoreHeader.setFontScale(0.6f);
        lpHeader = new Label("Lp.", DefComponents.LABEL_STYLE);
        lpHeader.setFontScale(0.6f);
    }

    private void initDataLabels()
    {
        lblLp = new Label[300];
        lblPlayers = new Label[300];
        lblScores = new Label[300];
    }

    private void initScrollPaneAndAddToStage()
    {
        ScrollPane scroll = new ScrollPane(scoreTable);
        scroll.setSize(500,280);
        scroll.setPosition(ClimberGame.WIDTH/5 - 30,ClimberGame.HEIGHT/5 );
        scroll.setScrollingDisabled(true,false);
        stage.addActor(scroll);
    }

    private void initBackButtonAndAddToStage()
    {
        TextButton btnBackToMenu = ButtonFactory.createButton("BACK TO MENU", 30);
        btnBackToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.adService.showInterstitial();
                game.setScreen(new MenuScreen(game,0.2f));
            }
        });
        stage.addActor(btnBackToMenu);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            game.adService.showInterstitial();
            game.setScreen(new MenuScreen(game,0.2f));
        }
        spriteBatch.begin();
        stage.act(delta);
        stage.draw();

        spriteBatch.end();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
        spriteBatch.dispose();
        game.dispose();
    }
}
