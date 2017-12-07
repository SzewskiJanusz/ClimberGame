package com.janusz.climbergame.game.managers.energy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.entities.player.Player;

/**
 * Created by Janusz on 2017-09-25.
 */

public class EnergyBar extends Image
{
    private final int starting_width = ClimberGame.WIDTH / 3 - 2*22;
    private final int starting_height = 41;

    private final int starting_x = 22;
    private final int starting_y = ClimberGame.HEIGHT - starting_height - 15;

    public static int actualEnergy;

    private Texture texture;


    public EnergyBar()
    {
        this.setSize(starting_width, starting_height);
        this.setOrigin(starting_width / 2 , starting_height / 2);
        this.setPosition(starting_x , starting_y );
        actualEnergy = starting_width;
        createTexture();
    }

    private void createTexture() {
        Pixmap pixmap = new Pixmap(starting_width, starting_height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(0, 0, actualEnergy, starting_height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        if (Player.instance().place == 0)
        {
            actualEnergy -= (int)(starting_width * 0.01);
        }

        if (actualEnergy <= 0 && Player.instance().place == 0)
        {
            Player.instance().place = 1;
            actualEnergy = 0;
        }


        batch.draw(texture, getX(), getY(), actualEnergy, getHeight());
    }


    public void addEnergy()
    {
        actualEnergy += 100;

        if (actualEnergy > starting_width)
        {
            actualEnergy = starting_width;
        }
    }
}
