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

import static com.xemplar.games.android.nerdshooter.screens.GameScreen.*;

public class LinearAI extends AbstractAI{
	protected final long period;
	protected final float speed;
	protected int dir = 1;
	
	public LinearAI(long period, float speed) {
		this.period = period;
		this.speed = speed;
	}

	public void updateAI(float delta) {
		if((gameTicks % period) == 0){
			dir *= -1;
			e.getVelocity().set(0, 0);
		}
		if(Math.abs(e.getVelocity().x) < speed){
			e.getVelocity().add(speed * dir, 0);
		}
	}
}
