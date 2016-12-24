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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Jaxon;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class SwitchBlock extends Block implements Switchable {
    protected boolean activated = false;
    protected String regionACT;

    protected SwitchBlock(Vector2 pos, String regionID, String regionACT) {
        super(pos, regionID);
        this.regionACT = regionACT;
    }

    protected SwitchBlock(Vector2 pos, String regionID, String regionACT, float size) {
        super(pos, regionID, size);
        this.regionACT = regionACT;
    }

    protected SwitchBlock(Vector2 pos, String regionID, String regionACT, float width, float height) {
        super(pos, regionID, width, height);
        this.regionACT = regionACT;
    }

    public boolean isCollidable(){
        return false;
    }
    public boolean isHidden(){
        return false;
    }
    public boolean isTouchable(){
        return !activated;
    }
    public boolean isAnimated(){
        return false;
    }

    public boolean isActivated(){
        return activated;
    }

    public void onTouch(Entity e){
        if(e instanceof Jaxon) {
            final Jaxon j = (Jaxon) e;
            if (j.getController().isFireDown()) {
                this.activated = true;
            }
        }
    }

    public TextureRegion getTexture() {
        return GameScreen.getTextureAltlas().findRegion(isActivated() ? regionACT : regionID);
    }

    public SwitchBlock clone(Vector2 pos){
        return new SwitchBlock(pos, regionID, regionACT, this.bounds.getWidth(), this.bounds.getHeight());
    }
}
