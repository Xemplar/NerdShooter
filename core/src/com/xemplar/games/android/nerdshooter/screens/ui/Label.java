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

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Label extends View{
	protected BitmapFont font;
	protected String text;
	
	public Label(BitmapFont font, String text, float x, float y, float width, float height){
		super(x, y, width, height);
		this.font = font;
		this.text = text;
		
	}
    
	public void setText(String text){
        this.text = text;
    }
    
	public void render(SpriteBatch batch) {
        font.draw(batch, text, bounds.getX(), bounds.getY() + ((bounds.getHeight() / 2F) + (font.getLineHeight() / 4F)));
	}
}
