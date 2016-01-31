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
package com.xemplar.games.android.nerdshooter.blocks;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class ExitBlock extends Block {
    public static final int EXIT_NORMAL = 0x000001;
    public static final int EXIT_SPECIAL = 0x000002;
    public static final int EXIT_NOCLEAR = 0x000003;
    
    private int exitCode;
    
    protected ExitBlock(Vector2 position, String regionID, int exitCode){
        super(position, regionID);
        this.exitCode = exitCode;
    }
    
    public boolean isCollideable() {
        return false;
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        System.out.println("Done");
        GameScreen.finishLevel(exitCode);
    }
    
    public ExitBlock clone(Vector2 pos){
		ExitBlock b = new ExitBlock(pos, regionID, exitCode);
		return b;
	}
}
