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

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class DirectionalDeathBlock extends DeathBlock{
	protected final int direction;
	
	protected DirectionalDeathBlock(Vector2 pos, String regionID, int dirs){
        super(pos, regionID);
        direction = dirs;
    }
    
    protected DirectionalDeathBlock(Vector2 pos, String regionID, float size, int dirs){
        super(pos, regionID, size);
        direction = dirs;
    }
    
    protected DirectionalDeathBlock(Vector2 pos, String regionID, float width, float height, int dirs){
        super(pos, regionID, width, height);
        direction = dirs;
    }
    
    protected DirectionalDeathBlock(Vector2 pos, String regionID, boolean top, boolean bottom, boolean left, boolean right){
        super(pos, regionID);
        direction = (toInt(top) << 3) + (toInt(bottom) << 2) + (toInt(left) << 1) + toInt(right);
    }
    
    protected DirectionalDeathBlock(Vector2 pos, String regionID, float size, boolean top, boolean bottom, boolean left, boolean right){
        super(pos, regionID, size);
        direction = (toInt(top) << 3) + (toInt(bottom) << 2) + (toInt(left) << 1) + toInt(right);
    }
    
    protected DirectionalDeathBlock(Vector2 pos, String regionID, float width, float height, boolean top, boolean bottom, boolean left, boolean right){
        super(pos, regionID, width, height);
        direction = (toInt(top) << 3) + (toInt(bottom) << 2) + (toInt(left) << 1) + toInt(right);
    }
    
    public void onTouch(Entity e){
    	boolean up = ((direction >> 3) & 0x01) == 1;
    	boolean down = ((direction >> 2) & 0x01) == 1;
    	boolean left = ((direction >> 1) & 0x01) == 1;
    	boolean right = (direction & 0x01) == 1;
    	

    	Vector2 center = new Vector2(bounds.x + (right ? 1F : 0F), bounds.y + (up ? 1F : -0.5F));
    	
    	boolean kill = false;
    	kill |= (e.getPosition().x < center.x && left);
    	kill |= (e.getPosition().x > center.x && right);
    	kill |= (e.getPosition().y < center.y && down);
    	kill |= (e.getPosition().y > center.y && up);
    	
    	if(kill){
    		super.onTouch(e);
    	}
    }
    
    private int toInt(boolean bool){
    	return bool ? 1 : 0;
    }
    
    public DirectionalDeathBlock clone(Vector2 pos){
    	return new DirectionalDeathBlock(pos, regionID, bounds.width, bounds.height, direction);
    }
}
