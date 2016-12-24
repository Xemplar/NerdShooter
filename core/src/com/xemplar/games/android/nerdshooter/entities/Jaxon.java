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
package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.controller.JaxonController;
import com.xemplar.games.android.nerdshooter.entities.mobs.EnemyMob;
import com.xemplar.games.android.nerdshooter.inventory.Inventory;
import com.xemplar.games.android.nerdshooter.items.ItemStack;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class Jaxon extends Entity{
    private static final float RUNNING_FRAME_DURATION = 0.06f;
    private static float space = 5F;
    
    private TextureRegion jaxonIdleLeft;
    private TextureRegion jaxonIdleRight;
    private TextureRegion jaxonFrame;
    private boolean locked = false;

    private long debounce = 0L;
    private float drawX;
    private boolean flipped;
    
    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;
    
    public Jaxon(Vector2 startPosition){
        super(startPosition, 1.0F / 3F, 1.0F / 2F, 8);
        
        controller = new JaxonController(this);
        
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

    public boolean isTouchable(){
        return true;
    }

    public void onTouch(Entity e){
        e.onTouch(this);
    }

    public boolean hasInventory() {
        return true;
    }

    public boolean hasInvSpace() {
        return inventory.hasSpace();
    }
    
    public boolean isRespawnable(){
        return true;
    }
    
    public JaxonController getController(){
        return (JaxonController)controller;
    }

    public void attackByEnemyMob(EnemyMob mob, int hurtAmt){
        if(debounce == 0){
            debounce = 100L;
            hurt(hurtAmt);
            System.out.println(health);
        }
    }

    public void updateEntity(float delta) {
        if(debounce != 0) debounce--;
        stateTime += delta;
        
        position.mulAdd(velocity.cpy(), delta);
        bounds.x = position.x;
        bounds.y = position.y;

        if(isDead()) onKill();
    }
    
    public void onKill(){
        debounce = 0L;
        hidden = true;
        if(!reset){
            Timer.schedule(respawn, 1F);
            reset = true;
        }
    }

    public void renderHealth(SpriteBatch batch, float width, float height, float size){
        TextureRegion full = GameScreen.getTextureAltlas().findRegion("hud_heartFull");
        TextureRegion half = GameScreen.getTextureAltlas().findRegion("hud_heartHalf");
        TextureRegion empty = GameScreen.getTextureAltlas().findRegion("hud_heartEmpty");

        int hearts = this.maxHealth / 2;
        int left = health;
        for(int i = 0; i < hearts; i++){
            if(left >= 2){
                batch.draw(full, i * (size + space), height - (size + space), size, size);
                left -= 2;
            } else if(left == 1){
                batch.draw(half, i * (size + space), height - (size + space), size, size);
                left = 0;
            } else if(left == 0){
                batch.draw(empty, i * (size + space), height - (size + space), size, size);
            }
        }
    }

    public void render(SpriteBatch batch) {
        jaxonFrame = isFacingLeft() ? jaxonIdleLeft : jaxonIdleRight;
        if(getState().equals(Jaxon.State.WALKING)) {
            jaxonFrame = isFacingLeft() ? walkLeftAnimation.getKeyFrame(getStateTime(), true) : walkRightAnimation.getKeyFrame(getStateTime(), true);
        }
        
        if(!isHidden() && debounce % 3 == 0){
            batch.draw(jaxonFrame, (getPosition().x - drawX), getPosition().y, (0.5F), (0.5F));
        }
        
        //Render Selected Item
        
        int selected = inventory.getSelectedItem();
        if(selected != -1){
            ItemStack stack = inventory.getItem(selected);
            if(stack != null){
                float x = isFacingLeft() ? (getPosition().x - drawX) : (getPosition().x - drawX) + bounds.getWidth() * 0.75F;
                TextureRegion reg = GameScreen.getTextureAltlas().findRegion(stack.getMock().regionID);
                if(isFacingLeft() && !flipped){
                    reg.flip(true, false);
                    flipped = true;
                } else if(!isFacingLeft() && flipped){
                    reg.flip(true, false);
                    flipped = false;
                }
                batch.draw(reg, x, (getPosition().y + ((bounds.getHeight() / 2F) - 0.25F)), 0.25F, 0.25F);
            }
        }
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public boolean isLocked(){
        return locked;
    }
}

