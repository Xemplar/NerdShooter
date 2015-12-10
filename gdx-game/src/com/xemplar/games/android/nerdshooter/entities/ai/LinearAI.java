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
package com.xemplar.games.android.nerdshooter.entities.ai;

import com.xemplar.games.android.nerdshooter.entities.Entity;

public class LinearAI extends AbstractAI{
	protected final long period;
	protected final float speed;
	
	public LinearAI(Entity e, long period, float speed) {
		super(e);
		this.period = period;
		this.speed = speed;
	}

	public void update(float delta) {
		if(e.getVelocity().x < speed){
			e.getVelocity().add(speed, 0);
		}
	}

	public void setVelocity(long ticks) {
		if(ticks % period == 0){
			e.getVelocity().set(-e.getVelocity().x, e.getVelocity().y);
		}
	}
}
