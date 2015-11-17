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
package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.inventory.Inventory;
import com.xemplar.games.android.nerdshooter.items.ItemStack;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class Jaxon extends Entity{
    private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private TextureRegion jaxonIdleLeft;
    private TextureRegion jaxonIdleRight;
    private TextureRegion jaxonFrame;
    
    private float drawX;
    private boolean fliped;
    
    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;
    
    public Jaxon(Vector2 startPosistion){
		super(startPosistion, 1.0F / 3F, 1.0F / 2F, 8);
        
        drawX = (bounds.getWidth() / 2F) - (1.0F / 2F);
        drawX = (drawX < 0) ? -drawX : drawX;
        drawX = drawX / 4F;
        
        inventory = new Inventory(this, 4);
        loadTextures();
	}
    
    public void loadTextures() {
    	TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.atlas"));

    	if(!NerdShooter.sanic){
        	jaxonIdleLeft = atlas.findRegion("jaxon00");

        	jaxonIdleRight = new TextureRegion(jaxonIdleLeft);
        	jaxonIdleRight.flip(true, false);
        	
        	TextureRegion[] walkLeftFrames = new TextureRegion[4];
        	
        	walkLeftFrames[0] = atlas.findRegion("jaxon00");
        	walkLeftFrames[1] = atlas.findRegion("jaxon01");
        	walkLeftFrames[2] = atlas.findRegion("jaxon00");
        	walkLeftFrames[3] = atlas.findRegion("jaxon02");

        	walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
        	
        	TextureRegion[] walkRightFrames = new TextureRegion[4];
        	
        	for (int i = 0; i < 4; i++) {
            	walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            	walkRightFrames[i].flip(true, false);
        	}
        	walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
        	
        	return;
    	}
    	jaxonIdleLeft = atlas.findRegion("sanic");

        jaxonIdleRight = new TextureRegion(jaxonIdleLeft);
        jaxonIdleRight.flip(true, false);

        TextureRegion[] walkLeftFrames = new TextureRegion[4];

        walkLeftFrames[0] = atlas.findRegion("sanic");
        walkLeftFrames[1] = atlas.findRegion("sanic");
        walkLeftFrames[2] = atlas.findRegion("sanic");
        walkLeftFrames[3] = atlas.findRegion("sanic");

        walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

        TextureRegion[] walkRightFrames = new TextureRegion[4];

        for (int i = 0; i < 4; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }
        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);
    }
    
    public boolean hasInventory() {
        return true;
    }

    public boolean hasInvSpace() {
        return inventory.hasSpace();
    }
	
	public void updateEntity(float delta) {
        if(isDead()){
            respawn();
        }
        
		stateTime += delta;
		position.mulAdd(velocity.cpy(), delta);
		bounds.x = position.x;
		bounds.y = position.y;

		if(getPosition().y < 0){
			kill();
		}
	}

    public void render(SpriteBatch batch) {
        jaxonFrame = isFacingLeft() ? jaxonIdleLeft : jaxonIdleRight;
        if(getState().equals(Jaxon.State.WALKING)) {
            jaxonFrame = isFacingLeft() ? walkLeftAnimation.getKeyFrame(getStateTime(), true) : walkRightAnimation.getKeyFrame(getStateTime(), true);
        }
        
        if(!isHidden()){
            batch.draw(jaxonFrame, (getPosition().x - drawX), getPosition().y, (0.5F), (0.5F));
        }
        
        //Render Selected Item
        
        int selected = inventory.getSelectedItem();
    	if(selected != -1){
    		ItemStack stack = inventory.getItem(selected);
    		if(stack != null){
    			float x = isFacingLeft() ? (getPosition().x - drawX) : (getPosition().x - drawX) + bounds.getWidth() * 0.75F;
    			TextureRegion reg = GameScreen.getTextureAltlas().findRegion(stack.getMock().regionID);
    			if(isFacingLeft() && !fliped){
    				reg.flip(true, false);
    				fliped = true;
    			} else if(!isFacingLeft() && fliped){
    				reg.flip(true, false);
    				fliped = false;
    			}
    			batch.draw(reg, x, (getPosition().y + ((bounds.getHeight() / 2F) - 0.25F)), 0.25F, 0.25F);
    		}
    	}
    }
}

