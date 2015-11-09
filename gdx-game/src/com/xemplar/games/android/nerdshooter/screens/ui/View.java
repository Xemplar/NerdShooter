package com.xemplar.games.android.nerdshooter.screens.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class View {
    protected Rectangle bounds = new Rectangle();
	public float x, y, height, width;
	
	public View(float x, float y, float width, float height){
		this.bounds.set(x, y, width, height);
		this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
	}
	
	public boolean isInside(Vector2 vec){
        return bounds.contains(vec);
    }
    
    public boolean isInside(float x, float y){
        return bounds.contains(x, y);
    }
	
	public abstract void render(SpriteBatch batch);
}
