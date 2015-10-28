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
