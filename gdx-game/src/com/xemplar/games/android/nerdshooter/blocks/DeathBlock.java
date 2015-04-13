package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class DeathBlock extends Block {
    public DeathBlock(Vector2 pos, String regionID){
        super(pos, regionID);
    }
    
    public DeathBlock(Vector2 pos, String regionID, float size){
        super(pos, regionID, size);
    }
    
    public DeathBlock(Vector2 pos, String regionID, float width, float height){
        super(pos, regionID, width, height);
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.kill();
    }
}
