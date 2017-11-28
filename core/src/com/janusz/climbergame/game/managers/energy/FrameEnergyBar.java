package com.janusz.climbergame.game.managers.energy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.janusz.climbergame.ClimberGame;

/**
 * Created by Janusz on 2017-09-25.
 */

public class FrameEnergyBar extends Image
{
    private final int width = ClimberGame.WIDTH / 3;
    private final int height = 65;

    private final int x = 0 ;
    private final int y = ClimberGame.HEIGHT - height;

    private Texture texture;

    public FrameEnergyBar()
    {
        this.setSize(width, height);
        this.setOrigin(width / 2 , height / 2);
        this.setPosition(x , y );

        createTexture();
    }

    private void createTexture() {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GOLD);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
