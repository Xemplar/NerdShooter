package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {
	//Projectile Definitions
	
		public static Projectile bush = new Projectile(empty, "bush", 2.0F, 90F, 1);
	
	// Start Class
	protected float speed, deg;
	
	public Projectile(Vector2 position, String regionID, float speed, float deg, int health) {
		super(position, regionID, health);
		float velX = (float) (MathUtils.cosDeg(deg) * speed);
		float velY = (float) (MathUtils.sinDeg(deg) * speed);
		
		this.speed = speed;
		this.deg = deg;
		this.velocity.set(velX, velY);
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
	
	public Projectile clone(Vector2 pos){
		return new Projectile(pos, regionID, speed, deg, health);
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
