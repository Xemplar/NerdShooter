package com.xemplar.games.android.nerdshooter.model;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.xemplar.games.android.nerdshooter.blocks.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class World {
	private Array<Rectangle> collisionRects = new Array<Rectangle>();
	private Level level;
    private Jaxon jaxon;
	
    public Array<Rectangle> getCollisionRects() {
        return collisionRects;
    }
	
    public Jaxon getJaxon() {
        return jaxon;
    }
	
    public Level getLevel() {
        return level;
    }

	public Array<Entity> getEntities(){
		return level.getEntities();
	}
	
	public Block getBlock(Vector2 pos){
		Array<Block> blocks = getBlocks(level.getWidth(), level.getHeight());
		for(Block block : blocks){
			if(block.getBounds().contains(pos)){
				return block;
            }
		}
		
		return null;
	}
	
    public Array<Block> getBlocks(int width, int height) {
        int x = (int)jaxon.getPosition().x - width;
        int y = (int)jaxon.getPosition().y - height;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
		
        int x2 = x + 2 * width;
        int y2 = y + 2 * height;
		
        if (x2 > level.getWidth()) {
            x2 = level.getWidth() - 1;
        }
        if (y2 > level.getHeight()) {
            y2 = level.getHeight() - 1;
        }
		
        Array<Block> blocks = new Array<Block>();
        Block block = null;
		
        for (int col = x; col <= x2; col++) {
            for (int row = y; row <= y2; row++) {
				if(col >= level.getWidth()) continue;
				if(row >= level.getHeight()) continue;
				
                block = level.getBlocks()[col + row * level.getWidth()];
                if (block != null) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }
	
	public World(int levelNum){
        level = new Level(levelNum);
		jaxon = new Jaxon(level.jaxonStart);
	}
}

