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
    public Texture lianatile = new Texture("liana.png");
    public Texture satelite = new Texture("satellite.png");
    public Texture stone = new Texture("stone.png");
    public Texture tequila = new Texture("tequila.png");
    public Texture trashcan = new Texture("trashcan.png");
    public Texture treasure = new Texture("treasure.png");
    public Texture watermelon = new Texture("watermelon.png");
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
        lianatile.dispose();
        satelite.dispose();
        stone.dispose();
        tequila.dispose();
        trashcan.dispose();
        treasure.dispose();
        watermelon.dispose();
    }
}
