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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class SlabBlock extends Block {
	private static TextureRegion texR;
	private static TextureRegion texY;
	private static TextureRegion texB;

    static{
    	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.atlas"));
        texR = atlas.findRegion("sanic_half_bg0");
        texY = atlas.findRegion("sanic_half_bg1");
        texB = atlas.findRegion("sanic_half_bg2");
        
        texR.flip(false, true);
        texY.flip(false, true);
        texB.flip(false, true);
	}
    
    protected boolean top;
	
    protected SlabBlock(Vector2 pos, String regionID, float width, float height, boolean top){
        super(pos, regionID, width, height);
        
        this.top = top;
        
        if(top) bounds.y = bounds.y + getHeight();
    }
    
    public SlabBlock clone(Vector2 pos){
		SlabBlock b = new SlabBlock(pos, regionID, bounds.width, bounds.height, top);
		return b;
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
    
    public void render(SpriteBatch batch) {
        if(!isHidden()){
            batch.draw(getTexture(), getPosition().x, getPosition().y, getWidth(), getHeight() * 2F);
        }
    }
}
