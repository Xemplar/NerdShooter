/*
 * NerdShooter is a pseudo libray project for future Xemplar 2D Side Scroller Games.
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

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.inventory.Inventory;

public abstract class Entity extends Block{
	public enum State {
        IDLE, WALKING, JUMPING, DYING
	}

    public static final float SPEED = 5f;  // unit per second
    public static final float JUMP_VELOCITY = 1f;
    public static final int UNLIMITED = 0xF00000;
    protected int health = 0;
    protected int maxHealth;
    
    public Inventory inventory;
    private boolean hidden, reset;

	protected float stateTime = 0;
    protected Vector2 acceleration = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected State state = State.IDLE;
    protected boolean facingLeft = true;
    
    public Entity(Vector2 position, int health) {
		super(position, "tochLit");
        this.position = position;
        this.bounds.height = 1F;
        this.bounds.width = 1F;
		this.bounds.x = position.x;
		this.bounds.y = position.y;
        this.health = health;
        this.maxHealth = health;
    }
	
    public Entity(Vector2 position, float size, int health) {
        this(position, health);
        this.bounds.height = size;
        this.bounds.width = size;
    }
    
    public Entity(Vector2 position, float width, float height, int health) {
        this(position, health);
        this.bounds.height = width;
        this.bounds.width = height;
    }
    
	public Entity(Vector2 position, String regionID, int health){
		this(position, health);
		
		this.regionID = regionID;
	}

    public Entity(Vector2 position, String regionID, float size, int health){
        this(position, regionID, health);
        this.bounds.height = size;
        this.bounds.width = size;
	}
    
    public Entity(Vector2 position, String regionID, float width, float height, int health){
        this(position, regionID, health);
        this.bounds.height = width;
        this.bounds.width = height;
	}
    
    public void setState(State newState) {
        this.state = newState;
    }

    public void setFacingLeft(boolean left){
        this.facingLeft = left;
    }

    public boolean isFacingLeft(){
        return facingLeft;
    }

    public State getState(){
        return state;
    }

    public float getStateTime(){
        return stateTime;
    }
    
	public void setPosition(Vector2 position) {
		this.position = position;
		this.bounds.setX(position.x);
		this.bounds.setY(position.y);
	}

    public void setCheckPoint(Vector2 point){
        this.spawnPoint = point.cpy();
        this.spawnPoint.add(0.025F, 0);
    }
    
	public void respawn(){
        this.health = maxHealth;
        velocity.set(0F, 0F);
		setPosition(spawnPoint.cpy());
        
        reset = false;
	}
	
	public Rectangle getBounds(){
		return bounds;
    }

	public Vector2 getVelocity(){
		return velocity;
	}

	public Vector2 getAcceleration(){
		return acceleration;
	}

	public Vector2 getPosition(){
		return position;
    }
    
    public void kill(){
        this.health = 0;
    }
    
    public void hurt(int amt){
        if(!isDead()){
            this.health = this.health - amt;
        }
    }

    public boolean isHidden() {
        return hidden;
    }
    
    public boolean isDead(){
        return health <= 0;
    }
    
    public abstract boolean hasInventory();
    public abstract boolean hasInvSpace();
    public abstract void updateEntity(float delta);
    
    public void update(float delta){
        if(isDead() && !(this instanceof Projectile)){
            if(hasInventory() && inventory.hasItems()){
                inventory.clear();
            }
            
            if(!reset){
                hidden = true;
                reset = true;
                Timer.schedule(run, 1F);
            }
        }
        
        if(!isHidden()){
            updateEntity(delta);
        }
    }
    
    Timer.Task run = new Timer.Task(){
        public void run(){
            hidden = false;
        }
    };
}
