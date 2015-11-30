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
import com.xemplar.games.android.nerdshooter.model.World;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class EntityController implements Controller{
	private Array<Rectangle> collisionRects = new Array<Rectangle>();
	private static final float GRAVITY = -20f;

    private static Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};
    
	private Array<Block> collidable = new Array<Block>();
    private boolean grounded = false;
    private Entity entity;
    
    public EntityController(Entity e){
    	this.entity = e;
    }
    
	public void update(float delta) {
		if(entity.affectWithGravity()){
			if (grounded && entity.getState().equals(Entity.State.JUMPING)) {
				entity.setState(Entity.State.IDLE);
			}

        	entity.getAcceleration().y = GRAVITY;
        	entity.getVelocity().mulAdd(entity.getAcceleration(), delta);
		}
		
		if(entity.collideWithEntities() || entity.collideWithBlocks()){
			checkCollisionWithBlocks(delta);
		}
    }
	
	private void checkCollisionWithBlocks(float delta) {
		entity.getVelocity().scl(delta);
        Rectangle entityRect = rectPool.obtain();
        entityRect.set(entity.getBounds().x, entity.getBounds().y, entity.getBounds().width, entity.getBounds().height);

        populateCollidableBlocks();
        entityRect.x += entity.getVelocity().x;
        collisionRects.clear();

        for (Block block : collidable) {
        	if (block == null) continue;
        	
        	entity.getVelocity().x = 0;
            collisionRects.add(block.getBounds());

            if (entityRect.overlaps(block.getBounds()) && block.isTouchable()) {
                block.onTouch(entity);
            }
            
            if (entity.getBounds().overlaps(block.getBounds()) && block.isCollideable()) {
                float entityX = entity.getPosition().x;
                float blockX = block.getPosition().x;

                System.out.println("got stuck on: " + block.regionID);

                if (entityX < blockX) {
                	entity.getPosition().x = block.getPosition().x - entity.getBounds().getWidth();
                } else {
                	entity.getPosition().x = block.getPosition().x + block.getBounds().getWidth();
                }
            }
            break;
        }

        entityRect.x = entity.getPosition().x;

        populateCollidableBlocks();
        entityRect.y += entity.getVelocity().y;

        for (Block block : collidable) {
        	if (block == null) continue;
        	
			if (entityRect.overlaps(block.getBounds()) && block.isTouchable()) {
                block.onTouch(entity);
            }

            if (entityRect.overlaps(block.getBounds()) && block.isCollideable()) {
                if (entity.getVelocity().y < 0) {
                    grounded = true;
                }

                entity.getVelocity().y = 0;
                collisionRects.add(block.getBounds());
                break;
            }
        }
    }

    private void populateCollidableBlocks() {
    	World world = GameScreen.instance.world;
    	
        collidable.clear();

        Vector2 pos = entity.getPosition().cpy();
        
        if(entity.collideWithBlocks()){
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
        
        if(entity.collideWithEntities()){
        	int size = world.getEntities().size;
        	for(int i = 0; i < size; i++){
            	Entity current = world.getEntities().get(i);
            
            	if(current.isHidden()) continue;
            	if(current.equals(entity)) continue;
            
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
