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

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.xemplar.games.android.nerdshooter.screens.*;

public class SlabDeathBlock extends SlabBlock {
    private static TextureRegion texR;
    private static TextureRegion texY;
	private static TextureRegion texB;
    
    static{
        Pixmap mapR = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
        Pixmap mapY = new Pixmap(8, 8, Pixmap.Format.RGBA8888);
        Pixmap mapB = new Pixmap(8, 8, Pixmap.Format.RGBA8888);

        mapR.setColor(1, 0.5F, 1F, 1);
        mapR.fillRectangle(0, 4, 8, 4);
        texR = new TextureRegion(new Texture(mapR));

        mapY.setColor(1, 1, 0.5F, 1);
        mapY.fillRectangle(0, 4, 8, 4);
        texY = new TextureRegion(new Texture(mapY));

        mapB.setColor(0.5F, 0.5F, 1, 1);
        mapB.fillRectangle(0, 4, 8, 4);
        texB = new TextureRegion(new Texture(mapB));
	}
    
    protected SlabDeathBlock(Vector2 pos, String regionID, float width, float height, boolean top){
        super(pos, regionID, width, height, top);
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        e.kill();
    }
    
    public TextureRegion getTexture(){
        if(!NerdShooter.sanic){
            return GameScreen.getTextureAltlas().findRegion(regionID);
        } 

        int ran = MathUtils.random(3);
        switch(ran){
        case 1:
            return texB;
        case 2:
            return texY;
        default:
            return texR;
        }
	}
    
    public SlabDeathBlock clone(Vector2 pos){
    	SlabDeathBlock b = new SlabDeathBlock(pos, regionID, bounds.width, bounds.height, top);
		return b;
	}
    
    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x * ppuX, getPosition().y * ppuY, getWidth() * ppuX, getHeight() * ppuY / getHeight());
        }
    }
}
