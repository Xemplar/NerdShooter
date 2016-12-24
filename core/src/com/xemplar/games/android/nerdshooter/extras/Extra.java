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
package com.xemplar.games.android.nerdshooter.extras;

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.blocks.Block;

public class Extra extends Block {
    public static final Extra window = new Extra(empty, "window");

    protected Extra(Vector2 pos, String regionID) {
        super(pos, regionID);
    }
    protected Extra(Vector2 pos, String regionID, float size) {
        super(pos, regionID, size);
    }
    protected Extra(Vector2 pos, String regionID, float width, float height) {
        super(pos, regionID, width, height);
    }

    public boolean isCollidable(){
        return false;
    }
    public boolean isHidden(){
        return false;
    }
    public boolean isTouchable(){
        return false;
    }
    public boolean isAnimated(){
        return false;
    }

    public Extra clone(Vector2 pos){
        Extra b = new Extra(pos, regionID, this.bounds.getWidth(), this.bounds.getWidth());
        return b;
    }
}
