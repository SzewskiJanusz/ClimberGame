package com.janusz.climbergame;


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

    /** COFFEE CONSTS */
    public final static int COFFEE_EFFECT_TIME = 15;
    public final static int COFFEE_DELAY_SPAWN = 8;
    public final static int COFFEE_BOTTOM_RANGE = 7;
    public final static int COFFEE_UPPER_RANGE = 14;
    public final static int COFFEE_WIDTH = 30;
    public final static int COFFEE_HEIGHT = 75;
    public final static int COFFEE_BASE_VELOCITY = 250;


    /** ANVIL CONSTS */
    public final static int ANVIL_DELAY_SPAWN = 5;
    public final static int ANVIL_BOTTOM_RANGE = 1;
    public final static int ANVIL_UPPER_RANGE = 7;
    public final static int ANVIL_WIDTH = 80;
    public final static int ANVIL_HEIGHT = 55;
    public final static int ANVIL_BASE_VELOCITY = 300;

    /** BANANA CONSTS */
    public final static int BANANA_DELAY_SPAWN = 3;
    public final static int BANANA_BOTTOM_RANGE = 3;
    public final static int BANANA_UPPER_RANGE = 7;
    public final static int BANANA_WIDTH = 45;
    public final static int BANANA_HEIGHT = 30;
    public final static int BANANA_BASE_VELOCITY = 215;

    /** STONE CONSTS */
    public final static int STONE_DELAY_SPAWN = 3;
    public final static int STONE_BOTTOM_RANGE = 1;
    public final static int STONE_UPPER_RANGE = 7;
    public final static int STONE_WIDTH = 55;
    public final static int STONE_HEIGHT = 80;
    public final static int STONE_BASE_VELOCITY = 300;

    /** DISTANCE BETWEEN INDICATOR AND LABEl */
    public final static int SPACE_BETWEEN_INDICATOR_AND_LABEL = 30;

    /** DISTANCE BETWEEN INDICATORS */
    public final static int SPACE_BETWEEN_INDICATORS = 70;

    /** DRUNK INDICATOR CONSTS */
    public final static int DRUNKINDICATOR_X = 550;
    public final static int DRUNKINDICATOR_Y = 120;
    public final static int DRUNKINDICATOR_WIDTH = 75;
    public final static int DRUNKINDICATOR_HEIGHT = 100;;

    /** COFFEE INDICATOR CONSTS */
    public final static int COFFEEINDICATOR_X = 550;
    public final static int COFFEEINDICATOR_Y = DRUNKINDICATOR_Y + DRUNKINDICATOR_WIDTH + SPACE_BETWEEN_INDICATORS;
    public final static int COFFEEINDICATOR_WIDTH = 75;
    public final static int COFFEEINDICATOR_HEIGHT = 100;

    /** LIANATILE ENTITY CONSTS */
    public final static int LIANATILE_WIDTH = 50;
    public final static int LIANATILE_HEIGHT = 120;
    public final static int LIANATILE_VELOCITY = 250;

    /** LIANAMANAGER CONSTS */
    public static final int LIANAMANAGER_FIRST_X = 150;
    public static final int LIANAMANAGER_SECOND_X = 300;
    public static final int LIANAMANAGER_THIRD_X = 450;

    /** PLAYER ANIMATION CONSTS */
    public static final int FRAME_WIDTH = 260;
    public static final int FRAME_HEIGHT = 240;


    /** „BAD” TIMER CONSTS */
    public static final double BAD_TIMER_RATIO = 2;

    /** „GOOD” TIMER CONSTS */
    public static final double GOOD_TIMER_RATIO = 1.5;

    /** TAP IMAGE */

    public static final int TAP_STARTING_X = 300;
    public static final int TAP_STARTING_Y = 200;
    public static final int TAP_WIDTH = 60;
    public static final int TAP_HEIGHT = 85;

    /** TAP LABEL */
    public static final int TAPLABEL_STARTING_X = 100;
    public static final int TAPLABEL_STARTING_Y = 350;

}
