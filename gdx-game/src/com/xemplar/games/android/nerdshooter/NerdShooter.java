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

