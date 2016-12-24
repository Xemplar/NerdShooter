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
package com.xemplar.games.android.nerdshooter.screens.ui;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xemplar.games.android.nerdshooter.NerdShooter;

public class Button extends Label{
    protected float[] array_pressed, array_not_pressed;
    protected Color pressedColor = Color.LIGHT_GRAY, notPressedColor = Color.GRAY;
    protected boolean pressed;
    protected Texture tex_not_pressed;
    protected Texture tex_pressed;
    protected NinePatch nine_patch;
    protected TextureRegion tex;
    protected int drawMethod = 1;

    protected int action;
    
    public Button(BitmapFont font, String text, float x, float y, float width, float height){
        super(font, text, x, y, width, height);
        
        this.text = text;
        this.font = font;

        array_pressed = new float[]{0.5F, 0.5F, 0.5F, 1.0F};
        array_not_pressed = new float[]{0.5F, 0.5F, 0.5F, 1.0F};

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(0.5F, 0.5F, 0.5F, 1.0F);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);
        
        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(0.7F, 0.7F, 0.7F, 1.0F);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
    }
    
    public Button(BitmapFont font, String text, float[] colors, float x, float y, float width, float height){
        super(font, text, x, y, width, height);

        array_pressed = colors;
        array_not_pressed = colors;

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
    }
    
    public Button(BitmapFont font, String text, float[] colors, float[] pressedColors, float x, float y, float width, float height){
        super(font, text, x, y, width, height);

        array_pressed = colors;
        array_not_pressed = pressedColors;

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(pressedColors[0], pressedColors[1], pressedColors[2], pressedColors[3]);
        pix_pressed.fill();
        tex_pressed = new Texture(pix_pressed);
    }

    public Button(BitmapFont font, TextureRegion tex, float x, float y, float width, float height){
        super(font, "", x, y, width, height);

        Pixmap map = tex.getTexture().getTextureData().consumePixmap();

        this.font = font;
        this.tex = tex;

        array_pressed = new float[]{0.5F, 0.5F, 0.5F, 1.0F};
        array_not_pressed = new float[]{0.5F, 0.5F, 0.5F, 1.0F};

        System.out.println("Is Null : " + this.tex == null);

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_pressed = new Texture(pix_pressed);

        map.dispose();
    }

    public Button(BitmapFont font, NinePatch tex, float x, float y, float width, float height){
        super(font, "", x, y, width, height);

        this.font = font;
        this.nine_patch = tex;
    }

    public Button(BitmapFont font, NinePatch tex, String text, float x, float y, float width, float height){
        super(font, text, x, y, width, height);

        this.text = text;
        this.font = font;
        this.nine_patch = tex;
    }

    public Button(BitmapFont font, TextureRegion tex, float[] colors, float x, float y, float width, float height){
        super(font, "", x, y, width, height);

        Pixmap map = tex.getTexture().getTextureData().consumePixmap();

        this.tex = tex;

        array_pressed = colors;
        array_not_pressed = colors;

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        pix_not_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_pressed.fill();
        pix_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_pressed = new Texture(pix_pressed);

        map.dispose();
    }
    
    public Button(BitmapFont font, TextureRegion tex, float[] colors, float[] pressedColors, float x, float y, float width, float height){
        super(font, "", x, y, width, height);

        Pixmap map = tex.getTexture().getTextureData().consumePixmap();

        this.tex = tex;

        array_pressed = colors;
        array_not_pressed = pressedColors;

        Pixmap pix_not_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_not_pressed.setColor(colors[0], colors[1], colors[2], colors[3]);
        pix_not_pressed.fill();
        pix_not_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_not_pressed = new Texture(pix_not_pressed);

        Pixmap pix_pressed = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pix_pressed.setColor(pressedColors[0], pressedColors[1], pressedColors[2], pressedColors[3]);
        pix_pressed.fill();
        pix_pressed.drawPixmap(map, 0, 0, map.getWidth(), map.getHeight(), 0, 0, (int)width, (int)height);
        tex_pressed = new Texture(pix_pressed);

        map.dispose();
    }

    public void setTexture(TextureRegion tex){
        this.tex = tex;
    }

    public void setTexture(Texture tex){
        this.tex = new TextureRegion(tex);
    }
    
    public String getText(){
        return this.text;
    }

    public void setPressedColor(Color c){
        this.pressedColor = c;
    }

    public void setNotPressedColor(Color c){
        this.notPressedColor = c;
    }

    public Button setActionNumber(int action){
        this.action = action;
        
        return this;
    }
    
    public int getAction(){
        return action;
    }
    
    public Button setPressed(boolean pressed){
        this.pressed = pressed;
        
        return this;
    }
    
    public boolean isPressed(){
        return pressed;
    }
    
    public void render(SpriteBatch batch){
        if(nine_patch != null){
            nine_patch.setColor(pressed ? pressedColor : notPressedColor);
            nine_patch.draw(batch, x, y, width, height);
        } else {
            batch.draw(pressed ? tex_pressed : tex_not_pressed, x, y, width, height);
        }
        if(tex != null){
            batch.draw(tex, x, y, width, height);
        } else {
            NerdShooter.layout.setText(this.font, text);
            float width = NerdShooter.layout.width;
        
            font.draw(batch, text, bounds.getX() + ((bounds.width / 2) - (width / 2)), bounds.getY() + ((bounds.getHeight() / 2F) + (font.getLineHeight() / 4F)));
        }
    }
}
