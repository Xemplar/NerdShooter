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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.blocks.Switchable;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class StateExtra extends Extra implements Switchable {
    protected Array<Switchable> sw;
    protected String regionACT;
    protected int state;

    public StateExtra(Vector2 pos, String regionID, String regionACT, int state) {
        super(pos, regionID);
        this.sw = new Array<Switchable>();
        this.regionACT = regionACT;
        this.state = state;
    }
    public StateExtra(Vector2 pos, String regionID, String regionACT, int state, float size) {
        super(pos, regionID, size);
        this.sw = new Array<Switchable>();
        this.regionACT = regionACT;
        this.state = state;
    }
    public StateExtra(Vector2 pos, String regionID, String regionACT, int state, float width, float height) {
        super(pos, regionID, width, height);
        this.sw = new Array<Switchable>();
        this.regionACT = regionACT;
        this.state = state;
    }

    public void addSwitchable(Switchable s){
        sw.add(s);
    }

    public int getState(){
        int state = 0;
        for(Switchable s : sw){
            state += s.isActivated() ? 1 : 0;
        }
        return state;
    }

    public boolean isActivated() {
        return getState() >= state;
    }

    public TextureRegion getTexture() {
        return GameScreen.getTextureAltlas().findRegion(isActivated() ? regionACT : regionID);
    }

    public StateExtra clone(Vector2 pos){
        return new StateExtra(pos, regionID, regionACT, state, this.bounds.getWidth(), this.bounds.getWidth());
    }
}
