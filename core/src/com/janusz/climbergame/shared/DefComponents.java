package com.janusz.climbergame.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * Created by Janusz on 2017-11-30.
 */

public abstract class DefComponents
{
    public static BitmapFont textFont;
    public static BitmapFont textFontWhite;
    public static BitmapFont textFieldFont;
    public static Label.LabelStyle LABEL_STYLE;
    public static Label.LabelStyle LABEL_STYLE_WHITE;
    public static TextField.TextFieldStyle TEXTFIELD_STYLE;
    public static TextButton.TextButtonStyle TEXTBUTTON_STYLE;
    public static TextField.TextFieldStyle TEXTFIELD_SCORE_STYLE;


    public static void prepareStyles()
    {
        LABEL_STYLE = getDefaultLabelStyle();
        LABEL_STYLE_WHITE = getWhiteDefaultLabelStyle();
        TEXTFIELD_STYLE = getDefaultTextfieldStyle();
        TEXTBUTTON_STYLE = getTextButtonStyle();
        TEXTFIELD_SCORE_STYLE = getScoreTextfieldStyle();
    }

    private static Label.LabelStyle getDefaultLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = textFont;
        ls.fontColor = Color.WHITE;

        ls.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        return ls;
    }

    private static Label.LabelStyle getWhiteDefaultLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = textFontWhite;
        ls.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
                Texture.TextureFilter.Linear);
        return ls;
    }

    private static TextField.TextFieldStyle getDefaultTextfieldStyle()
    {
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.font = textFont;
        tStyle.fontColor = Color.BLUE;
        return tStyle;
    }

    private static TextField.TextFieldStyle getScoreTextfieldStyle()
    {
        TextField.TextFieldStyle tStyle = new TextField.TextFieldStyle();
        tStyle.font = textFieldFont;
        tStyle.fontColor = Color.BLACK;
        tStyle.font.getData().setScale(1f);
        return tStyle;
    }

    private static TextButton.TextButtonStyle getTextButtonStyle()
    {
        TextButton.TextButtonStyle bs;
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("uiskin.atlas"));
        Skin skin = new Skin(atlas);
        bs = new TextButton.TextButtonStyle();
        bs.font = textFont;
        bs.up = skin.getDrawable("button-orange");
        bs.down = skin.getDrawable("button-orange-down");
        return bs;

    }

    private static Label.LabelStyle getBeginLabelStyle()
    {
        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = textFont;
        ls.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear,
            Texture.TextureFilter.Linear);

        return ls;
    }


    public static void createFonts() {
        FileHandle fontFile = Gdx.files.internal("gomarice_no_continue.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;//20;
        parameter.color = new Color(169f/255,42f/255,54f/255,1f);
        textFont = generator.generateFont(parameter);
        parameter.color = Color.BLACK;
        textFieldFont = generator.generateFont(parameter);
        parameter.color = new Color(1,1,1,1f);
        textFontWhite = generator.generateFont(parameter);
        generator.dispose();
    }

    public static void dispose()
    {
        textFieldFont.dispose();
        textFont.dispose();
        textFontWhite.dispose();
    }
}
