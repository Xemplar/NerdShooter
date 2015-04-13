package com.xemplar.games.android.nerdshooter;

import com.badlogic.gdx.Game;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

public class NerdShooter extends Game {
    public static final int START_SCREEN = 0x000001;
    public static final int GAME_SCREEN = 0x000002;
    
    public static final String COMP_DATA = "compData";
    
    public static BitmapFont text;
    
	public static NerdShooter shooter;
    private int screen;
    
    public void create() {
        shooter = this;
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 12;
        text = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        
        setScreen(new SplashScreen());
        new CompletedLevel();
    }
    
    public int getCurrentScreen(){
        return screen;
    }
    
    public void setCurrentScreen(int screen){
        this.screen = screen;
    }
}

