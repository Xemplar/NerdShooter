package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.model.World;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class OutputDevice extends Entity{
	public OutputDevice(Vector2 position, String regionID, float width, float height, int health) {
		super(position, regionID, width, height, health);
	}
	
	public OutputDevice(Vector2 position, String regionID, float size, int health) {
		super(position, regionID, size, size, health);
	}
	
	public OutputDevice(Vector2 position, String regionID, int health) {
		super(position, regionID, health);
	}
	
	public boolean hasInventory() {
		return false;
	}

	public boolean hasInvSpace() {
		return false;
	}

	public void updateEntity(float delta) {
		long ticks = GameScreen.gameTicks;
		
		if(ticks % 100 == 0){
			World.spawnEntity(Projectile.bush.clone(position.cpy()));
		}
	}

}
