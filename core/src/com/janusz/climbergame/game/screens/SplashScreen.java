package com.janusz.climbergame.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.menu.Title;
import com.janusz.climbergame.menu.screens.MenuScreen;


/**
 * Created by Janusz on 2017-09-27.
 */

public class SplashScreen extends AbstractScreen
{

    private Title title;

    public SplashScreen(final ClimberGame game)
    {
        super(game);

        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run()
                           {
                               // InputProcessor set in MenuScreen constructor because is not
                               //  initialized now.
                               game.setScreen(new MenuScreen(game));
                           }
                       }
                , 2
        );
    }

    @Override
    protected void init()
    {
        title = new Title();
        stage.addActor(title);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        stage.draw();
    }
}
