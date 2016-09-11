/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
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
package com.xemplar.games.android.nerdshooter.screens.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class SwitchButton extends Button implements Saveable{
	protected final String yes_string, no_string;
	protected String key;

	public SwitchButton(BitmapFont font, float x, float y, float width, float height, String key) {
		super(font, "No", x, y, width, height);
		this.key = key;

		this.yes_string = "Yes";
		this.no_string = "No";
	}

	public SwitchButton(BitmapFont font, NinePatch patch, float x, float y, float width, float height, String key) {
		super(font, "No", x, y, width, height);
		this.key = key;

        this.nine_patch = patch;

		this.yes_string = "Yes";
		this.no_string = "No";
	}

    public SwitchButton(BitmapFont font, float x, float y, float width, float height, String no, String yes, String key) {
        super(font, no, x, y, width, height);
        this.key = key;

        this.yes_string = yes;
        this.no_string = no;
    }

    public SwitchButton(BitmapFont font, NinePatch patch, float x, float y, float width, float height, String no, String yes, String key) {
        super(font, no, x, y, width, height);
        this.key = key;

        this.nine_patch = patch;

        this.yes_string = yes;
        this.no_string = no;
    }
	
	public void toggle(){
		this.pressed = !this.pressed;
		this.text = this.pressed ? yes_string : no_string;
	}
	
	public SwitchButton setPressed(boolean pressed){
		return this;
	}

	public String getSaveState() {
		return Boolean.toString(pressed);
	}
	
	public String getKey(){
		return this.key;
	}
	
	public void setFromSave(String state) {
		this.pressed = Boolean.parseBoolean(state);
		this.text = this.pressed ? yes_string : no_string;
	}
}
