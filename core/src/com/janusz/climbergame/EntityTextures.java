package com.janusz.climbergame;

import com.badlogic.gdx.graphics.Texture;

/**
 * Singleton with all textures
 */
public class EntityTextures
{
    private static EntityTextures at;

    // Textures
    public Texture apple = new Texture("apple.png");
    public Texture anvil = new Texture("anvil.png");
    public Texture banana = new Texture("banana.png");
    public Texture carrot = new Texture("carrot.png");
    public Texture coffee = new Texture("coffee.png");
    public Texture fries = new Texture("fries.png");
    public Texture grapes = new Texture("grapes.png");
    public Texture pear = new Texture("pear.png");
    public Texture lianatile1 = new Texture("lianapart1.png");
    public Texture lianatile2 = new Texture("lianapart2.png");
    public Texture lianatile3 = new Texture("lianapart3.png");
    public Texture satelite = new Texture("satellite.png");
    public Texture stone = new Texture("stone.png");
    public Texture tequila = new Texture("tequila.png");
    public Texture trashcan = new Texture("trashcan.png");
    public Texture treasure = new Texture("treasure.png");
    public Texture watermelon = new Texture("watermelon.png");
    public Texture pineapple = new Texture("pineapple.png");
    public Texture peach = new Texture("peach.png");
    public Texture mango = new Texture("mango.png");
    public Texture hotdog = new Texture("hotdog.png");
    public Texture hamburger = new Texture("hamburger.png");
    public Texture pomegranate = new Texture("pomegranate.png");
    ////


    public static EntityTextures get()
    {
        if (at == null)
            at = new EntityTextures();

        return at;
    }

    private EntityTextures() {}

    public void dispose()
    {
        apple.dispose();
        anvil.dispose();
        banana.dispose();
        carrot.dispose();
        coffee.dispose();
        fries.dispose();
        grapes.dispose();
        pear.dispose();
        lianatile1.dispose();
        lianatile2.dispose();
        lianatile3.dispose();
        satelite.dispose();
        stone.dispose();
        tequila.dispose();
        trashcan.dispose();
        treasure.dispose();
        watermelon.dispose();
        pineapple.dispose();
        peach.dispose();
        mango.dispose();
        hotdog.dispose();
        hamburger.dispose();
        pomegranate.dispose();
    }
}
