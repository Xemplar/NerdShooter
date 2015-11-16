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

public abstract class Projectile extends Entity {
	//Projectile Definitions
	
	public static DeathProjectile bullet = new DeathProjectile(empty, "bullet", 0.75F, 0.125F);

	// Start Class
	protected float speed, deg;
	
	public Projectile(Vector2 position, String regionID) {
		super(position, regionID, 1);
		
	}
	
	public Projectile(Vector2 position, String regionID, float size) {
		super(position, regionID, size, 1);
		
	}
	
	public Projectile(Vector2 position, String regionID, float width, float height) {
		super(position, regionID, width, height, 1);
		
	}
	
	public final boolean hasInventory() {
		return false;
	}

	public boolean isCollideable(){
		return false;
	}
	
	public boolean isHidden(){
		return false;
	}
	
	public final boolean hasInvSpace() {
		return false;
	}
	
	protected void setVelocity(float speed, float deg){
		float velX = (float) (MathUtils.cosDeg(deg) * speed);
		float velY = (float) (MathUtils.sinDeg(deg) * speed);
		
		this.speed = speed;
		this.deg = deg;
		this.velocity.set(velX, velY);
	}
	
	public abstract Projectile launch(Vector2 pos, float speed, float deg);
	
	public void updateEntity(float delta) {
		this.position.mulAdd(velocity.cpy(), delta);
		this.bounds.setPosition(this.position);
	}
	
	public void render(SpriteBatch batch){
		if(!isHidden()){
            batch.draw(getTexture(), getPosition().x, getPosition().y, getWidth() / 2F, getHeight() / 2F);
        }  
	}
}
