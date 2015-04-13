package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class HazardBlock extends Block {
    private int hurtAmount = 0;
    public HazardBlock(Vector2 pos, String regionID, int removeHealth){
        super(pos, regionID);
        this.hurtAmount = removeHealth;
    }
    
    public HazardBlock(Vector2 pos, String regionID, float width, float height, int removeHealth){
        super(pos, regionID, width, height);
        this.hurtAmount = removeHealth;
    }
    
    public HazardBlock(Vector2 pos, String regionID, float size, int removeHealth){
        super(pos, regionID, size);
        this.hurtAmount = removeHealth;
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.hurt(hurtAmount);
    }
}
