package com.xemplar.games.android.nerdshooter.entities;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.badlogic.gdx.graphics.g2d.*;

public class MoveablePlatform extends Entity {
    public static enum MovementType{
		SINE, COSINE
	}
	
	private MovementType movement = MovementType.SINE;
	private float equasion = 1;
	private float amp, frequency;
	
	public MoveablePlatform(Vector2 start, String tex, float amplitude, float frequency, MovementType type){
		super(start, tex, UNLIMITED);
		this.amp = amplitude;
        this.frequency = frequency;
        this.movement = type;
	}
	
	public float getEquasion(){
		return equasion;
	}
	
	public void updateEntity(float delta){
        equasion = amp * MathUtils.sinDeg(frequency * GameScreen.gameTicks);
		setPosition(new Vector2(spawnPoint.x + equasion, spawnPoint.y));
	}

    public boolean isCollideable() {
        return true;
    }
    
    public boolean hasInventory() {
        return false;
    }

    public boolean hasInvSpace() {
        return false;
    }
    
    public boolean isDead() {
        return false;
    }
}
