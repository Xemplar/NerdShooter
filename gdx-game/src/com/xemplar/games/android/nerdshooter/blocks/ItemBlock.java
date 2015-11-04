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
package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.items.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class ItemBlock extends Block{
    protected Item item;
    protected boolean canBeTaken = true;
    
    protected ItemBlock(Vector2 pos, Item item){
        super(pos, item.regionID);
        this.item = item;
        this.item.setBlock(this);
    }
    
    protected ItemBlock(Vector2 pos, float size, Item item){
        super(pos, item.regionID);
        this.bounds.width = size;
        this.bounds.height = size;
        this.item = item;
        this.item.setBlock(this);
    }

    protected ItemBlock(Vector2 pos, float width, float height, Item item){
        super(pos, item.regionID);
        this.bounds.width = width;
        this.bounds.height = height;
        this.item = item;
        this.item.setBlock(this);
    }
    
    public boolean isCollideable() {
        return false;
    }
    
    public boolean isHidden(){
        return !canBeTaken;
    }
    
    public boolean isTouchable() {
        return canBeTaken;
    }
    
    public void returnItem(){
        canBeTaken = true;
    }
    
    public void onTouch(Entity e){
        if(e.hasInventory() && e.hasInvSpace() && canBeTaken){
            e.inventory.addItem(item);
            canBeTaken = false;
        }
    }
    
    public ItemBlock clone(Vector2 pos){
		ItemBlock b = new ItemBlock(pos, bounds.width, bounds.height, item.clone());
		return b;
	}
}
