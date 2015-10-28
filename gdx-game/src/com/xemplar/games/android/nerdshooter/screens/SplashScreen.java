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
package com.xemplar.games.android.nerdshooter.screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.*;
import com.xemplar.games.android.nerdshooter.*;

public class SplashScreen implements Screen {
    private float width, height, logoSize, logoX;
    private SpriteBatch batch;
    private Texture logo;
    private BitmapFont text;
    
    public SplashScreen(){
        batch = new SpriteBatch();
        text = new BitmapFont();//Gdx.files.internal("font/digital.fnt"));
        
        FileHandle handle = Gdx.files.internal("logo.png");
        logo = new Texture(handle);
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1F);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		
        float line = text.getLineHeight();
        
        batch.begin();{
            batch.draw(logo, logoX, height - logoSize, logoSize, logoSize);
            text.draw(batch, "Made By Xemplar", width / 10F, line + 5);
        } batch.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        
        this.logoSize = height / 2F;
        this.logoX = (width / 2F) - (logoSize / 2F);
    }
    
    public void show() {
        Timer.Task task = new Timer.Task(){
            public void run() {
                NerdShooter.shooter.setScreen(new StartScreen());
            }
        };
        
        Timer.schedule(task, 1.5F);
    }

    public void hide() {
        // TODO: Implement this method
    }

    public void pause() {
        // TODO: Implement this method
    }

    public void resume() {
        // TODO: Implement this method
    }

    public void dispose() {
        // TODO: Implement this method
    }
}
