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
package com.xemplar.games.android.nerdshooter.entities.mobs;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.ai.AbstractAI;

public class BlockMob extends Mob{
    protected Block b;
    public BlockMob(Vector2 position, Block b, AbstractAI ai){
        super(position, b.regionID, b.getBounds().width, b.getBounds().height, UNLIMITED, ai);
        this.b = b;
        this.b.getPosition().set(position);
    }

    public boolean isTouchable(){
        return b.isTouchable();
    }

    public boolean collideWithEntities(){
        return true;
    }

    public boolean isCollidable(){
        return b.isCollidable();
    }

    public void onTouch(Entity e){
        b.getPosition().set(this.getPosition().cpy());
        b.getBounds().setPosition(this.getPosition().cpy());
        b.onTouch(e);


    }

    public BlockMob clone(Vector2 pos){
        return new BlockMob(pos, b, (AbstractAI) controller);
    }
}
