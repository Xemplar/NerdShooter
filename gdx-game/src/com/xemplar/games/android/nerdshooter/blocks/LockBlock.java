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
