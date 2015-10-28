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

import com.xemplar.games.android.nerdshooter.items.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.badlogic.gdx.graphics.glutils.*;

public class Inventory {
    private static float space = 5F;
    
    private static float drawWidth = 0F;
    private static float drawHeight = 0F;
    
    private Entity master;
    private int spots;
    private Array<Item> items;
    
    public Inventory(Entity master, int spots){
        this.master = master;
        this.spots = spots;
        
        this.items = new Array<Item>();
    }
    
    public Item getItem(int spot){
        if(items.size < spot){
            return null;
        } else {
            return items.get(spot);
        }
    }
    
    public Array<Item> getItems(){
        return items;
    }
    
    public boolean hasSpace(){
        return items.size < spots;
    }
    
    public boolean hasItems(){
        return items.size > 0;
    }
    
    public void clear(){
        while(hasItems()){
            items.get(0).returnToBlock(master);
        }
    }
    
    public int invHasItem(Item item){
        for(int i = 0; i < items.size; i++){
            if(items.get(i).id == item.id){
                return i;
            }
        }
        
        return -1;
    }
    
    public int invHasItem(int itemID){
        for(int i = 0; i < items.size; i++){
            if(items.get(i).id == itemID){
                return i;
            }
        }

        return -1;
    }
    
    public boolean removeItem(int spot){
        if(spot > items.size) return false;
        if(spot == -1) return false;
        items.removeIndex(spot);
        return true;
    }
    
    public boolean addItem(Item item){
        if(items.size == spots){
            return false;
        } else {
            items.add(item);
            return true;
        }
    }
    
    public void renderGUI(ShapeRenderer renderer, int width, int height, float size){
        this.drawWidth = (space * (spots + 1)) + (size * spots);
        this.drawHeight = (space * 2) + size;
        
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
        this.drawWidth = (space * (spots + 1)) + (size * spots);
        this.drawHeight = (space * 2) + size;
        
        float slotY = height - (space + size);
        int amt = items.size;
        
        for(int i = 0; i < amt; i++){
            float x = (width - ((space + size) * i)) - (size + space);
            TextureRegion region = GameScreen.instance.getTextureAltlas().findRegion(items.get(i).getRegionID());

            batch.draw(region, x, slotY, size, size);
        }
    }
}
