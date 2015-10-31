/*
 * NerdShooter is a pseudo libray project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2015  Rohan Loomis
 *
 * This file is part of NerdShooter
 *
 * NerdShooter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License, or
 * any later version.
 *
 * NerdShooter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.xemplar.games.android.nerdshooter.screens.CompletedLevel;
import com.xemplar.games.android.nerdshooter.screens.SplashScreen;
import com.xemplar.games.android.nerdshooter.utils.InterScreenData;

public class NerdShooter extends Game {
	public static final float BUTTON_HEIGHT = 6F;
	
    public static final int START_SCREEN = 0x000001;
    public static final int GAME_SCREEN = 0x000002;
    
    public static final String COMP_DATA = "compData";
    public Boolean useKeys;
    public int[] keys;
    
    public static BitmapFont label, text;
    public static GlyphLayout layout;
    
    public static FreeTypeFontGenerator gen;
    public static FreeTypeFontParameter params;
    
	public static NerdShooter shooter;
    private int screen;
    
    public void create() {
        shooter = this;
        
        layout = new GlyphLayout();
        
        keys = (int[])InterScreenData.getInstance("desktop_keys").getData();
        if(keys != null){
        	useKeys = true;
        } else {
        	useKeys = false;
        }
        
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

