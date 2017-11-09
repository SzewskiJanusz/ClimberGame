package com.janusz.climbergame.game.managers;

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
    private static EnergyBar eb = null;
    private final int starting_width = ClimberGame.WIDTH / 2 - 2*22;
    private final int starting_height = 65 - 24;

    private final int starting_x = ClimberGame.WIDTH / 3 + 22;
    private final int starting_y = ClimberGame.HEIGHT - starting_height - 10 - 5;

    public static int actualEnergy;

    private Texture texture;

    public static EnergyBar get() {
        if(eb == null) {
            eb = new EnergyBar();
        }
        return eb;
    }

    private EnergyBar()
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

        if (Player.place == 0)
        {
            actualEnergy -= (int)(starting_width * 0.005);
        }

        if (actualEnergy <= 0 && Player.place == 0)
        {
            Player.place = 1;
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
