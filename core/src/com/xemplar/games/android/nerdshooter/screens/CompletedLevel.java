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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.blocks.ExitBlock;
import com.xemplar.games.android.nerdshooter.screens.ui.Button;
import com.xemplar.games.android.nerdshooter.utils.InterScreenData;
import com.xemplar.games.android.nerdshooter.utils.XPMLItem;

public class CompletedLevel implements Screen, InputProcessor {
    public static final String KEY_COMPLETED_TIME = "comp";
    public static final String KEY_FINISH_TYPE = "type";
    public static final String KEY_LEVEL_NUM = "level";
    
    public static CompletedLevel instance;
    
    private double completed;
    private int level;
    private String message;
    private boolean set = false;
    
    private Button replay;
    private Button menu;
    
    private BitmapFont text;
    private float buttonHeight;
    
    private int width, height;
    private SpriteBatch batch;
    
    private Texture rickmas;
    
    public CompletedLevel(){
    	rickmas = new Texture(Gdx.files.internal("rickmas.png"));
        instance = this;
    }
    
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
		
        NerdShooter.layout.setText(text, message);
        float font = NerdShooter.layout.width;
        
        batch.begin();{
            text.draw(batch, message, (width / 2F) - (font / 2F), height - text.getCapHeight());
            
            float width = buttonHeight * 10;
            float height = buttonHeight * 4;
            
            if(NerdShooter.sanic){
            	batch.draw(rickmas, (this.width / 2F) - (width / 2F), (this.height / 2F) - (height / 4F), width, height);
            }
            
            replay.render(batch);
            menu.render(batch);
        } batch.end();
    }

    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        
        float spacer = 10F;
        float buttonWidth = (width * (3F / 4F));
        buttonHeight = height / 9F;
        
        text = NerdShooter.label;
        text.setColor(0, 0, 0, 1);
        
        set = false;

        replay = new Button(NerdShooter.label, "Replay", (width / 2F) - (buttonWidth / 2F), buttonHeight + spacer, buttonWidth, buttonHeight);
        menu = new Button(NerdShooter.label, "Menu", (width / 2F) - (buttonWidth / 2F), replay.y + spacer + buttonHeight, buttonWidth, buttonHeight);
    }

    public void show() {
        Object det = InterScreenData.getInstance(NerdShooter.COMP_DATA).getData();

        if(det != null){
            XPMLItem item = (XPMLItem) det;
            completed = Long.parseLong(item.getElementValue(KEY_COMPLETED_TIME)) / 10D;
            int finishType = Integer.parseInt(item.getElementValue(KEY_FINISH_TYPE));
            level = Integer.parseInt(item.getElementValue(KEY_LEVEL_NUM));

            switch(finishType){
            case ExitBlock.EXIT_NOCLEAR:
                message = "Didn't Finish Level " + level;
                break;
            case ExitBlock.EXIT_NORMAL:
                message = "Completed Level " + level + " in\n" + completed + " seconds.";
                break;
            default:
                message = "Hacker";
                break;
            }
        }
        
        batch = new SpriteBatch();
        
        Gdx.input.setInputProcessor(this);
    }

    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void pause() {
        
    }

    public void resume() {
        
    }

    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
    
    public boolean touchDown(int pX, int pY, int pointer, int button) {
    	set = true;
    	
        float x = pX;
        float y = height - pY;

        boolean value = false;

        if(replay.isInside(x, y)){
            replay.setPressed(true);
            value |= true;
        }
        
        if(menu.isInside(x, y)){
            menu.setPressed(true);
            value |= true;
        }
        
        return value;
    }

    public boolean touchUp(int pX, int pY, int pointer, int button) {
        float x = pX;
        float y = height - pY;

        boolean value = false;

        if(replay.isInside(x, y) && set){
            replay.setPressed(false);
            NerdShooter.shooter.setScreen(new GameScreen(level));
            value |= true;
        }

        if(menu.isInside(x, y) && set){
            menu.setPressed(false);
            NerdShooter.shooter.setScreen(StartScreen.instance);
            value |= true;
        }

        return value;
    }

    public boolean touchDragged(int pX, int pY, int pointer) {
        float x = pX;
        float y = height - pY;

        replay.setPressed(replay.isInside(x, y));
        menu.setPressed(menu.isInside(x, y));
        
        return false;
    }

    public boolean mouseMoved(int p1, int p2) { return touchDragged(p1, p2, 0); }
    public boolean scrolled(int p1) { return false; }
    public boolean keyDown(int keycode) {
        if ((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)){
            Gdx.app.exit();
        }

        return false;
    }
    public boolean keyUp(int p1) { return false; }
	public boolean keyTyped(char p1) { return false; }
}
