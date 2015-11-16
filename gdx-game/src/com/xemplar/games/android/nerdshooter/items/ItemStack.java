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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class ItemStack {
	protected Array<Item> items;
	protected Item mock;
	protected int id = 0;
	protected int maxAmt;
	
	public ItemStack(Array<Item> items){
		this.items = items;
		
		if(items != null){
			mock = items.get(0).clone();
			if(items.size > 0){
				this.id = items.get(0).id;
				this.maxAmt = items.get(0).maxStack;
			} else {
				this.id = -1;
				this.maxAmt = 0;
			}
		} else {
			this.mock = null;
			this.items = new Array<Item>();
			this.id = -1;
			this.maxAmt = 0;
		}
	}
	
	public boolean hasEnough(int amount){
		return this.items.size >= amount;
	}
	
	public boolean isFull(){
		return this.items.size == maxAmt;
	}
	
	public int add(Array<Item> items){
		if(this.items.size == 0 && items.size > 0){
			this.id = items.get(0).id;
			this.maxAmt = items.get(0).maxStack;
			this.mock = items.get(0).clone();
		}
		
		if(items.size == 0){
			return items.size;
		}
		
		if(items.get(0).id != this.id){
			return items.size;
		}
		
		int start = items.size;
		int upper = Math.min(maxAmt, items.size);
		
		for(int i = start; i < upper; i++){
			this.items.add(items.get(0));
			items.removeIndex(0);
		}
		
		return items.size;
	}
	
	public boolean add(Item item){
		if(item == null){
			return false;
		}
		
		if(this.items.size == 0){
			this.id = item.id;
			this.maxAmt = items.get(0).maxStack;
			this.mock = items.get(0).clone();
		}
		
		if(item.id != this.id){
			return false;
		}
		
		if(isFull()){
			return false;
		}
		
		this.items.add(item);
		
		return true;
	}
	
	public Array<Item> remove(int amt){
		int upper = Math.min(amt, this.items.size);
		Array<Item> ret = new Array<Item>();
		
		for(int i = 0; i < upper; i++){
			ret.add(this.items.get(0));
			this.items.removeIndex(0);
		}
		
		if(this.items.size == 0){
			this.mock = null;
			this.id = -1;
			this.maxAmt = 0;
		}
		
		return ret;
	}
	
	public int getCount(){
		return this.items.size;
	}
	
	public Item getItem(int i){
		if((i + 1) > this.items.size){
			return null;
		}
		
		Item item = this.items.get(i);
		this.items.removeIndex(i);
		
		if(this.items.size == 0){
			this.id = -1;
			this.maxAmt = 0;
			this.mock = null;
		}
		
		return item;
	}
	
	public void returnStackToBlock(Entity master){
		for(int i = 0; i < items.size; i++){
			items.get(0).returnToBlock(master);
		}
	}
	
	public String getRegionID(){
		if(this.items.size == 0){
			return "";
		}
        return this.items.get(0).regionID;
	}
	
	public int getID(){
		return this.id;
	}
	
	public int getMaxStack(){
		return this.maxAmt;
	}
	
	public Item getMock(){
		if(mock != null){
			return this.mock.clone();
		}
		return null;
	}
	
	public void render(SpriteBatch batch, int x, int y, int size){
		TextureRegion region = GameScreen.getTextureAltlas().findRegion(this.getRegionID());
        
		if(region != null){
			batch.draw(region, x, y, size, size);
		}
        
		NerdShooter.text.draw(batch, getCount() + "", x, y + (size / 4));
        
	}
}
