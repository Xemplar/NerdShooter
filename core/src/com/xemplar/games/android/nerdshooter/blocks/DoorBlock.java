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

import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Jaxon;
import com.xemplar.games.android.nerdshooter.view.ModFinishListener;
import com.xemplar.games.android.nerdshooter.view.RendererModifier;
import com.xemplar.games.android.nerdshooter.view.WorldRenderer;

public class DoorBlock extends Block implements Uncloneable{
    protected Vector2 loc;
    public DoorBlock(Vector2 pos, String regionID, Vector2 loc) {
        super(pos, regionID);
        this.loc = loc;
    }

    public DoorBlock(Vector2 pos, String regionID, Vector2 loc, float size) {
        super(pos, regionID, size);
        this.loc = loc;
    }

    public DoorBlock(Vector2 pos, String regionID, Vector2 loc, float width, float height) {
        super(pos, regionID, width, height);
        this.loc = loc;
    }

    public boolean isCollidable(){
        return false;
    }

    public boolean isHidden(){
        return false;
    }

    public boolean isTouchable(){
        return true;
    }

    public boolean isAnimated(){
        return false;
    }

    public void onTouch(Entity e){
        if(e instanceof Jaxon){
            final Jaxon j = (Jaxon) e;
            if(j.getController().isFireDown() && loc != null) {
                WorldRenderer.setModify(RendererModifier.MOD_FADEOUT, new ModFinishListener() {
                    public void onFinish() {
                        j.warpTo(loc);
                        WorldRenderer.setModify(RendererModifier.MOD_FADEIN, null);
                    }
                    public void onCancel() { }
                });
            }
        }
    }

    public DoorBlock setLoc(Vector2 loc){
        System.out.println(loc);
        this.loc = loc;

        return this;
    }

    public DoorBlock clone(Vector2 pos){
        return new DoorBlock(pos, regionID, loc, this.bounds.getWidth(), this.bounds.getHeight());
    }
}
