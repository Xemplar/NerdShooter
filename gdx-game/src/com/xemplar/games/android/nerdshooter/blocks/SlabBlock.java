/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
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

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class SlabBlock extends Block {
	protected boolean top;
	
    protected SlabBlock(Vector2 pos, String regionID, float width, float height, boolean top){
        super(pos, regionID, width, height);
        
        this.top = top;
        
        if(top) bounds.y = bounds.y + getHeight();
    }
    
    public SlabBlock clone(Vector2 pos){
		SlabBlock b = new SlabBlock(pos, regionID, bounds.width, bounds.height, top);
		return b;
	}
    
    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY / getHeight());
        }
    }
}
