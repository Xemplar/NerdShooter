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
package com.xemplar.games.android.nerdshooter.items;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Projectile;

public class Ammo extends Item {
	protected Projectile pro;
	
	protected Ammo(int id, int maxStack, Projectile pro) {
		super(id, maxStack, pro.regionID);
		this.pro = pro;
	}
	
	public void launch(Vector2 pos, float speed, float deg){
		this.pro.launch(pos, speed, deg);
	}
	
	public Ammo clone(){
        return new Ammo(id, maxStack, pro);
    }
	
	public boolean stayInInventory(){
		return true;
	}
}
