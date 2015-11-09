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
package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {
	//Projectile Definitions
	
		public static Projectile bush = new Projectile(empty, "bush", 1);
	
	// Start Class
	protected float speed, deg;
	
	public Projectile(Vector2 position, String regionID, int health) {
		super(position, regionID, health);
		
	}

	public boolean hasInventory() {
		return false;
	}

	public boolean isHidden(){
		return false;
	}
	
	public boolean hasInvSpace() {
		return false;
	}
	
	public boolean isCollideable(){
		return true;
	}
	
	protected void setVelocity(float speed, float deg){
		float velX = (float) (MathUtils.cosDeg(deg) * speed);
		float velY = (float) (MathUtils.sinDeg(deg) * speed);
		
		this.speed = speed;
		this.deg = deg;
		this.velocity.set(velX, velY);
	}
	
	public Projectile launch(Vector2 pos, float speed, float deg){
		Projectile pro = new Projectile(pos, regionID, health);
		pro.setVelocity(speed, deg);
		return pro;
	}
	
	public void updateEntity(float delta) {
		this.position.mulAdd(velocity.cpy(), delta);
	}
	
	public void render(SpriteBatch batch, float ppuX, float ppuY){
		if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY);
        }  
	}
}
