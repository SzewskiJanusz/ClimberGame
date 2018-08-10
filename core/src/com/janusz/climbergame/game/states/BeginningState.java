package com.janusz.climbergame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.Const;
import com.janusz.climbergame.game.background.GameBackground;
import com.janusz.climbergame.game.environment.EntireLiana;
import com.janusz.climbergame.game.screens.GameScreen;
import com.janusz.climbergame.shared.DefComponents;

public class BeginningState implements GameState
{
    public GameScreen gameScreen;
    public EntireLiana allLianas;
    private ClimberGame game;
    GameBackground gb;
    Label taptostart;
    Image handImg;

    public BeginningState(GameScreen gs, ClimberGame game)
    {
        this.gameScreen = gs;
        this.game = game;
        init();
        allLianas = new EntireLiana(gs);
    }

    public void init()
    {
        gb = new GameBackground();
        gb.toBack();
        gameScreen.stage.addActor(gb);
        Label tapToStart = initStartingLabel();
        gameScreen.stage.addActor(tapToStart);
        Image handImg = initHandImage();
        gameScreen.stage.addActor(handImg);
    }

    private Label initStartingLabel()
    {
        taptostart = new Label("TAP TO START", DefComponents.LABEL_STYLE);
        taptostart.setFontScale(0.6f);
        taptostart.setX(Const.TAPLABEL_STARTING_X);
        taptostart.setY(Const.TAPLABEL_STARTING_Y);
        return taptostart;
    }

    private Image initHandImage()
    {
        handImg = new Image(new Texture("tap.png"));
        handImg.setSize(Const.TAP_WIDTH, Const.TAP_HEIGHT);
        handImg.setPosition(Const.TAP_STARTING_X, Const.TAP_STARTING_Y);
        return handImg;
    }

    @Override
    public void tick(float delta)
    {
        if (Gdx.input.isTouched())
        {
            startGame();
        }
        update(delta);
    }

    private void startGame()
    {
        handImg.remove();
        taptostart.remove();
        gameScreen.setState(new PlayGameState(this, game));
    }

    @Override
    public void update(float delta)
    {
        allLianas.moveAllLianasDown(delta);
        handImg.toFront();
        taptostart.toFront();
    }
}
