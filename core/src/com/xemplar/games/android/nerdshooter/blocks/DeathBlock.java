/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
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

public class DeathBlock extends Block {
    protected DeathBlock(Vector2 pos, String regionID){
        super(pos, regionID);
    }
    
    protected DeathBlock(Vector2 pos, String regionID, float size){
        super(pos, regionID, size);
    }
    
    protected DeathBlock(Vector2 pos, String regionID, float width, float height){
        super(pos, regionID, width, height);
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.kill();
    }
    
    public DeathBlock clone(Vector2 pos){
		DeathBlock b = new DeathBlock(pos, regionID, bounds.width, bounds.height);
		return b;
	}
}
