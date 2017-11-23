package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.BackToMenuButton;
import com.janusz.climbergame.shared.scoreclient.ServerConnection;
import java.util.List;


/**
 * Created by Janusz on 2017-11-22.
 */

public class TopScoreScreen extends AbstractScreen
{

    private Title title;

    private TextButton.TextButtonStyle bs;

    private BackToMenuButton btnBackToMenu;

    private Table scoreTable;

    private Label playerHeader;
    private Label scoreHeader;
    private Label lpHeader;

    private Label[] lblLp;
    private Label[] lblPlayers;
    private Label[] lblScores;

    private Label.LabelStyle ls;

    public TopScoreScreen(ClimberGame game)
    {
        super(game);
        // Set input processor here because stage is initialized
        Gdx.input.setInputProcessor(stage);

        fillTableWithHeaders();
        ServerConnection sc = new ServerConnection();
        List<String> scores = sc.getScoresFromServer();
        fillTableWithScores(scores);
    }

    private void fillTableWithHeaders()
    {
        scoreTable.add(lpHeader).width(160);
        scoreTable.add(playerHeader).width(200);
        scoreTable.add(scoreHeader);
        scoreTable.row();
    }

    private void fillTableWithScores(List<String> scores)
    {
        for (int i = 0 ; i < scores.size() ; i++)
        {
            lblLp[i] = new Label(String.valueOf(i + 1), ls);
            lblLp[i].setFontScale(1.2f);
            lblPlayers[i] = new Label(scores.get(i).split(":")[0], ls);
            lblPlayers[i].setFontScale(1.2f);
            lblScores[i] = new Label(scores.get(i).split(":")[1], ls);
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
        initTitle();
        initLabelStyle();
        initButtonStyle();
        initBackButton();
        initTable();

        initHeaderLabels();
        initDataLabels();

    }

    private void initLabelStyle()
    {
        ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;
    }

    private void initDataLabels()
    {
        lblLp = new Label[10];
        lblPlayers = new Label[10];
        lblScores = new Label[10];
    }

    private void initHeaderLabels()
    {
        playerHeader = new Label("Player", ls);
        playerHeader.setFontScale(3);
        scoreHeader = new Label("Score", ls);
        scoreHeader.setFontScale(3);
        lpHeader = new Label("Lp.", ls);
        lpHeader.setFontScale(3);
    }

    private void initTable()
    {
        scoreTable = new Table();
        scoreTable.setFillParent(true);
        stage.addActor(scoreTable);
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

    private void initBackButton()
    {
        btnBackToMenu = new BackToMenuButton("BACK TO MENU", bs, 30);
        btnBackToMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
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
        stage.draw();

        spriteBatch.end();
    }
}
