package com.janusz.climbergame.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Janusz on 2017-10-02.
 */

public class Title extends Image
{

    public Title()
    {
        super(new Texture("title.png"));
        setPosition(365 , 420 ,1);
        setSize(520,110);
    }

}
