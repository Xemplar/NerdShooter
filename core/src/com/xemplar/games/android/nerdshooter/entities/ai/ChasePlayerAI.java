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

import com.xemplar.games.android.nerdshooter.blocks.Block;

public class ChasePlayerAI extends AbstractAI{
    protected float speed, distance;
    public ChasePlayerAI(float speed, float distance){
        this.speed = speed;
        this.distance = distance;
    }

    public void updateAI(float delta) {
        if(entity.getVelocity().x == 0) entity.getVelocity().set(speed, 0);
        entity.setFacingLeft(entity.getVelocity().x < 0);
        float xDist = entity.getPosition().x - world.getJaxon().getPosition().x;
        float yDist = Math.abs(entity.getPosition().y - world.getJaxon().getPosition().y);

        if(yDist <= 2.01F && Math.abs(xDist) <= distance){
            Block f = world.getBlock(entity.getPosition().cpy().add(entity.isFacingLeft() ? -0.01F : 0.3F, -1));
            Block b = world.getBlock(entity.getPosition().cpy().add(entity.isFacingLeft() ? -0.01F : 0.3F, 0));
            if(xDist <= 0){
                entity.getVelocity().set(speed, 0);
            } else {
                entity.getVelocity().set(-speed, 0);
            }
            if(f == null || (b != null && b.isCollidable())){
                entity.getVelocity().set(entity.isFacingLeft() ? speed : -speed, 0);
            }
        } else {
            Block f = world.getBlock(entity.getPosition().cpy().add(entity.isFacingLeft() ? -0.01F : 0.3F, -1));
            Block b = world.getBlock(entity.getPosition().cpy().add(entity.isFacingLeft() ? -0.01F : 0.3F, 0));
            if(f == null || (b != null && b.isCollidable())){
                entity.getVelocity().set(entity.isFacingLeft() ? speed : -speed, 0);
            }
        }
    }
}
