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
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.xemplar.games.android.nerdshooter.controller.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.xemplar.games.android.nerdshooter.items.*;
import com.xemplar.games.android.nerdshooter.model.*;
import com.xemplar.games.android.nerdshooter.view.*;
import com.xemplar.games.android.nerdshooter.*;
import com.xemplar.games.android.nerdshooter.utils.*;

public class GameScreen implements Screen, InputProcessor {
	public static boolean useGameDebugRenderer = false;
    public static GameScreen instance;
    public static long gameTicks = 0L;
	private static TextureAtlas atlas;
	private Rectangle left, right, jump;
    public World world;
    public float buttonSize = 0F;
    
    private Array<Block> blocks;
    private static int levelNum;
    
    private WorldRenderer renderer;
    private JaxonController controller;
	private ShapeRenderer button;
	private SpriteBatch batch;
	private BitmapFont font;
    private int width, height;
	
	private TextureRegion controlLeft;
	private TextureRegion controlRight;
	private TextureRegion controlUp;
	
	public GameScreen(int level){
        instance = this;
        
        levelNum = level;
        
		atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.pack"));
		
		font = new BitmapFont();
		
        if(level == -1){
            GameScreen.useGameDebugRenderer = true;
        } else {
            GameScreen.useGameDebugRenderer = false;
        }
        
		controlLeft = atlas.findRegion("HUDLeft");
		controlRight = atlas.findRegion("HUDRight");
		controlUp = atlas.findRegion("HUDJump");
		
		world = new World(level);
		blocks = world.getBlocks(world.getLevel().getWidth(), world.getLevel().getHeight());
        
		button = new ShapeRenderer();
		batch = new SpriteBatch();
		
		left = new Rectangle();
		right = new Rectangle();
		
		jump = new Rectangle();
	}
	
    public void show() {
        gameTicks = 0L;
        
        renderer = new WorldRenderer(world, useGameDebugRenderer);
        controller = new JaxonController(world);
        controller.reset();
        Gdx.input.setInputProcessor(this);
        
        NerdShooter.shooter.setCurrentScreen(NerdShooter.GAME_SCREEN);
    }
	
    public void render(float delta) {
		gameTicks++;
		
        long seconds = (long)((gameTicks / 60D) * 10L);
        
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);
		
        controller.update(delta);
		updateEntities(delta);
        renderer.render();
		
        button.begin(ShapeRenderer.ShapeType.Filled);{
            if (Gdx.app.getType().equals(Application.ApplicationType.Android)){
                Gdx.gl.glEnable(Gdx.gl20.GL_BLEND);
                Gdx.gl.glBlendFunc(Gdx.gl20.GL_SRC_ALPHA, Gdx.gl20.GL_ONE_MINUS_SRC_ALPHA);

                button.setColor(0.0F, 0.5F, 0.5F, 0.5F);
                button.rect(left.x, left.y, left.width, left.height);
                button.rect(right.x, right.y, right.width, right.height);

                button.setColor(1.0F, 1.0F, 1.0F, 0.5F);
                button.rect(jump.x, jump.y, jump.width, jump.height);
            }
            
            world.getJaxon().inventory.renderGUI(button, width, height, buttonSize * 0.75F);
        } button.end();
        
        batch.begin(); {
            if (Gdx.app.getType().equals(Application.ApplicationType.Android)){
                batch.draw(controlLeft, left.x, left.y, left.width, left.height);
                batch.draw(controlRight, right.x, right.y, right.width, right.height);
				batch.draw(controlUp, jump.x, jump.y, jump.width, jump.height);
            }
            
            world.getJaxon().inventory.renderItems(batch, width, height, buttonSize * 0.75F);
            font.draw(batch, "Time: " + (seconds / 10D) + " ticks, FPS: " + Gdx.graphics.getFramesPerSecond(), 0, height - 10);
        } batch.end();
    }
	
    public static void finishLevel(int code){
        XPMLItem item = new XPMLItem("data");
        item.addElement(CompletedLevel.KEY_COMPLETED_TIME, (long)((gameTicks / 60D) * 10L) + "");
        item.addElement(CompletedLevel.KEY_FINISH_TYPE, code + "");
        item.addElement(CompletedLevel.KEY_LEVEL_NUM, levelNum + "");
        
        InterScreenData.getInstance(NerdShooter.COMP_DATA).setData(item);
        
        NerdShooter.shooter.setScreen(CompletedLevel.instance);
    }
    
    public void resize(int width, int height) {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
		buttonSize = height / 6F;
		
		left.set(buttonSize / 2F, buttonSize / 2F, buttonSize, buttonSize);
		right.set(buttonSize * 2, buttonSize / 2F, buttonSize, buttonSize);
		
		jump.set(width - (buttonSize * 3/2), buttonSize / 2F, buttonSize, buttonSize);
    }
	
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
	
    public void pause() {
		
    }
	
    public void resume() {
        NerdShooter.shooter.setScreen(StartScreen.instance);
    }
	
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
	
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT)
		    controller.leftPressed(-1);
			
        if (keycode == Keys.RIGHT)
			controller.rightPressed(-1);
			
        if (keycode == Keys.Z)
			controller.jumpPressed(-1);
		
        if (keycode == Keys.X)
			controller.firePressed(-1);
	    
        if ((keycode == Keys.BACK) || (keycode == Keys.ESCAPE)){
            finishLevel(ExitBlock.EXIT_NOCLEAR);
        }
            
        return true;
    }
	
    public boolean keyUp(int keycode) {
        if (keycode == Keys.LEFT)
		    controller.leftReleased();
			
        if (keycode == Keys.RIGHT)
		    controller.rightReleased();
		
        if (keycode == Keys.Z)
		    controller.jumpReleased();
		
        if (keycode == Keys.X)
		    controller.fireReleased();
            
        return true;
    }
	
    public boolean keyTyped(char character) {
        return false;
    }
	
	
    public boolean touchDown(int x, int y, int pointer, int button) {
		if(Gdx.app.getType().equals(Application.ApplicationType.Android)){
        	if(left.contains(x, (y - height) * -1)){
				controller.leftPressed(pointer);
			}
			
			if(right.contains(x, (y - height) * -1)){
				controller.rightPressed(pointer);
			}
		
			if(jump.contains(x, (y - height) * -1)){
				controller.jumpPressed(pointer);
			}
		
			return true;
		}
		return false;
    }
	
    public boolean touchUp(int x, int y, int pointer, int button) {
		if(Gdx.app.getType().equals(Application.ApplicationType.Android)){
        	if(left.contains(x, (y - height) * -1)) {
            	controller.leftReleased();
        	}
		
        	if(right.contains(x, (y - height) * -1)) {
            	controller.rightReleased();
        	}
		
			if(jump.contains(x, (y - height) * -1)){
				controller.jumpReleased();
			}
        	return true;
		}
		return false;
    }
	
    public boolean touchDragged(int x, int y, int pointer) {
		if(Gdx.app.getType().equals(Application.ApplicationType.Android)){
			if(controller.isLeftDown() && !left.contains(x, (y - height) * -1) && controller.leftPointer == pointer) {
            	controller.leftReleased();
        	}
			if(controller.isRightDown() && !right.contains(x, (y - height) * -1) && controller.rightPointer == pointer) {
            	controller.rightReleased();
        	}
			if(controller.isJumpDown() && !jump.contains(x, (y - height) * -1) && controller.jumpPointer == pointer) {
        	    controller.jumpReleased();
        	}
			
			if(!controller.isLeftDown() && left.contains(x, (y -height) * -1) && controller.leftPointer == -1){
				controller.leftPressed(pointer);
			}
			
			if(!controller.isRightDown() && right.contains(x, (y -height) * -1) && controller.rightPointer == -1){
				controller.rightPressed(pointer);
			}
			
			if(!controller.isJumpDown() && jump.contains(x, (y -height) * -1) && controller.jumpPointer == -1){
				controller.jumpPressed(pointer);
			}
        	return true;
		}
		return false;
    }
	
    public boolean mouseMoved(int x, int y) {
        return false;
    }
    
    public boolean scrolled(int amount) {
        return false;
    }
	
	public void updateEntities(float delta){
		for(Entity e : world.getEntities()){
			e.update(delta);
		}
	}
	
	public static TextureAtlas getTextureAltlas(){
		return atlas;
	}
}
