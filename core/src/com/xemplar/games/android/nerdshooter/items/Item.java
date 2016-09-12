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
import com.xemplar.games.android.nerdshooter.blocks.ItemBlock;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Projectile;

public class Item {
    public static Item BLUE_KEY = new Item(0, 8, "keyBlue");
    public static Item RED_KEY = new Item(1, 8, "keyRed");
    public static Item GREEN_KEY = new Item(2, 8, "keyGreen");
    public static Item YELLOW_KEY = new Item(3, 8, "keyYellow");
    
    public static Launcher launch = new Launcher(4, "gun");
    public static Ammo ammo = new Ammo(5, 100, Projectile.bullet);
    
    public int id;
    public int maxStack;
    public String regionID;
    public boolean checkPointed = false;
    
    protected ItemBlock block;
    
    protected Item(int id, int maxStack, String regionID){
        this.id = id;
        this.maxStack = maxStack;
        this.regionID = regionID;
    }
    
    public boolean stayInInventory(){
    	return false;
    }
    
    public Item clone(){
        return new Item(id, maxStack, regionID);
    }
    
    public void setBlock(ItemBlock b){
        this.block = b;
    }
    
    public void returnToBlock(Entity e){
        e.inventory.removeItem(this);
        if(block != null){
            block.returnItem(this);
        }
    }
    
    public String getRegionID(){
        return regionID;
	}
}
