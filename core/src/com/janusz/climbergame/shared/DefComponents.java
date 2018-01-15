package com.janusz.climbergame.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    private static BitmapFont textFont;

    public static Label.LabelStyle getDefaultLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = textFont;
        ls.fontColor = Color.WHITE;
        ls.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        return ls;
    }

    public static TextField.TextFieldStyle getDefaultTextfieldStyle()
    {
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.font = textFont;

        tStyle.fontColor = Color.WHITE;
        return tStyle;
    }

    public static TextButton.TextButtonStyle getTextButtonStyle()
    {
        TextButton.TextButtonStyle bs;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = textFont;
       // bs.up = skin.getDrawable("button_02");
       // bs.down = skin.getDrawable("button_06");
        bs.up = skin.getDrawable("button-orange");
        bs.down = skin.getDrawable("button-orange-down");
        return bs;
    }

    public static void createFonts() {
        FileHandle fontFile = Gdx.files.internal("gomarice_no_continue.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = new Color(169f/255,42f/255,54f/255,1f);
        textFont = generator.generateFont(parameter);
        generator.dispose();
    }
}
