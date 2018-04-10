package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.BackToMenuButton;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.scoreclient.NetClientGet;
import com.janusz.climbergame.shared.scoreclient.Score;

import java.util.List;


/**
 * Created by Janusz on 2017-11-22.
 */

public class TopScoreScreen extends AbstractScreen
{

    private Title title;

    private BackToMenuButton btnBackToMenu;

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
            lblLp[i].setFontScale(1.2f);
            lblPlayers[i] = new Label(scores.get(i).getName(), DefComponents.LABEL_STYLE);
            lblPlayers[i].setFontScale(1.2f);
            lblScores[i] = new Label(String.valueOf(scores.get(i).getScore()), DefComponents.LABEL_STYLE);
            lblScores[i].setFontScale(1.2f);
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
        title = new Title();
        stage.addActor(title);
    }

    private void initTable()
    {
        scoreTable = new Table();
    }

    private void initHeaderLabels()
    {
        playerHeader = new Label("Player", DefComponents.LABEL_STYLE);
        playerHeader.setFontScale(3);
        scoreHeader = new Label("Score", DefComponents.LABEL_STYLE);
        scoreHeader.setFontScale(3);
        lpHeader = new Label("Lp.", DefComponents.LABEL_STYLE);
        lpHeader.setFontScale(3);
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
        btnBackToMenu = new BackToMenuButton("BACK TO MENU", DefComponents.TEXTBUTTON_STYLE, 30);
        btnBackToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.adService.showInterstitial();
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(btnBackToMenu);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);

        spriteBatch.begin();
        stage.act(delta);
        stage.draw();

        spriteBatch.end();
    }
}
