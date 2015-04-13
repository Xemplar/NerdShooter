package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class SlabDeathBlock extends DeathBlock {
    public SlabDeathBlock(Vector2 pos, String regionID, float width, float height, boolean top){
        super(pos, regionID, width, height);
        if(top) bounds.y = bounds.y + getHeight();
    }

    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY / getHeight());
        }
    }
}
