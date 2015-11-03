package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {
	private Texture tex;
	
	public Projectile(Vector2 position, float speed, float deg, int health) {
		super(position, health);
		float velX = (float) (MathUtils.cosDeg(deg) * speed);
		float velY = (float) (MathUtils.sinDeg(deg) * speed);
		
		tex = new Texture(Gdx.files.internal("images/bush.png"));//sanic.png"));
		
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

	public void updateEntity(float delta) {
		this.position.mulAdd(velocity.cpy(), delta);
	}
	
	public void render(SpriteBatch batch, float ppuX, float ppuY){
		if(!isHidden()){
            batch.draw(tex, getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY);
        }  
	}
}
