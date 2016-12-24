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

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class HazardBlock extends Block {
    protected int hurtAmount = 0;
    
    protected HazardBlock(Vector2 pos, String regionID, int removeHealth){
        super(pos, regionID);
        this.hurtAmount = removeHealth;
    }
    
    protected HazardBlock(Vector2 pos, String regionID, float width, float height, int removeHealth){
        super(pos, regionID, width, height);
        this.hurtAmount = removeHealth;
    }
    
    protected HazardBlock(Vector2 pos, String regionID, float size, int removeHealth){
        super(pos, regionID, size);
        this.hurtAmount = removeHealth;
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.hurt(hurtAmount);
    }
    
    public HazardBlock clone(Vector2 pos){
        HazardBlock b = new HazardBlock(pos, regionID, bounds.width, bounds.height, hurtAmount);
        return b;
    }
}
