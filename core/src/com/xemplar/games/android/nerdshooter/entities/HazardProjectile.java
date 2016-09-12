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

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.model.World;

public class HazardProjectile extends Projectile{
	protected int hurtAmount = 0;
	
	public HazardProjectile(Vector2 position, String regionID, int damageAmt) {
		super(position, regionID);
		this.hurtAmount = damageAmt;
	}
	
	public HazardProjectile(Vector2 position, String regionID, float size, int damageAmt) {
		super(position, regionID, size);
		this.hurtAmount = damageAmt;
	}
	
	public HazardProjectile(Vector2 position, String regionID, float width, float height, int damageAmt) {
		super(position, regionID, width, height);
		this.hurtAmount = damageAmt;
	}
	
	public boolean isTouchable(){
		return true;
	}
	
	public void onTouch(Entity e) {
        e.hurt(hurtAmount);
        this.kill();
    }
	
	public void onKill(){
		World.despawnEntity(this);
	}
	
	public HazardProjectile launch(Vector2 pos, float speed, float deg){
		HazardProjectile pro = new HazardProjectile(pos, regionID, bounds.getWidth(), bounds.getHeight(), hurtAmount);
		pro.setVelocity(speed, deg);
		World.spawnEntity(pro);
		return pro;
	}
}
