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
package com.xemplar.games.android.nerdshooter.screens;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;

public class ScreenButton {
    private Rectangle bounds = new Rectangle();
    private boolean pressed, use;
    private String text = "";
    private Texture tex_not_pressed;
    private Texture tex_pressed;
    public float x, y, height, width;
    private BitmapFont font;
    private int action;
    
    public ScreenButton(BitmapFont font, String text, float x, float y, float width, float height){
        this.bounds.set(x, y, width, height);
        this.text = text;
        this.font = font;

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(0.5F, 0.5F, 0.5F, 1.0F);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);
        
        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(0.7F, 0.7F, 0.7F, 1.0F);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
        
        this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }
    
    public ScreenButton(BitmapFont font, String text, float[] colors, float x, float y, float width, float height){
        this.bounds.set(x, y, width, height);
        this.text = text;
        
        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
        
        this.font = font;
        
        this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }
    
    public ScreenButton(BitmapFont font, String text, float[] colors, float[] pressedColors, float x, float y, float width, float height){
        this.bounds.set(x, y, width, height);
        this.text = text;
        
        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(pressedColors[0], pressedColors[1], pressedColors[2], pressedColors[3]);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
        
        this.font = font;
        
        this.x = bounds.x;
        this.y = bounds.y;
        this.width = bounds.width;
        this.height = bounds.height;
    }
    
    public ScreenButton setActionNumber(int action){
        this.action = action;
        
        return this;
    }
    
    public int getAction(){
        return action;
    }
    
    public ScreenButton setPressed(boolean pressed){
        this.pressed = pressed;
        
        return this;
    }
    
    public boolean isInside(Vector2 vec){
        return bounds.contains(vec);
    }
    
    public boolean isInside(float x, float y){
        return bounds.contains(x, y);
    }
    
    public void render(SpriteBatch batch){
        if(pressed){
            batch.draw(tex_pressed, x, y, width, height);
        } else {
            batch.draw(tex_not_pressed, x, y, width, height);
        }
        
        font.draw(batch, text, bounds.getX() + (bounds.getHeight() * 2F), bounds.getY() + ((bounds.getHeight() / 2F) + (font.getLineHeight() / 4F)));
    }
}
