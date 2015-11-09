package com.xemplar.games.android.nerdshooter.screens.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xemplar.games.android.nerdshooter.NerdShooter;

public class Label extends View{
	protected BitmapFont font;
	protected String text;
	
	public Label(BitmapFont font, String text, float x, float y, float width, float height){
		super(x, y, width, height);
		this.font = font;
		this.text = text;
		
	}
	
	public void render(SpriteBatch batch) {
		NerdShooter.layout.setText(NerdShooter.label, text);
        float width = NerdShooter.layout.width;
        
        font.draw(batch, text, bounds.getX() + ((bounds.width / 2) - (width / 2)), bounds.getY() + ((bounds.getHeight() / 2F) + (font.getLineHeight() / 4F)));
	}
}
