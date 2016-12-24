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
 * along with this program.  If not, see <http:www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.entities.mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Jaxon;
import com.xemplar.games.android.nerdshooter.entities.ai.ChasePlayerAI;

public class EnemyMob extends Mob{
    private static final float RUNNING_FRAME_DURATION = 0.5f;
    private static TextureRegion ratIdleLeft;
    private static TextureRegion ratIdleRight;
    private static TextureRegion ratFrame;
    private static boolean loaded = false;

    private float drawX;

    private static Animation walkLeftAnimation;
    private static Animation walkRightAnimation;

    public EnemyMob(Vector2 position, float size, int health) {
        super(position.add(0.1F, 0.001F), size, health, new ChasePlayerAI(1F, 3F));
        drawX = (bounds.getWidth() / 2F) - (1.0F / 2F);
        drawX = (drawX < 0) ? -drawX : drawX;
        drawX = drawX / 4F;

        if(!loaded) loadTextures();
    }

    public EnemyMob(Vector2 position, float width, float height, int health) {
        super(position.add(0.1F, 0.001F), width, height, health, new ChasePlayerAI(1F, 3F));
        drawX = (bounds.getWidth() / 2F) - (1.0F / 2F);
        drawX = (drawX < 0) ? -drawX : drawX;
        drawX = drawX / 4F;

        if(!loaded) loadTextures();
    }

    public void updateEntity(float delta) {
        stateTime += delta;

        position.mulAdd(velocity.cpy(), delta);
        bounds.x = position.x;
        bounds.y = position.y;
    }

    private static void loadTextures(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.atlas"));

        if(!NerdShooter.sanic){
            ratIdleLeft = atlas.findRegion("rat00");

            ratIdleRight = new TextureRegion(ratIdleLeft);
            ratIdleRight.flip(true, false);

            TextureRegion[] walkLeftFrames = new TextureRegion[4];

            walkLeftFrames[0] = atlas.findRegion("rat00");
            walkLeftFrames[1] = atlas.findRegion("rat01");
            walkLeftFrames[2] = atlas.findRegion("rat00");
            walkLeftFrames[3] = atlas.findRegion("rat02");

            walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);

            TextureRegion[] walkRightFrames = new TextureRegion[4];

            for (int i = 0; i < 4; i++) {
                walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
                walkRightFrames[i].flip(true, false);
            }
            walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);

            loaded = true;
            return;
        }
        ratIdleLeft = atlas.findRegion("sanic");

        ratIdleRight = new TextureRegion(ratIdleLeft);
        ratIdleRight.flip(true, false);

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
        loaded = true;
    }

    public boolean isTouchable(){
        return true;
    }
    public void onTouch(Entity e){
        if(e instanceof Jaxon){
            ((Jaxon)e).attackByEnemyMob(this, 1);
        } else {
            e.onTouch(this);
        }
    }
    public boolean hasInventory() {
        return false;
    }
    public boolean hasInvSpace() {
        return false;
    }
    public boolean isRespawnable(){
        return false;
    }

    public void render(SpriteBatch batch) {
        ratFrame = isFacingLeft() ? walkLeftAnimation.getKeyFrame(getStateTime(), true) : walkRightAnimation.getKeyFrame(getStateTime(), true);

        if(!isHidden()){
            batch.draw(ratFrame, (getPosition().x - drawX), getPosition().y, (0.5F), (0.5F));
        }
    }
}
