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
package com.xemplar.games.android.nerdshooter.inventory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.items.Item;
import com.xemplar.games.android.nerdshooter.items.ItemStack;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class Inventory {
    private static float space = 5F;
    
    private static float drawWidth = 0F;
    private static float drawHeight = 0F;
    
    private Entity master;
    private int spots, selected = -1;
    private Array<ItemStack> stacks;
    
    public Inventory(Entity master, int spots){
        this.master = master;
        this.spots = spots;
        
        this.stacks = new Array<ItemStack>();
    }
    
    public ItemStack getItem(int spot){
    	ItemStack stack = null;
        if(spot >= 0 && spot < stacks.size){
            stack =  stacks.get(spot);
        	fixStacks();
        }
        
        return stack;
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
    
    public void forceClear(){
    	Array<Item> items = new Array<Item>();
        for(int i = 0; i < stacks.size; i++){
    		items.addAll(stacks.get(i).remove(stacks.get(i).getCount()));
        }
        stacks.clear();
        for(int i = 0; i < items.size; i++){
        	items.get(i).returnToBlock(master);
        }
        items.clear();
    }
    
    public void clear(){
    	Array<Item> items = new Array<Item>();
        for(int i = 0; i < stacks.size; i++){
        	ItemStack current = stacks.get(i);
        	if(stacks.get(i).getMock().stayInInventory()){
        		continue;
        	}
        	for(int b = 0; b < current.getCount(); b++){
        		if(!current.getCheckPointed().get(b)){
        			items.add(stacks.get(i).getItem(b));
        		}
        	}
        }
        fixStacks();
        for(int i = 0; i < items.size; i++){
        	items.get(i).returnToBlock(master);
        }
        items.clear();
    }
    
    public void hitCheckPoint(){
    	for(int i = 0; i < stacks.size; i++){
        	stacks.get(i).setCheckPointed();
        }
    }
    
    public void fixStacks(){
    	for(ItemStack stack : stacks){
    		if(stack.getCount() == 0){
    			stacks.removeValue(stack, false);
    		}
    	}
    }
    
    public int invHasItemType(Class<? extends Item> c){
    	int ret = -1;
    	
    	for(int i = 0; i < stacks.size; i++){
    		Item mock = stacks.get(i).getMock();
    		if(mock == null){
    			continue;
    		}
            if(mock.getClass().getName().equals(c.getName())){
                ret = i;
            }
        }
    	
        return ret;
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
    
    public boolean removeItem(Item item){
        if(stacks.size == 0) return false;
        
        for(ItemStack stack : stacks){
        	if(stack.getID() == item.id){
        		int amount = stack.getCount();
        		if(amount == 1){
        			stacks.removeValue(stack, false);
        			fixStacks();
        			return true;
        		} else {
        			stack.remove(1);
        			fixStacks();
        			return true;
        		}
        	}
        }
        
        fixStacks();
        return false;
    }
    
    public boolean removeItem(int spot){
        if(spot > stacks.size) { 
        	fixStacks();
        	return false;
        }
        if(spot == -1) {
        	fixStacks();
        	return false;
        }
        ItemStack stack = stacks.get(spot);
        int num = stack.getCount();
        if(num == 1){
            stacks.removeIndex(spot);
        } else {
        	stacks.get(spot).remove(1);
        }
        fixStacks();
        return true;
    }
    
    public int getSelectedItem(){
    	return selected;
    }
    
    public void setSelctedItem(int selected){
    	this.selected = selected;
    }
    
    public boolean pressed(int pressX, int pressY){
    	int width = GameScreen.instance.width, height = GameScreen.instance.height;
    	float size = GameScreen.instance.buttonSize * 0.75F;
    	
    	Inventory.drawWidth = (space * (spots + 1)) + (size * spots);
        Inventory.drawHeight = (space * 2) + size;
        
        float slotY = height - (space + size);
        
        boolean ret = false;
        
        for(int i = 0; i < spots; i++){
            float x = (width - ((space + size) * i)) - (size + space);
            Rectangle rect = new Rectangle(x, slotY, size, size);
            if(rect.contains(pressX, pressY)){
            	selected = i;
            	ret |= true;
            	break;
            }
        }
        
        return ret;
    }
    
    public boolean addItems(Array<Item> items){
    	boolean ret = true;
    	for(Item i : items){
    		ret &= addItem(i);
    	}
    	
    	return ret;
    }
    
    public boolean addItem(Item item){
    	if(stacks.size == 0){
    		Array<Item> items = new Array<Item>();
    		items.add(item);
    		stacks.add(new ItemStack(items));
    		fixStacks();
    		return true;
    	} else {
    		boolean found = false;
    		int pos = 0;
    		for(int i = 0; i < stacks.size; i++){
    			found |= (stacks.get(i).getID() == item.id && !stacks.get(i).isFull());
    			
    			if(found){
    				pos = i;
    				break;
    			}
    		}
    		
    		if(found){
    			stacks.get(pos).add(item);
    			fixStacks();
    			return true;
    		} else if(stacks.size == spots){
    			fixStacks();
    			return false;
    		} else {
    			Array<Item> items = new Array<Item>();
        		items.add(item);
        		stacks.add(new ItemStack(items));
        		fixStacks();
        		return true;
    		}
    	}
    }
    
    public void renderGUI(ShapeRenderer renderer, int width, int height, float size){
        Inventory.drawWidth = (space * (spots + 1)) + (size * spots);
        Inventory.drawHeight = (space * 2) + size;
        
        renderer.setColor(0.7F, 0.7F, 0.7F, 1F);
        renderer.rect(width - drawWidth, height - drawHeight, drawWidth, drawHeight);
        
        float slotY = height - (space + size);
        
        for(int i = 0; i < spots; i++){
        	if(selected != i){
                renderer.setColor(0.3F, 0.3F, 0.3F, 1F);
        	} else {
                renderer.setColor(0.6F, 0.6F, 0.6F, 1F);
        	}
        	
            float x = (width - ((space + size) * i)) - (size + space);
            renderer.rect(x, slotY, size, size);
        }
    }
    
    public void renderItems(SpriteBatch batch, int width, int height, float size){
    	Inventory.drawWidth = (space * (spots + 1)) + (size * spots);
    	Inventory.drawHeight = (space * 2) + size;
        
        float slotY = height - (space + size);
        
        for(int i = 0; i < stacks.size; i++){
            float x = (width - ((space + size) * i)) - (size + space);
            stacks.get(i).render(batch, (int)x, (int)slotY, (int)size);
        }
    }
}
