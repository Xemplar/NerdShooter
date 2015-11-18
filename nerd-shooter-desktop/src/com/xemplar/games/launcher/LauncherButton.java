package com.xemplar.games.launcher;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.xemplar.games.launcher.action.OnButtonPress;

public class LauncherButton extends LauncherLabel{
	protected final ArrayList<OnButtonPress> listeners = new ArrayList<OnButtonPress>();
	protected boolean isHoveredOver = false;
	
	public LauncherButton(int width, int height) {
		super(width, height);
	}
	
	public LauncherButton(int width, int height, String text) {
		super(width, height, text);
	}
	
	public LauncherButton(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public LauncherButton(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}
	
	public void addOnButtonPressListener(OnButtonPress press){
		listeners.add(press);
	}
	
	public void removeOnButtonPressListener(OnButtonPress press){
		listeners.remove(press);
	}
	
	public boolean onClick(MouseEvent e){
		boolean inSide = true;
		
		inSide &= x <= e.getX();
		inSide &= x + width >= e.getX();
		
		inSide &= y <= e.getY();
		inSide &= y + height >= e.getY();
		
		if(inSide){
			for(int i = 0; i < listeners.size(); i++){
				listeners.get(i).onButtonPress(this, e);
			}
		}
		
		return inSide;
	}
	
	public boolean onHover(MouseEvent e){
		boolean inSide = true;
		
		inSide &= x <= e.getX();
		inSide &= x + width >= e.getX();
		
		inSide &= y <= e.getY();
		inSide &= y + height >= e.getY();
		
		isHoveredOver = inSide;
		
		return inSide;
	}
	
	public void render(Graphics2D g) {
		Color pre = g.getColor();
		
		g.setColor(bgColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(isHoveredOver ? textColor.brighter() : textColor);
		
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
