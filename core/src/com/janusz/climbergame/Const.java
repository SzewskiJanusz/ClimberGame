package com.janusz.climbergame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Janusz on 2018-01-11.
 */

public abstract class Const
{
    /** TEQUILA CONSTS */
    public final static int TEQUILA_DELAY_SPAWN = 5;
    public final static int TEQUILA_EFFECT_TIME = 5;
    public final static int TEQUILA_BOTTOM_RANGE = 9;
    public final static int TEQUILA_UPPER_RANGE = 18;
    public final static int TEQUILA_WIDTH = 45;
    public final static int TEQUILA_HEIGHT = 107;
    public final static int TEQUILA_BASE_VELOCITY = 250;
    public final static Texture TEQUILA_TEXTURE  = new Texture("tequila.png");

    /** COFFEE CONSTS */
    public final static int COFFEE_EFFECT_TIME = 15;
    public final static int COFFEE_DELAY_SPAWN = 8;
    public final static int COFFEE_BOTTOM_RANGE = 7;
    public final static int COFFEE_UPPER_RANGE = 14;
    public final static int COFFEE_WIDTH = 30;
    public final static int COFFEE_HEIGHT = 75;
    public final static int COFFEE_BASE_VELOCITY = 250;
    public final static Texture COFFEE_TEXTURE = new Texture("coffee.png");

    /** ANVIL CONSTS */
    public final static int ANVIL_DELAY_SPAWN = 5;
    public final static int ANVIL_BOTTOM_RANGE = 1;
    public final static int ANVIL_UPPER_RANGE = 7;
    public final static int ANVIL_WIDTH = 80;
    public final static int ANVIL_HEIGHT = 55;
    public final static int ANVIL_BASE_VELOCITY = 300;
    public final static Texture ANVIL_TEXTURE = new Texture("anvil.png");

    /** BANANA CONSTS */
    public final static int BANANA_DELAY_SPAWN = 3;
    public final static int BANANA_BOTTOM_RANGE = 3;
    public final static int BANANA_UPPER_RANGE = 7;
    public final static int BANANA_WIDTH = 45;
    public final static int BANANA_HEIGHT = 30;
    public final static int BANANA_BASE_VELOCITY = 215;
    public final static Texture BANANA_TEXTURE = new Texture("banana.png");

    /** STONE CONSTS */
    public final static int STONE_DELAY_SPAWN = 3;
    public final static int STONE_BOTTOM_RANGE = 1;
    public final static int STONE_UPPER_RANGE = 7;
    public final static int STONE_WIDTH = 55;
    public final static int STONE_HEIGHT = 80;
    public final static int STONE_BASE_VELOCITY = 300;
    public final static Texture STONE_TEXTURE = new Texture("stone.png");

    /** DISTANCE BETWEEN INDICATOR AND LABEl */
    public final static int SPACE_BETWEEN_INDICATOR_AND_LABEL = 30;

    /** DISTANCE BETWEEN INDICATORS */
    public final static int SPACE_BETWEEN_INDICATORS = 70;

    /** DRUNK INDICATOR CONSTS */
    public final static int DRUNKINDICATOR_X = 550;
    public final static int DRUNKINDICATOR_Y = 120;
    public final static int DRUNKINDICATOR_WIDTH = 75;
    public final static int DRUNKINDICATOR_HEIGHT = 100;
    public final static Texture DRUNKINDICATOR_TEXTURE = new Texture("drop.png");

    /** COFFEE INDICATOR CONSTS */
    public final static int COFFEEINDICATOR_X = 550;
    public final static int COFFEEINDICATOR_Y = DRUNKINDICATOR_Y + DRUNKINDICATOR_WIDTH + SPACE_BETWEEN_INDICATORS;
    public final static int COFFEEINDICATOR_WIDTH = 75;
    public final static int COFFEEINDICATOR_HEIGHT = 100;
    public final static Texture COFFEEINDICATOR_TEXTURE = new Texture("coffeeindicator.png");

    /** LIANATILE ENTITY CONSTS */
    public final static int LIANATILE_WIDTH = 50;
    public final static int LIANATILE_HEIGHT = 120;
    public final static int LIANATILE_VELOCITY = 250;
    public final static Texture LIANATILE_TEXTURE = new Texture("liana.png");

    /** LIANAMANAGER CONSTS */
    public static final int LIANAMANAGER_FIRST_X = 150;
    public static final int LIANAMANAGER_SECOND_X = 300;
    public static final int LIANAMANAGER_THIRD_X = 450;

    /** PLAYER ANIMATION CONSTS */
    public static final int FRAME_WIDTH = 260;
    public static final int FRAME_HEIGHT = 240;
    public static final Texture FRAME_STRIP_TEXTURE = new Texture("climbing-strip.png");

}
