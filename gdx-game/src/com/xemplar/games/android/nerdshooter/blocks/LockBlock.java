package com.xemplar.games.android.nerdshooter.blocks;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.xemplar.games.android.nerdshooter.items.*;

public class LockBlock extends Block{
    private static int keyBegin = 0;
    private boolean isLocked = true;
    public Item lockID;
    
    public LockBlock(Vector2 pos, String regionID, Item lockID){
        super(pos, regionID);
        this.lockID = lockID;
    }
    
    public boolean isCollideable() {
        return isLocked;
    }
    
    public boolean isHidden() {
        return !isLocked;
    }
    
    public boolean isTouchable(){
        return true;
    }
    
    public void onTouch(Entity e){
        if(e.hasInventory() && isLocked){
            int slot = e.inventory.invHasItem(lockID);
            if(e.inventory.removeItem(slot)) {
                isLocked = false;
                
                if(e instanceof Jaxon){
                    e.setCheckPoint(position);
                }
            }
        }
    }
}
