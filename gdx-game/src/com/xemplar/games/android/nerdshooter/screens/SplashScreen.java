/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
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
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.xemplar.games.android.nerdshooter.NerdShooter.gen;
import static com.xemplar.games.android.nerdshooter.NerdShooter.params;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Timer;
import com.xemplar.games.android.nerdshooter.NerdShooter;

public class SplashScreen implements Screen {
	private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+,<.>/?";
    private float width, logoSize, logoX;
    private SpriteBatch batch;
    private Texture logo;
    private BitmapFont label;
    
    public SplashScreen(){
        batch = new SpriteBatch();
        
        gen = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
        params = new FreeTypeFontParameter();
        
        NerdShooter.label = label = gen.generateFont(params);
        NerdShooter.label_small = gen.generateFont(params);
        NerdShooter.text = gen.generateFont(params);
        
        FileHandle handle = Gdx.files.internal("logo.png");
        logo = new Texture(handle);
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1F);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
		
        float line = label.getLineHeight();
        
        batch.begin();{
            batch.draw(logo, logoX, logoSize, logoSize, logoSize);
            label.draw(batch, "XEMPLAR\n    &\n  CRYOGENERIX", width / 10F, (line * 3 + 5));
        } batch.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        
        params.characters = chars;
        params.shadowOffsetX = 1;
        params.shadowOffsetY = 1;
        
        params.size = (int) (((float)height / NerdShooter.BUTTON_HEIGHT) / 2);
        params.color = new Color(0, 0, 0, 1);
        NerdShooter.label = gen.generateFont(params);
        
        label = gen.generateFont(params);
        label.setColor(0, 0, 0, 1);
        
        params.size = (int) (((float)height / NerdShooter.BUTTON_HEIGHT) / 3);
        params.color = new Color(1, 1, 1, 1);
        NerdShooter.text = gen.generateFont(params);
        
        params.color = new Color(0, 0, 0, 1);
        NerdShooter.label_small = gen.generateFont(params);
        
        NerdShooter.atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.atlas"));
        
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
