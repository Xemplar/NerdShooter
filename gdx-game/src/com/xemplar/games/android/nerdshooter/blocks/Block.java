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
package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class Block {
    public static float WIDTH = 1f;
    public static float HEIGHT = 1f;
    
	public String regionID;
	
    protected Vector2 position = new Vector2();
	protected Vector2 spawnPoint = new Vector2();
    protected Rectangle bounds = new Rectangle();

    public Block(Vector2 pos, String regionID) {
		this.regionID = regionID;
		this.spawnPoint = new Vector2(pos);
        this.position = pos;
		this.bounds.setPosition(pos);
        this.bounds.width = WIDTH;
        this.bounds.height = HEIGHT;
    }
    
	public Block(Vector2 pos, String regionID, float size) {
        this.regionID = regionID;
        this.spawnPoint = new Vector2(pos);
        this.position = pos;
		this.bounds.setPosition(pos);
        this.bounds.width = size;
        this.bounds.height = size;
    }
    
    public Block(Vector2 pos, String regionID, float width, float height) {
        this.regionID = regionID;
        this.spawnPoint = new Vector2(pos);
        this.position = pos;
		this.bounds.setPosition(pos);
        this.bounds.width = width;
        this.bounds.height = height;
    }
    
    public float getWidth(){
        return this.bounds.width;
    }
    
    public float getHeight(){
        return this.bounds.height;
    }
    
    public boolean isCollideable(){
        return true;
    }
    
    public boolean isHidden(){
        return false;
    }
    
    public boolean isTouchable(){
        return false;
    }
    
    public boolean isAnimated(){
        return false;
    }
    
    public void onTouch(Entity e){
        
    }
    
	public TextureRegion getTexture(){
		return GameScreen.getTextureAltlas().findRegion(regionID);
	}
	
	public Rectangle getBounds(){
		return bounds;
    }
	
	public Vector2 getPosition(){
		return position;
    }
	
	public Vector2 getSpawnPoint(){
		return spawnPoint;
	}
    
    public void render(SpriteBatch batch, float ppuX, float ppuY){
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY);
        }   
    }
    
    public void render(TextureRegion region, SpriteBatch batch, float ppuX, float ppuY){
        if(!isHidden()){
            batch.draw(region, getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY);
        }   
    }
}

