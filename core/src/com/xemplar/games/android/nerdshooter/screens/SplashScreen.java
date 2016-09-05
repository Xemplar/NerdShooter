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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;
import com.xemplar.games.android.nerdshooter.NerdShooter;

public class SplashScreen implements Screen {
    private float width, logoSize, logoX;
    private Timer.Task splash0, splash1;
    private SpriteBatch batch;
    private Texture logo, lts;
    private BitmapFont label;
    private int stage = 0;
    
    public SplashScreen(){
        batch = new SpriteBatch();

        FileHandle handle0 = Gdx.files.internal("logo.png");
        FileHandle handle1 = Gdx.files.internal("lts.png");

        NerdShooter.label = label = new BitmapFont(Gdx.files.internal("font/NerdShooter.fnt"));
        NerdShooter.label_small = new BitmapFont(Gdx.files.internal("font/NerdShooter.fnt"));
        NerdShooter.text = new BitmapFont(Gdx.files.internal("font/NerdShooter.fnt"));

        logo = new Texture(handle0);
        lts = new Texture(handle1);
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1F);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
		
        float line = label.getLineHeight();
        
        batch.begin();{
            if(stage == 0) {
                batch.draw(logo, logoX, logoSize, logoSize, logoSize);
                label.draw(batch, "XEMPLAR\n    &\n  CRYOGENERIX", width / 10F, (line * 3 + 5));
            } else {
                batch.draw(lts, logoX, logoSize, logoSize, logoSize);
                label.draw(batch, "Music By:\n The Living Tombstone", width / 10F, (line * 3 + 5));
            }
        } batch.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        
        float size = (((float)height / NerdShooter.BUTTON_HEIGHT) / 2);
        NerdShooter.label.getData().setScale(size / 128);
        
        size = (((float)height / NerdShooter.BUTTON_HEIGHT) / 3);
        NerdShooter.text.getData().setScale(size / 128);
        NerdShooter.label_small.getData().setScale(size / 128);
        
        NerdShooter.atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.atlas"));
        
        this.logoSize = height / 2F;
        this.logoX = (width / 2F) - (logoSize / 2F);
    }

    public void loadAssets(){

    }

    public void show() {
        splash0 = new Timer.Task(){
            public void run() {
                stage = 1;
                Timer.schedule(splash1, 1.5F);
                loadAssets();
            }
        };
        splash1 = new Timer.Task(){
            public void run() {
                NerdShooter.shooter.setScreen(new StartScreen());
            }
        };

        Timer.schedule(splash0, 1.5F);
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
