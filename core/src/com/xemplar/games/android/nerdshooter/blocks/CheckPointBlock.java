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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class CheckPointBlock extends Block{

	protected CheckPointBlock(Vector2 pos) {
		super(pos, "");
	}
	
	public boolean isTouchable(){
		return true;
	}
	
	public boolean isCollideable(){
		return false;
	}
	
	public void onTouch(Entity e){
		e.setCheckPoint(position.cpy().add(0, 0.25F));
	}
	
	public final boolean isHidden(){
		return true;
	}
	
	public CheckPointBlock clone(Vector2 pos){
		return new CheckPointBlock(pos);
	}
	
	public void render(SpriteBatch batch){
		
	}
}
