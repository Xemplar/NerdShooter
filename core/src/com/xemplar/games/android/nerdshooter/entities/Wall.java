/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
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
package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class Wall extends Entity{
	private boolean useNumTextures = false;
	public Wall(Vector2 position, String regionID, int health) {
		super(position, regionID, health);
	}
	
	public Wall(Vector2 position, String regionID, int health, boolean useNumTextures) {
		super(position, regionID, health);
		this.useNumTextures = useNumTextures;
	}

	public boolean hasInventory() {
		return false;
	}

	public boolean hasInvSpace() {
		return false;
	}

	public boolean isHidden(){
		return isDead();
	}
	
	public boolean isCollideable(){
		return !isDead();
	}
	
	public boolean collideWithEntities(){
        return !isDead();
    }
	
	public void updateEntity(float delta) {
		
	}
	
	public TextureRegion getTexture(){
		if(NerdShooter.sanic || !useNumTextures){
			return super.getTexture();
		} else {
			String tex = (int)(((float)health / (float)maxHealth) * 10F) + "";
			if(tex.equals("0") || tex.equals("10")){
				return super.getTexture();
			} else {
				return GameScreen.getTextureAltlas().findRegion(regionID);
			}
		}
	}
	
	public Wall clone(Vector2 pos){
		return new Wall(pos, regionID, health);
	}
	
	public Wall clone(Vector2 pos, int health){
		return new Wall(pos, regionID, health);
	}
	
	public void render(SpriteBatch batch){
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x, getPosition().y, bounds.getWidth(), bounds.getHeight());
        }   
    }
}
