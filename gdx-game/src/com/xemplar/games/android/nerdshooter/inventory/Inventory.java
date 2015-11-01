/*
 * NerdShooter is a pseudo libray project for future Xemplar 2D Side Scroller Games.
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
package com.xemplar.games.android.nerdshooter.inventory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.items.Item;
import com.xemplar.games.android.nerdshooter.items.ItemStack;

public class Inventory {
    private static float space = 5F;
    
    private static float drawWidth = 0F;
    private static float drawHeight = 0F;
    
    private Entity master;
    private int spots;
    private Array<ItemStack> stacks;
    
    public Inventory(Entity master, int spots){
        this.master = master;
        this.spots = spots;
        
        this.stacks = new Array<ItemStack>();
        for(int i = 0; i < spots; i++){
        	stacks.add(new ItemStack(null));
        }
    }
    
    public ItemStack getItem(int spot){
        if(stacks.size < spot){
            return null;
        } else {
            return stacks.get(spot);
        }
    }
    
    public Array<ItemStack> getItems(){
        return stacks;
    }
    
    public boolean hasSpace(){
        return stacks.size < spots;
    }
    
    public boolean hasItems(){
        return stacks.size > 0;
    }
    
    public void clear(){
        while(hasItems()){
        	ItemStack current = stacks.get(0);
        	for(int i = 0; i < current.getCount(); i++){
        		current.getItem(0).returnToBlock(master);
        	}
        }
    }
    
    public int invHasItem(Item item){
        for(int i = 0; i < stacks.size; i++){
            if(stacks.get(i).getID() == item.id){
                return i;
            }
        }
        
        return -1;
    }
    
    public int invHasItem(int itemID){
        for(int i = 0; i < stacks.size; i++){
            if(stacks.get(i).getID() == itemID){
                return i;
            }
        }

        return -1;
    }
    
    public boolean removeItem(int spot){
        if(spot > stacks.size) return false;
        if(spot == -1) return false;
        stacks.removeIndex(spot);
        return true;
    }
    
    public boolean addItem(Item item){
    	boolean full = true;
    	int spot = -1;
    	
    	for(int i = 0; i < spots; i++){
    		ItemStack current = stacks.get(i);
    		
    		if(current.getID() == item.id){
    			full &= current.isFull();
    		}
    		
    		if(!full){
    			spot = i;
    			break;
    		}
    	}
    	
        if(full){
            return false;
        } else {
            stacks.get(spot).add(item);
            return true;
        }
    }
    
    public void renderGUI(ShapeRenderer renderer, int width, int height, float size){
        Inventory.drawWidth = (space * (spots + 1)) + (size * spots);
        Inventory.drawHeight = (space * 2) + size;
        
        renderer.setColor(0.7F, 0.7F, 0.7F, 1F);
        renderer.rect(width - drawWidth, height - drawHeight, drawWidth, drawHeight);
        
        float slotY = height - (space + size);
        renderer.setColor(0.3F, 0.3F, 0.3F, 1F);
        
        for(int i = 0; i < spots; i++){
            float x = (width - ((space + size) * i)) - (size + space);
            renderer.rect(x, slotY, size, size);
        }
    }
    
    public void renderItems(SpriteBatch batch, int width, int height, float size){
    	Inventory.drawWidth = (space * (spots + 1)) + (size * spots);
    	Inventory.drawHeight = (space * 2) + size;
        
        float slotY = height - (space + size);
        
        for(int i = 0; i < spots; i++){
            float x = (width - ((space + size) * i)) - (size + space);
        }
        
        System.out.println("Ended: 0");
    }
}
