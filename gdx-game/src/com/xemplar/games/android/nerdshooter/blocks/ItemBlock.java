package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.items.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class ItemBlock extends Block{
    private Item item;
    private boolean canBeTaken = true;
    
    public ItemBlock(Vector2 pos, Item item){
        super(pos, item.regionID);
        this.item = item;
        this.item.setBlock(this);
    }
    
    public ItemBlock(Vector2 pos, float size, Item item){
        super(pos, item.regionID);
        this.bounds.width = size;
        this.bounds.height = size;
        this.item = item;
        this.item.setBlock(this);
    }

    public ItemBlock(Vector2 pos, float width, float height, Item item){
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
}
