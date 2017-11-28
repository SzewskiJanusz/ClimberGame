package com.janusz.climbergame.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.janusz.climbergame.ClimberGame;

/**
 * Abstract screen holding methods for screens used in this project
 */

public abstract class AbstractScreen implements Screen {

    protected ClimberGame game;

    public static Stage stage;

    protected SpriteBatch spriteBatch;

    private OrthographicCamera camera;

    /**
     * Initialization of every object in screen
     */
    protected abstract void init();

    public AbstractScreen(ClimberGame game)
    {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(ClimberGame.WIDTH, ClimberGame.HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        init();
    }

    private void createCamera()
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ClimberGame.WIDTH, ClimberGame.HEIGHT);
        camera.update();
    }

    @Override
    public void render(float delta)
    {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    private void clearScreen()
    {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {

    }
    @Override
    public void resume()
    {
        game.setPaused(false);
    }
    @Override
    public void pause()
    {
        game.setPaused(true);
    }
    @Override
    public void dispose()
    {
        game.dispose();
    }
    @Override
    public void resize(int width, int height)
    {

    }@Override
    public void hide()
    {

    }
}
