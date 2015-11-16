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
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Jaxon;
import com.xemplar.games.android.nerdshooter.entities.MoveablePlatform;
import com.xemplar.games.android.nerdshooter.items.Equippable;
import com.xemplar.games.android.nerdshooter.items.Fireable;
import com.xemplar.games.android.nerdshooter.items.Item;
import com.xemplar.games.android.nerdshooter.items.ItemStack;
import com.xemplar.games.android.nerdshooter.model.World;

public class JaxonController {
    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    private static final long LONG_JUMP_PRESS = 150l;
    private static final float ACCELERATION = 22f;
    private static final float GRAVITY = -20f;
    private static final float JUMP_HEIGHT = 7.25f;
    private static final float DAMP = 0.90f;
    private static final float MAX_VEL = 4f;

	private boolean isLeftDown;
	private boolean isRightDown;
	private boolean isJumpDown;
	private boolean isFireDown;

	private Vector2 touchedMovingPlatform;

	public int leftPointer;
	public int rightPointer;
	public int jumpPointer;
	public int firePointer;

	private Array<Block> collidable = new Array<Block>();

	private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
		@Override
		protected Rectangle newObject() {
			return new Rectangle();
		}
	};

	boolean grounded = false;

    private static Block[] blocks;
    private static int length;
    
    private World world;
    private Jaxon jaxon;
    private long jumpPressedTime;
    private boolean jumpingPressed;

    static Map<Keys, Boolean> keys = new HashMap<JaxonController.Keys, Boolean>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    };

    public JaxonController(World world) {
        this.world = world;
        this.jaxon = world.getJaxon();
        
        blocks = world.getLevel().getBlocks();
		length = blocks.length;
    }

    public void leftPressed(int pointer) {
        keys.get(keys.put(Keys.LEFT, true));
		leftPointer = pointer;
		isLeftDown = true;
    }

    public void rightPressed(int pointer) {
        keys.get(keys.put(Keys.RIGHT, true));
		rightPointer = pointer;
		isRightDown = true;
    }

    public void jumpPressed(int pointer) {
        keys.get(keys.put(Keys.JUMP, true));
		jumpPointer = pointer;
		isJumpDown = true;
    }

    public void firePressed(int pointer) {
        keys.get(keys.put(Keys.FIRE, true));
		firePointer = pointer;
		isFireDown = true;
    }

    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
		leftPointer = -1;
		isLeftDown = false;
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
		rightPointer = -1;
		isRightDown = false;
    }

    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
		jumpPointer = -1;
        jumpingPressed = false;
		isJumpDown = false;
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
		firePointer = -1;
		isFireDown = false;
    }

    public void reset() {
        keys.get(keys.put(Keys.LEFT, false));
        leftPointer = -1;
		isLeftDown = false;

        keys.get(keys.put(Keys.RIGHT, false));
        rightPointer = -1;
		isRightDown = false;

        keys.get(keys.put(Keys.FIRE, false));
        firePointer = -1;
		isFireDown = false;

        keys.get(keys.put(Keys.JUMP, false));
        jumpPointer = -1;
        jumpingPressed = false;
		isJumpDown = false;
    }

    public void update(float delta) {
        processInput();

		if (grounded && jaxon.getState().equals(Entity.State.IDLE)) {
			if (touchedMovingPlatform == null)
				touchedMovingPlatform = jaxon.getPosition();

			Block b = world.getBlock(new Vector2(jaxon.getPosition().x, jaxon.getPosition().y - 1F));
			if (b instanceof MoveablePlatform) {
				jaxon.setPosition(new Vector2(touchedMovingPlatform.x, touchedMovingPlatform.y));
			}
		} else {
			touchedMovingPlatform = null;
		}

		if (grounded && jaxon.getState().equals(Jaxon.State.JUMPING)) {
            jaxon.setState(Jaxon.State.IDLE);
        }

        jaxon.getAcceleration().y = GRAVITY;
        jaxon.getAcceleration().scl(delta);
        jaxon.getVelocity().add(jaxon.getAcceleration().x, jaxon.getAcceleration().y);
        checkCollisionWithBlocks(delta);
        jaxon.getVelocity().x *= DAMP;

        if (jaxon.getVelocity().x > MAX_VEL) {
            jaxon.getVelocity().x = MAX_VEL;
        }

        if (jaxon.getVelocity().x < -MAX_VEL) {
            jaxon.getVelocity().x = -MAX_VEL;
        }

        jaxon.update(delta);
        
        if(isFireDown){
        	int selected = jaxon.inventory.getSelectedItem();
        	if(selected != -1){
        		ItemStack stack = jaxon.inventory.getItem(selected);
        		if(stack != null){
        			Item mock = stack.getMock();
            		if(mock instanceof Fireable){
            			((Equippable) mock).onEquip(jaxon);
            			((Fireable) mock).onFire();
            		}
        		}
        	}
        }
    }

    private void checkCollisionWithBlocks(float delta) {
        jaxon.getVelocity().scl(delta);
        Rectangle jaxonRect = rectPool.obtain();
        jaxonRect.set(jaxon.getBounds().x, jaxon.getBounds().y, jaxon.getBounds().width, jaxon.getBounds().height);

        populateCollidableBlocks();
        jaxonRect.x += jaxon.getVelocity().x;
        world.getCollisionRects().clear();

        for (Block block : collidable) {
            if (block == null) continue;

            if (jaxonRect.overlaps(block.getBounds()) && (block.isTouchable())) {
                block.onTouch(jaxon);
            }

            if (jaxonRect.overlaps(block.getBounds()) && block.isCollideable()) {
                jaxon.getVelocity().x = 0;
                world.getCollisionRects().add(block.getBounds());

                if (jaxon.getBounds().overlaps(block.getBounds())) {
                    float jaxonX = jaxon.getPosition().x;
                    float blockX = block.getPosition().x;

                    System.out.println("got stuck");

                    if (jaxonX < blockX) {
                        jaxon.getPosition().x = block.getPosition().x - jaxon.getWidth();
                    } else {
                        jaxon.getPosition().x = block.getPosition().x + block.getWidth();
                    }
                }
                break;
            }
        }

        jaxonRect.x = jaxon.getPosition().x;

        populateCollidableBlocks();
        jaxonRect.y += jaxon.getVelocity().y;

        for (Block block : collidable) {
            if (block == null) continue;
			if (jaxonRect.overlaps(block.getBounds()) && (block.isTouchable())) {
                block.onTouch(jaxon);
            }

            if (jaxonRect.overlaps(block.getBounds()) && block.isCollideable()) {
                if (jaxon.getVelocity().y < 0) {
                    grounded = true;
                }

                jaxon.getVelocity().y = 0;
                world.getCollisionRects().add(block.getBounds());
                break;
            }
        }
        jaxonRect.y = jaxon.getPosition().y;
        jaxon.getVelocity().scl(1 / delta);
    }

    private void populateCollidableBlocks() {
        collidable.clear();

        Vector2 pos = jaxon.getPosition().cpy().add(jaxon.getVelocity().cpy());

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
        
        int size = world.getEntities().size;
        for(int i = 0; i < size; i++){
            Entity current = world.getEntities().get(i);
            if (current.isCollideable() || current.isTouchable()) {
                float xDist = Math.abs(current.getPosition().x - pos.x);
                float yDist = Math.abs(current.getPosition().y - pos.y);

                if (xDist < 1F && yDist < 1F) {
                    collidable.add(current);
                }
            }
        }
    }


    private boolean processInput() {
		if (keys.get(Keys.JUMP)) {
			if (!jaxon.getState().equals(Jaxon.State.JUMPING) && grounded) {
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				jaxon.setState(Jaxon.State.JUMPING);
				jaxon.getVelocity().y = JUMP_HEIGHT; 
				grounded = false;
			} else {
				if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
					jumpingPressed = false;
				} else {
					if (jumpingPressed) {
						jaxon.getVelocity().y = JUMP_HEIGHT;
					}
				}
			}
		}
		if (keys.get(Keys.LEFT)) {
			// left is pressed
			jaxon.setFacingLeft(true);
			if (!jaxon.getState().equals(Jaxon.State.JUMPING)) {
				jaxon.setState(Jaxon.State.WALKING);
			}
			jaxon.getAcceleration().x = -ACCELERATION;
		} else if (keys.get(Keys.RIGHT)) {
			// right is pressed
			jaxon.setFacingLeft(false);
			if (!jaxon.getState().equals(Jaxon.State.JUMPING)) {
				jaxon.setState(Jaxon.State.WALKING);
			}
			jaxon.getAcceleration().x = ACCELERATION;
		} else {
			if (!jaxon.getState().equals(Jaxon.State.JUMPING)) {
				jaxon.setState(Jaxon.State.IDLE);
			}
			jaxon.getAcceleration().x = 0;

		}
		return false;
	}

	public boolean isLeftDown() {
		return isLeftDown;
	}

	public boolean isRightDown() {
		return isRightDown;
	}

	public boolean isJumpDown() {
		return isJumpDown;
	}

	public boolean isFireDown() {
		return isFireDown;
	}
}

