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
package com.xemplar.games.android.nerdshooter.blocks;

import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.items.Item;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class Block {
	protected static final Vector2 empty = new Vector2(2, 2);
	//Block Definitions
		//Grass
		public static Block grass = new Block(empty, "grass");
		public static Block grass_mid = new Block(empty, "grassMid");
		public static Block grass_left = new Block(empty, "grassLeft");
		public static Block grass_right = new Block(empty, "grassRight");
		public static Block grass_center = new Block(empty, "grassCenter");
		
		public static Block grass_cliff_left = new Block(empty, "grassCliffLeft");
		public static Block grass_cliff_right = new Block(empty, "grassCliffRight");
		public static Block grass_cliff_left_a = new Block(empty, "grassCliffLeftAlt");
		public static Block grass_cliff_right_a = new Block(empty, "grassCliffRightAlt");
		
		public static SlabBlock grass_half = new SlabBlock(empty, "grassHalf", 1F, 0.5F, true);
		public static SlabBlock grass_half_mid = new SlabBlock(empty, "grassHalfMid", 1F, 0.5F, true);
		public static SlabBlock grass_half_left = new SlabBlock(empty, "grassHalfLeft", 1F, 0.5F, true);
		public static SlabBlock grass_half_right = new SlabBlock(empty, "grassHalfRight", 1F, 0.5F, true);
	
		//stone
		public static Block stone = new Block(empty, "stone");
		public static Block stone_mid = new Block(empty, "stoneMid");
		public static Block stone_left = new Block(empty, "stoneLeft");
		public static Block stone_right = new Block(empty, "stoneRight");
		public static Block stone_center = new Block(empty, "stoneCenter");
				
		public static Block stone_cliff_left = new Block(empty, "stoneCliffLeft");
		public static Block stone_cliff_right = new Block(empty, "stoneCliffRight");
		public static Block stone_cliff_left_a = new Block(empty, "stoneCliffLeftAlt");
		public static Block stone_cliff_right_a = new Block(empty, "stoneCliffRightAlt");
				
		public static SlabBlock stone_half = new SlabBlock(empty, "stoneHalf", 1F, 0.5F, true);
		public static SlabBlock stone_half_mid = new SlabBlock(empty, "stoneHalfMid", 1F, 0.5F, true);
		public static SlabBlock stone_half_left = new SlabBlock(empty, "stoneHalfLeft", 1F, 0.5F, true);
		public static SlabBlock stone_half_right = new SlabBlock(empty, "stoneHalfRight", 1F, 0.5F, true);
		
		//castle
		public static Block castle = new Block(empty, "castle");
		public static Block castle_mid = new Block(empty, "castleMid");
		public static Block castle_left = new Block(empty, "castleLeft");
		public static Block castle_right = new Block(empty, "castleRight");
		public static Block castle_center = new Block(empty, "castleCenter");
		
		public static Block castle_cliff_left = new Block(empty, "castleCliffLeft");
		public static Block castle_cliff_right = new Block(empty, "castleCliffRight");
		public static Block castle_cliff_left_a = new Block(empty, "castleCliffLeftAlt");
		public static Block castle_cliff_right_a = new Block(empty, "castleCliffRightAlt");
		
		public static SlabBlock castle_half = new SlabBlock(empty, "castleHalf", 1F, 0.5F, true);
		public static SlabBlock castle_half_mid = new SlabBlock(empty, "castleHalfMid", 1F, 0.5F, true);
		public static SlabBlock castle_half_left = new SlabBlock(empty, "castleHalfLeft", 1F, 0.5F, true);
		public static SlabBlock castle_half_right = new SlabBlock(empty, "castleHalfRight", 1F, 0.5F, true);
	
		//snow
		public static Block snow = new Block(empty, "snow");
		public static Block snow_mid = new Block(empty, "snowMid");
		public static Block snow_left = new Block(empty, "snowLeft");
		public static Block snow_right = new Block(empty, "snowRight");
		public static Block snow_center = new Block(empty, "snowCenter");
		
		public static Block snow_cliff_left = new Block(empty, "snowCliffLeft");
		public static Block snow_cliff_right = new Block(empty, "snowCliffRight");
		public static Block snow_cliff_left_a = new Block(empty, "snowCliffLeftAlt");
		public static Block snow_cliff_right_a = new Block(empty, "snowCliffRightAlt");
				
		public static SlabBlock snow_half = new SlabBlock(empty, "snowHalf", 1F, 0.5F, true);
		public static SlabBlock snow_half_mid = new SlabBlock(empty, "snowHalfMid", 1F, 0.5F, true);
		public static SlabBlock snow_half_left = new SlabBlock(empty, "snowHalfLeft", 1F, 0.5F, true);
		public static SlabBlock snow_half_right = new SlabBlock(empty, "snowHalfRight", 1F, 0.5F, true);
			
		//dirt
		public static Block dirt = new Block(empty, "dirt");
		public static Block dirt_mid = new Block(empty, "dirtMid");
		public static Block dirt_left = new Block(empty, "dirtLeft");
		public static Block dirt_right = new Block(empty, "dirtRight");
		public static Block dirt_center = new Block(empty, "dirtCenter");
				
		public static Block dirt_cliff_left = new Block(empty, "dirtCliffLeft");
		public static Block dirt_cliff_right = new Block(empty, "dirtCliffRight");
		public static Block dirt_cliff_left_a = new Block(empty, "dirtCliffLeftAlt");
		public static Block dirt_cliff_right_a = new Block(empty, "dirtCliffRightAlt");
				
		public static SlabBlock dirt_half = new SlabBlock(empty, "dirtHalf", 1F, 0.5F, true);
		public static SlabBlock dirt_half_mid = new SlabBlock(empty, "dirtHalfMid", 1F, 0.5F, true);
		public static SlabBlock dirt_half_left = new SlabBlock(empty, "dirtHalfLeft", 1F, 0.5F, true);
		public static SlabBlock dirt_half_right = new SlabBlock(empty, "dirtHalfRight", 1F, 0.5F, true);
			
		//sand
		public static Block sand = new Block(empty, "sand");
		public static Block sand_mid = new Block(empty, "sandMid");
		public static Block sand_left = new Block(empty, "sandLeft");
		public static Block sand_right = new Block(empty, "sandRight");
		public static Block sand_center = new Block(empty, "sandCenter");
		
		public static Block sand_cliff_left = new Block(empty, "sandCliffLeft");
		public static Block sand_cliff_right = new Block(empty, "sandCliffRight");
		public static Block sand_cliff_left_a = new Block(empty, "sandCliffLeftAlt");
		public static Block sand_cliff_right_a = new Block(empty, "sandCliffRightAlt");
		
		public static SlabBlock sand_half = new SlabBlock(empty, "sandHalf", 1F, 0.5F, true);
		public static SlabBlock sand_half_mid = new SlabBlock(empty, "sandHalfMid", 1F, 0.5F, true);
		public static SlabBlock sand_half_left = new SlabBlock(empty, "sandHalfLeft", 1F, 0.5F, true);
		public static SlabBlock sand_half_right = new SlabBlock(empty, "sandHalfRight", 1F, 0.5F, true);
			
		//Misc
		public static DeathBlock misc_lava = new DeathBlock(empty, "lava");
		public static SlabDeathBlock misc_lava_top = new SlabDeathBlock(empty, "lavaTop", 1F, 0.5F, false);
		public static SlabDeathBlock misc_lava_mid = new SlabDeathBlock(empty, "lavaTop_mid", 1F, 0.5F, false);
		
		public static Block misc_water = new Block(empty, "water");
		public static SlabBlock misc_water_top = new SlabBlock(empty, "waterTop", 1F, 0.5F, false);
		public static SlabBlock misc_water_mid = new SlabBlock(empty, "waterTop_mid", 1F, 0.5F, false);
		
		public static ItemBlock key_blue = new ItemBlock(empty, 0.5F, Item.BLUE_KEY.clone());
		public static ItemBlock key_red = new ItemBlock(empty, 0.5F, Item.RED_KEY.clone());
		public static ItemBlock key_green = new ItemBlock(empty, 0.5F, Item.GREEN_KEY.clone());
		public static ItemBlock key_yellow = new ItemBlock(empty, 0.5F, Item.YELLOW_KEY.clone());
		
		public static LockBlock lock_blue = new LockBlock(empty, "lock_blue", Item.BLUE_KEY);
		public static LockBlock lock_red = new LockBlock(empty, "lock_red", Item.RED_KEY);
		public static LockBlock lock_green = new LockBlock(empty, "lock_green", Item.GREEN_KEY);
		public static LockBlock lock_yellow = new LockBlock(empty, "lock_yellow", Item.YELLOW_KEY);
		
		public static ExitBlock exit = new ExitBlock(empty, "exit", ExitBlock.EXIT_NORMAL);
		
	//Start Class
	public long id = 0L;
	protected static Pixmap mapR = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
	protected static Pixmap mapY = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
	protected static Pixmap mapB = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
	
	protected static TextureRegion texR;
	protected static TextureRegion texY;
	protected static TextureRegion texB;
	
	static{
		mapR.setColor(1, 0.5F, 1F, 1);
		mapR.fill();
		
		texR = new TextureRegion(new Texture(mapR));
		
		mapY.setColor(1, 1, 0.5F, 1);
		mapY.fill();
		
		texY = new TextureRegion(new Texture(mapY));
		
		mapB.setColor(0.5F, 0.5F, 1, 1);
		mapB.fill();
		
		texB = new TextureRegion(new Texture(mapB));
	}
	
	public String regionID;
	
    protected Vector2 position = new Vector2();
	protected Vector2 spawnPoint = new Vector2();
    protected Rectangle bounds = new Rectangle();

    protected Block(Vector2 pos, String regionID) {
		this.regionID = regionID;
		this.spawnPoint = new Vector2(pos);
        this.position = pos;
		this.bounds.setPosition(pos);
        this.bounds.width = 1F;
        this.bounds.height = 1F;
        
        id = (new Random()).nextLong();
    }
    
    protected Block(Vector2 pos, String regionID, float size) {
        this(pos, regionID);
        this.bounds.width = new Float(size);
        this.bounds.height = new Float(size);
        

        id = (new Random()).nextLong();
    }
    
    protected Block(Vector2 pos, String regionID, float width, float height) {
        this(pos, regionID);
        this.bounds.width = new Float(width);
        this.bounds.height = new Float(height);
        
        id = (new Random()).nextLong();
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
		if(!NerdShooter.sanic){
			return GameScreen.getTextureAltlas().findRegion(regionID);
		} 
		
		int ran = MathUtils.random(3);
		switch(ran){
		case 1:
			return texB;
		case 2:
			return texY;
		default:
			return texR;
		}
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
    
	public Block clone(Vector2 pos){
		Block b = new Block(pos, regionID, this.bounds.getWidth(), this.bounds.getWidth());
		return b;
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

