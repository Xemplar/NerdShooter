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
package com.xemplar.games.android.nerdshooter.items;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class Launcher extends Item implements Fireable, Equippable{
	protected int rounds;
	protected Entity e;
	
	protected Launcher(int id, String regionID){
        super(id, 1, regionID);
    }
	
	public Launcher clone(){
        return new Launcher(id, regionID);
    }
	
	public boolean stayInInventory(){
		return true;
	}
	
	public boolean onEquip(Entity e){
		if(e.hasInventory()){
			this.e = e;
			return true;
		}
		
		return false;
	}
	
	public boolean onFire(){
		if(e != null){
			int res = e.inventory.invHasItemType(Ammo.class);
			if(res != -1){
				ItemStack stack = e.inventory.getItem(res);
				Ammo amm = (Ammo)stack.getItem(0);
				
				Vector2 pos = e.getPosition().cpy();
				float addX = e.isFacingLeft() ? -(0.1F + amm.pro.getBounds().getWidth()) : (0.1F + e.getBounds().getWidth());
				float deg = e.isFacingLeft() ? 180F : 0F;
				
				pos.add(addX, 0);
				amm.launch(pos, 20F, deg);
			}
		}
		return false;
	}
}
