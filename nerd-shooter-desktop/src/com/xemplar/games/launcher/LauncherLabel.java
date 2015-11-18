package com.xemplar.games.launcher;

import java.awt.Color;
import java.awt.Graphics2D;

public class LauncherLabel extends LauncherView{
	public static final int ALIGN_LEFT = 0x01;
	public static final int ALIGN_CENTER = 0x02;
	public static final int ALIGN_RIGHT = 0x03;
	
	protected String text = "";
	protected int textAlign = ALIGN_CENTER;
	protected Color textColor = new Color(0x000000);
	protected Color bgColor = new Color(0xFFFFFF);
	
	public LauncherLabel(int width, int height) {
		super(width, height);
	}
	
	public LauncherLabel(int width, int height, String text) {
		super(width, height);
		this.text = text;
	}
	
	public LauncherLabel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public LauncherLabel(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.text = text;
	}

	public boolean isHoverable() {
		return false;
	}

	public boolean isClickable() {
		return false;
	}
	
	public void setTextColor(Color c){
		this.textColor = c;
	}
	
	public void setBackgroundColor(Color c){
		this.bgColor = c;
	}
	
	public void setTextColor(int c){
		this.textColor = new Color(c);
	}
	
	public void setBackgroundColor(int c){
		this.bgColor = new Color(c);
	}
	
	public void setTextAlign(int align){
		this.textAlign = align;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void render(Graphics2D g) {
		Color pre = g.getColor();
		
		g.setColor(bgColor);
		g.fillRect(x, y, width, height);
		g.setColor(textColor);
		
		int textWidth = g.getFontMetrics().stringWidth(text);
		int textHeight = g.getFontMetrics().getHeight();
		
		int centerY = (int)((height / 2F) + (textHeight / 4F));
		int centerX = (int)((width / 2F) - (textWidth / 2F));
		
		if(textAlign == ALIGN_LEFT){
			if(width >= (textWidth + 5)){
				g.drawString(text, x + 5, y + centerY);
			}
		} else if(textAlign == ALIGN_RIGHT){
			if(width >= (textWidth + 5)){
				g.drawString(text, (x + width) - (5 + textWidth), y + centerY);
			}
		} else {
			g.drawString(text, x + centerX, y + centerY);
		}
		
		g.setColor(pre);
	}
}