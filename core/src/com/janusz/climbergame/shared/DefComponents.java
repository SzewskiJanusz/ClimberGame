package com.janusz.climbergame.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by Janusz on 2017-11-30.
 */

public abstract class DefComponents
{
    public static Label.LabelStyle getDefaultLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont();
        ls.fontColor = Color.WHITE;
        ls.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        return ls;
    }

    public static TextField.TextFieldStyle getDefaultTextfieldStyle()
    {
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.font = new BitmapFont();
        tStyle.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        tStyle.fontColor = Color.WHITE;
        return tStyle;
    }

    public static TextButton.TextButtonStyle getTextButtonStyle()
    {
        TextButton.TextButtonStyle bs;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui-green.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = new BitmapFont();
        bs.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        bs.up = skin.getDrawable("button_02");
        bs.down = skin.getDrawable("button_06");
        return bs;
    }
}
