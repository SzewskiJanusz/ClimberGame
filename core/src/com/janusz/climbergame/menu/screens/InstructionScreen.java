package com.janusz.climbergame.menu.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.janusz.climbergame.ClimberGame;
import com.janusz.climbergame.game.background.GameBackground;
import com.janusz.climbergame.shared.AbstractScreen;
import com.janusz.climbergame.shared.DefComponents;
import com.janusz.climbergame.shared.factories.ButtonFactory;

public class InstructionScreen extends AbstractScreen
{
    public InstructionScreen(ClimberGame game)
    {
        super(game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK))
        {
            game.setScreen(new MenuScreen(game,1f));
        }
        stage.draw();
    }

    @Override
    protected void init()
    {
        GameBackground gameBackground = new GameBackground();
        stage.addActor(gameBackground);
        Label howToPlay = initTitle();
        Label[] instructions = new Label[5];
        String[] instructionText = {
                "COLLECT FRUITS AND VEGETABLES TO GET POINTS",
                "JUNK FOOD MAKES YOU SLOWER",
                "ALCOHOL REVERSES YOUR MOVEMENT",
                "COFFEE MAKES YOU FASTER",
                "UPLOAD YOUR SCORE AND BE THE BEST!"
        };
        for(int i = 0 ; i < 5 ; i++)
        {
            instructions[i] = initInstruction(instructionText[i], i);
            stage.addActor(instructions[i]);
        }
        TextButton backToMenu = initBackButton();
        TextButton about = initAboutButton();
        stage.addActor(howToPlay);
        stage.addActor(backToMenu);
        stage.addActor(about);
    }

    private Label initTitle()
    {
        Label title = new Label("HOW TO PLAY", DefComponents.LABEL_STYLE_ORANGE);
        title.setColor(Color.YELLOW);
        title.setFontScale(0.9f,0.9f);
        title.setAlignment(Align.top);
        title.setPosition(100, ClimberGame.HEIGHT - 100);
        return title;
    }

    private Label initInstruction(String s, int i)
    {
        Label instruction = new Label(s, DefComponents.LABEL_STYLE);
        instruction.setColor(Color.RED);
        instruction.setFontScale(0.2f, 0.2f);
        instruction.setPosition(140, ClimberGame.HEIGHT - 200 - i*40);
        return instruction;
    }

    private TextButton initBackButton()
    {
        TextButton back = ButtonFactory.createButton("MENU");
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new MenuScreen(game));
            }
        });
        back.setSize(269, 50);
        back.setPosition(50, 50);
        return back;
    }

    private TextButton initAboutButton()
    {
        TextButton about = ButtonFactory.createButton("ABOUT");
        about.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new AboutScreen(game));
            }
        });
        about.setSize(269, 50);
        about.setPosition(ClimberGame.WIDTH - 269 - 50, 50);
        return about;
    }
}
