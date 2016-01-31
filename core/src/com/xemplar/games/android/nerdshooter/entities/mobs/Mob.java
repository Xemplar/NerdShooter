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
 * along with this program.  If not, see <http:www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.entities.mobs;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.ai.AbsoluteLinearAI;
import com.xemplar.games.android.nerdshooter.entities.ai.AbstractAI;
import com.xemplar.games.android.nerdshooter.entities.ai.TimedLinearAI;

public class Mob extends Entity{
	public static final Mob window = new Mob(empty, "window", 10, new TimedLinearAI(100, 1F, AbstractAI.Direction.HORIZONTAL));
	public static final BlockMob spikes = new BlockMob(empty, BlockMob.spike_steel_left,
            new AbsoluteLinearAI(new Vector2(2, 0), 2, AbstractAI.Direction.HORIZONTAL));

	public Mob(Vector2 position, float size, int health, AbstractAI ai) {
        super(position, size, health);
        this.controller = ai;
        ai.bindWithEntity(this);
    }
    
    public Mob(Vector2 position, float width, float height, int health, AbstractAI ai) {
    	super(position, width, height, health);
    	this.controller = ai;
        ai.bindWithEntity(this);
    }
    
	public Mob(Vector2 position, String regionID, int health, AbstractAI ai){
		super(position, regionID, health);
		this.controller = ai;
        ai.bindWithEntity(this);
	}

    public Mob(Vector2 position, String regionID, float size, int health, AbstractAI ai){
    	super(position, regionID, size, health);
    	this.controller = ai;
        ai.bindWithEntity(this);
	}
    
    public Mob(Vector2 position, String regionID, float width, float height, int health, AbstractAI ai){
    	super(position, regionID, width, height, health);
    	this.controller = ai;
        ai.bindWithEntity(this);
	}
    
    public Mob clone(Vector2 pos){
    	return new Mob(pos, regionID, bounds.width, bounds.height, health, (AbstractAI) controller);
    }
    
	public boolean hasInventory() {
		return false;
	}

	public boolean hasInvSpace() {
		return false;
	}

	public void updateEntity(float delta) {
		position.mulAdd(velocity.cpy(), delta);
		bounds.x = position.x;
		bounds.y = position.y;
	}
}
