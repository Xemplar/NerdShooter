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

public class DeathProjectile extends Projectile{

	public DeathProjectile(Vector2 position, String regionID, int health) {
		super(position, regionID, health);
	}
	
	public boolean isTouchable(){
		return true;
	}
	
	public void onTouch(Entity e){
		e.kill();
		this.kill();
	}
	
	public DeathProjectile launch(Vector2 pos, float speed, float deg){
		DeathProjectile pro = new DeathProjectile(pos, regionID, health);
		pro.setVelocity(speed, deg);
		return pro;
	}
	
	public void onKill(){
		World.despawnEntity(this);
	}
}
