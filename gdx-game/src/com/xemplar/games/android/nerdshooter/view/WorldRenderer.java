package com.xemplar.games.android.nerdshooter.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.model.*;
import java.util.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.xemplar.games.android.nerdshooter.entities.*;
import com.badlogic.gdx.utils.*;

public class WorldRenderer {
	private static final float CAMERA_WIDTH = 12f;
    private static final float CAMERA_HEIGHT = 7f;
	
    private World world;
    private OrthographicCamera cam;
	private Vector2 center = new Vector2();
    ShapeRenderer debugRenderer = new ShapeRenderer();
	
    /** Inventory **/
	
    private SpriteBatch spriteBatch;
	
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;
	
    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.cam.update();
        this.debug = debug;
        spriteBatch = new SpriteBatch();
    }
	
    public void render() {
		world.getJaxon().getBounds().getCenter(center);
		moveCamera(center.x, center.y);
		spriteBatch.setProjectionMatrix(cam.combined);
		
		spriteBatch.begin();
          drawBlocks();
		  drawEntities();
          drawJaxon();
		spriteBatch.end();
		
		if (debug) drawDebug();
    }

	public void moveCamera(float x,float y){
		float xCam = CAMERA_WIDTH / 2F;
		float yCam = CAMERA_HEIGHT / 2F;
		
		if (x > (CAMERA_WIDTH / 2F)) {
			xCam = x;
		} if(x > (world.getLevel().getWidth() - (CAMERA_WIDTH / 2F))){
			xCam = world.getLevel().getWidth() - (CAMERA_WIDTH / 2F);
		}
		
		if (y > (CAMERA_HEIGHT / 2F)) {
			yCam = y;
		} if(y > (world.getLevel().getHeight() - (CAMERA_HEIGHT / 2F))){
			yCam = world.getLevel().getHeight() - (CAMERA_HEIGHT / 2F);
		}
		
		cam.position.set(xCam, yCam, 0);
		cam.update();
	}
	
    private void drawBlocks() {
        for (Block block : world.getBlocks((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            if(!block.isHidden()){
                block.render(spriteBatch, ppuX, ppuY);
            }
        }
    }

    private void drawJaxon() {
        Jaxon jaxon = world.getJaxon();
        
        jaxon.render(spriteBatch, ppuX, ppuY);
    }
	
	private void drawEntities(){
		Array<Entity> entites = world.getEntities();
		
		for(Entity entity : entites){
            entity.render(spriteBatch, ppuX, ppuY);
		}
	}

    private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		for (Block block : world.getBlocks((int)CAMERA_WIDTH, (int)CAMERA_HEIGHT)) {
			Rectangle rect = block.getBounds();
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
		
		Jaxon jaxon = world.getJaxon();
		Rectangle rect = jaxon.getBounds();
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.end();
	}
	
	private int count = 0;
	
	public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = 1;//(float)width / CAMERA_WIDTH;
        ppuY = 1;//(float)height / CAMERA_HEIGHT;
    }
}

