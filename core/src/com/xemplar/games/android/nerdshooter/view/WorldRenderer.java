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
package com.xemplar.games.android.nerdshooter.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.blocks.Block;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.entities.Jaxon;
import com.xemplar.games.android.nerdshooter.extras.Extra;
import com.xemplar.games.android.nerdshooter.model.World;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public final class WorldRenderer {
    private static final float CAMERA_WIDTH = 12f;
    private static final float CAMERA_HEIGHT = 7f;
    public static RendererModifier modify;

    public int width;
    public int height;

    World world;
    OrthographicCamera cam;
    Vector2 center = new Vector2();
    ShapeRenderer debugRenderer = new ShapeRenderer();
    SpriteBatch spriteBatch;

    private Viewport port;
    private boolean debug = false;
    
    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.port = new ExtendViewport(CAMERA_WIDTH, CAMERA_HEIGHT, cam);
        this.cam.position.set(cam.viewportWidth / 2f, cam.viewportWidth / 2f, 0);
        this.cam.update();
        this.debug = false;//debug;
        this.modify = new RendererModifier(this);
        spriteBatch = new SpriteBatch();
    }
    
    public void render(float delta) {
        world.getJaxon().getBounds().getCenter(center);
        moveCamera(center.x, center.y);
        spriteBatch.setProjectionMatrix(cam.combined);

        modify.applyMod(delta);
        spriteBatch.begin();
            if(NerdShooter.sanic){
                spriteBatch.draw(GameScreen.tex, cam.position.x - (CAMERA_WIDTH / 2F), cam.position.y  - (CAMERA_HEIGHT / 2F), CAMERA_WIDTH, CAMERA_HEIGHT);
            }
            drawBlocks();
            drawExtras();
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
        Array<Block> blocks = world.getVisibleBlocks((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT);

        for (Block block : blocks) {
            block.render(spriteBatch);
        }
    }

    private void drawExtras() {
        Array<Extra> extras = world.getExtras((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT);

        for(Extra extra : extras) {
            extra.render(spriteBatch);
        }
    }

    private void drawJaxon() {
        Jaxon jaxon = world.getJaxon();
        
        jaxon.render(spriteBatch);
    }
    
    private void drawEntities(){
        Array<Entity> entities = world.getEntities();
        
        for(Entity entity : entities){
            entity.render(spriteBatch);
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
        
        for(Entity e : world.getEntities()){
            Rectangle rect = e.getBounds();
            debugRenderer.setColor(new Color(0, 1, 1, 1));
            debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        
        Jaxon jaxon = world.getJaxon();
        Rectangle rect = jaxon.getBounds();
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.end();
    }
    
    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
    }

    public static void setModify(int mod, ModFinishListener listener){
        modify.setModification(mod, listener);
    }
}

