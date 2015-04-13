package com.xemplar.games.android.nerdshooter.items;
import com.xemplar.games.android.nerdshooter.model.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Item {
    public static Item BLUE_KEY = new Item(0, "keyBlue");
    public static Item RED_KEY = new Item(1, "keyRed");
    public static Item GREEN_KEY = new Item(2, "keyGreen");
    public static Item YELLOW_KEY = new Item(3, "keyYellow");
    
    public int id;
    public String regionID;
    
    private ItemBlock block;
    
    private Item(int id, String regionID){
        this.id = id;
        this.regionID = regionID;
    }
    
    public Item clone(){
        return new Item(id, regionID);
    }
    
    public void setBlock(ItemBlock b){
        this.block = b;
    }
    
    public void returnToBlock(Entity e){
        e.inventory.removeItem(e.inventory.invHasItem(this));
        if(block != null){
            block.returnItem();
        }
    }
    
    public String getRegionID(){
        return regionID;
	}
}
