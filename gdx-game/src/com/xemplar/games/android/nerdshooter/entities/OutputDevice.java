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
		super(position, regionID, 10F, 90F, health);
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
			World.spawnEntity(Projectile.bush);
		}
	}

}
