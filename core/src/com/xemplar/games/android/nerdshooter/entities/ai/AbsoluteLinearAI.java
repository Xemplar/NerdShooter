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
package com.xemplar.games.android.nerdshooter.entities.ai;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;

public class AbsoluteLinearAI extends AbstractAI{
    protected final Direction movement;
    protected final Vector2 toPos;
    protected float speed;
    protected Vector2 fromPos;
    protected int dir = 1;

    public AbsoluteLinearAI(Vector2 toPos, float speed, Direction movement) {
        this.movement = movement;
        this.toPos = toPos;
        this.speed = speed;
    }

    public void bindWithEntity(Entity e){
        if(fromPos == null) {
            fromPos = e.getPosition().cpy();
            toPos.add(e.getPosition().x, e.getPosition().y);
        }
        super.bindWithEntity(e);
    }

    public void updateAI(float delta) {
        Vector2 ePos = entity.getPosition();
        if(movement == Direction.HORIZONTAL) {
            if(ePos.x <= Math.min(toPos.x, fromPos.x)){
                dir = 1;
                entity.getVelocity().set(0, 0);
            } else if(ePos.x >= Math.max(toPos.x, fromPos.x)){
                dir = -1;
                entity.getVelocity().set(0, 0);
            }

            if (Math.abs(entity.getVelocity().x) < speed) {
                entity.getVelocity().add(speed * dir, 0);
            }
        } else if(movement == Direction.VERTICAL){
            if(ePos.y <= Math.min(toPos.y, fromPos.y)){
                dir = 1;
                entity.getVelocity().set(0, 0);
            } else if(ePos.y >= Math.max(toPos.y, fromPos.y)){
                dir = -1;
                entity.getVelocity().set(0, 0);
            }

            if (Math.abs(entity.getVelocity().y) < speed) {
                entity.getVelocity().add(0, speed * dir);
            }
        }
    }
}
