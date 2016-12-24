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
 * along with this program.  If not, see <http:www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.entities.ai;

import static com.xemplar.games.android.nerdshooter.screens.GameScreen.gameTicks;

public class TimedLinearAI extends AbstractAI{
    protected final Direction movement;
    protected final long period;
    protected final float speed;
    protected int dir = 1;
    
    public TimedLinearAI(long period, float speed, Direction movement) {
        this.movement = movement;
        this.period = period;
        this.speed = speed;
    }

    public void updateAI(float delta) {
        if((gameTicks % period) == 0){
            dir *= -1;
            entity.getVelocity().set(0, 0);
        }
        if(movement == Direction.HORIZONTAL) {
            if (Math.abs(entity.getVelocity().x) < speed) {
                entity.getVelocity().add(speed * dir, 0);
            }
        } else if(movement == Direction.VERTICAL){
            if (Math.abs(entity.getVelocity().y) < speed) {
                entity.getVelocity().add(0, speed * dir);
            }
        } else {
            if (Math.abs(entity.getVelocity().x) < speed) {
                entity.getVelocity().add(speed * dir, 0);
            }
            if (Math.abs(entity.getVelocity().y) < speed) {
                entity.getVelocity().add(0, speed * dir);
            }
        }
    }
}
