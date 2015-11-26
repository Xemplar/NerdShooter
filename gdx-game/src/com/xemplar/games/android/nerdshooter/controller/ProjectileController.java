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
package com.xemplar.games.android.nerdshooter.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Projectile;
import com.xemplar.games.android.nerdshooter.model.World;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class ProjectileController implements Controller{
	private static final float GRAVITY = -20f;

    private static Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
    
	private Array<Block> collidable = new Array<Block>();
    private boolean grounded = false;
	protected Projectile projectile;
	
	public ProjectileController(Projectile projectile) {
		this.projectile = projectile;
	}
	
	public void update(float delta) {
		if(projectile.affectWithGravity()){
			if (grounded && projectile.getState().equals(Entity.State.JUMPING)) {
				projectile.setState(Entity.State.IDLE);
			}

        	projectile.getAcceleration().y = GRAVITY;
        	projectile.getVelocity().mulAdd(projectile.getAcceleration(), delta);
		}
		
		if(projectile.collideWithEntities() || projectile.collideWithBlocks()){
			checkCollisionWithBlocks(delta);
		}
		
		projectile.getVelocity().x *= projectile.getSpeedDamper();
    }
	
	private void checkCollisionWithBlocks(float delta) {
		World world = GameScreen.instance.world;
		
        Rectangle thisRect = rectPool.obtain();
        thisRect.set(projectile.getBounds().x, projectile.getBounds().y, projectile.getBounds().width, projectile.getBounds().height);

        populateCollidableBlocks();
        world.getCollisionRects().clear();

        for (Block block : collidable) {
            if (block == null) continue;

            if (thisRect.overlaps(block.getBounds()) && (block.isTouchable())) {
                block.onTouch(projectile);
            }
        }

        populateCollidableBlocks();

        for (Block block : collidable) {
            if (block == null) continue;
			if (thisRect.overlaps(block.getBounds()) && (block.isTouchable())) {
                block.onTouch(projectile);
            }
        }
    }

    private void populateCollidableBlocks() {
    	World world = GameScreen.instance.world;
    	
        collidable.clear();

        Vector2 pos = projectile.getPosition().cpy();
        
        if(projectile.collideWithBlocks()){
        	int length = world.getLevel().getBlocks().length;
        	Block[] blocks = world.getLevel().getBlocks();
            
    		for (int i = 0; i < length; i++) {
                Block current = blocks[i];
                
    			if (current != null) {
                    if (current.isCollideable() || current.isTouchable()) {
    				    float xDist = Math.abs(current.getPosition().x - pos.x);
    				    float yDist = Math.abs(current.getPosition().y - pos.y);

    				    if (xDist < 1F && yDist < 1F) {
    					    collidable.add(current);
    				    }
                    }
    			}
    		}
        }
        
        if(projectile.collideWithEntities()){
        	int size = world.getEntities().size;
        	for(int i = 0; i < size; i++){
            	Entity current = world.getEntities().get(i);
            
            	if(current.isHidden()) continue;
            	if(current.equals(projectile)) continue;
            
            	if (current.isCollideable() || current.isTouchable()) {
                	float xDist = Math.abs(current.getPosition().x - pos.x);
                	float yDist = Math.abs(current.getPosition().y - pos.y);

                	if (xDist < 1F && yDist < 1F) {
                    	collidable.add(current);
                	}
            	}
        	}
        }
    }
}
