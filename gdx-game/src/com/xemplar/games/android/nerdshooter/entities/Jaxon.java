package com.xemplar.games.android.nerdshooter.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.*;
import com.xemplar.games.android.nerdshooter.inventory.*;
import com.xemplar.games.android.nerdshooter.items.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;

public class Jaxon extends Entity{
    private static final float RUNNING_FRAME_DURATION = 0.06f;
	
	private TextureRegion jaxonIdleLeft;
    private TextureRegion jaxonIdleRight;
    private TextureRegion jaxonFrame;
    
    private float drawX;
    
    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;
    
    public Jaxon(Vector2 startPosistion){
		super(startPosistion, WIDTH / 2F, HEIGHT / 3F, 8);
        
        drawX = (getWidth() / 2F) - (WIDTH / 2F);
        drawX = (drawX < 0) ? -drawX : drawX;
        drawX = drawX / 4F;
        
        inventory = new Inventory(this, 4);
        loadTextures();
	}
    
    private void loadTextures() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("textures/nerdshooter.pack"));

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

    public void render(SpriteBatch batch, float ppuX, float ppuY) {
        jaxonFrame = isFacingLeft() ? jaxonIdleLeft : jaxonIdleRight;
        if(getState().equals(Jaxon.State.WALKING)) {
            jaxonFrame = isFacingLeft() ? walkLeftAnimation.getKeyFrame(getStateTime(), true) : walkRightAnimation.getKeyFrame(getStateTime(), true);
        }
        
        if(!isHidden()){
            batch.draw(jaxonFrame, (getPosition().x - drawX) * ppuX, getPosition().y * ppuY, (WIDTH / 2F) * ppuX, (HEIGHT / 2F) * ppuY);
        }
    }
}

