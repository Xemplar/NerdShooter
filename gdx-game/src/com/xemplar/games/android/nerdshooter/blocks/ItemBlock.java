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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.items.Item;
import com.xemplar.games.android.nerdshooter.items.ItemStack;

public class ItemBlock extends Block{
    protected ItemStack stack;
    protected boolean canBeTaken = true;
    
    protected ItemBlock(Vector2 pos, Item item){
        super(pos, item.regionID);
        Array<Item> items = new Array<Item>();
        items.add(item);
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
    }
    
    protected ItemBlock(Vector2 pos, float size, Item item){
        super(pos, item.regionID);
        this.bounds.width = size;
        this.bounds.height = size;
        Array<Item> items = new Array<Item>();
        items.add(item);
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
    }

    protected ItemBlock(Vector2 pos, float width, float height, Item item){
        super(pos, item.regionID);
        this.bounds.width = width;
        this.bounds.height = height;
        Array<Item> items = new Array<Item>();
        items.add(item);
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
    }
    
    protected ItemBlock(Vector2 pos, Item item, int qnt){
        super(pos, item.regionID);
        Array<Item> items = new Array<Item>();
        for(int i = 0; i < qnt; i++){
        	items.add(item.clone());
        }
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
    }
    
    protected ItemBlock(Vector2 pos, float size, Item item, int qnt){
        super(pos, item.regionID);
        this.bounds.width = size;
        this.bounds.height = size;
        Array<Item> items = new Array<Item>();
        for(int i = 0; i < qnt; i++){
        	items.add(item.clone());
        }
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
    }

    protected ItemBlock(Vector2 pos, float width, float height, Item item, int qnt){
        super(pos, item.regionID);
        this.bounds.width = width;
        this.bounds.height = height;
        Array<Item> items = new Array<Item>();
        for(int i = 0; i < qnt; i++){
        	items.add(item.clone());
        }
        this.stack = new ItemStack(items);
        for(Item i : items){
        	i.setBlock(this);
        }
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
    
    public void returnItem(Item i){
    	stack.add(i);
        canBeTaken = true;
    }
    
    public void onTouch(Entity e){
        if(e.hasInventory() && e.hasInvSpace() && canBeTaken){
            e.inventory.addItems(stack.getAll());
            canBeTaken = false;
        }
    }
    
    public ItemBlock clone(Vector2 pos){
		ItemBlock b = new ItemBlock(pos, bounds.width, bounds.height, stack.getMock(), stack.getCount());
		return b;
	}
}
