package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.buttons.BackToMenuButton;


/**
 * Created by Janusz on 2017-11-22.
 */

public class TopScoreScreen extends AbstractScreen
{

    private Title title;

    private TextButton.TextButtonStyle bs;

    private BackToMenuButton btnBackToMenu;

    public TopScoreScreen(ClimberGame game)
    {
        super(game);
    }


    @Override
    protected void init()
    {
        initTitle();

        initButtonStyle();
        initBackButton();

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;
        Label l = new Label("janusz", ls);
        Label l1 = new Label("janusz1", ls);
        Label l2 = new Label("janusz2", ls);
        Label l3 = new Label("janusz3", ls);

        l.setFontScale(2);
        l1.setFontScale(2);
        l2.setFontScale(2);
        l3.setFontScale(2);


        Table table = new Table();
        table.setFillParent(true);
        table.add(l);
        table.add(l1).width(100);
        table.row();
        table.add(l2);
        table.add(l3).width(100);

        stage.addActor(table);
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
