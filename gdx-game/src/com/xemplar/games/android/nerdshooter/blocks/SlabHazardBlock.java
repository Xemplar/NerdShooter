package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class SlabHazardBlock extends HazardBlock {
    private int hurtAmount = 0;
    public SlabHazardBlock(Vector2 pos, String regionID, float width, float height, boolean top, int removeHealth){
        super(pos, regionID, width, height, removeHealth);
        this.hurtAmount = removeHealth;
        
        if(top) bounds.y = bounds.y + getHeight();
    }
    
    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY / getHeight());
        }
    }
}
