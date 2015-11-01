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
package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class SlabHazardBlock extends SlabBlock {
	protected int hurtAmount = 0;
	
    protected SlabHazardBlock(Vector2 pos, String regionID, float width, float height, boolean top, int removeHealth){
        super(pos, regionID, width, height, top);
        this.hurtAmount = removeHealth;
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.hurt(hurtAmount);
    }
    
    public SlabHazardBlock clone(Vector2 pos){
    	SlabHazardBlock b = new SlabHazardBlock(pos, regionID, bounds.width, bounds.height, top, hurtAmount);
		return b;
	}
    
    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY / getHeight());
        }
    }
}
