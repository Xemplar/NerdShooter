package com.xemplar.games.launcher;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class LauncherView {
	protected final int width, height;
	protected int x, y;
	
	public LauncherView(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public LauncherView(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract boolean isHoverable();
	public abstract boolean isClickable();
	public abstract void render(Graphics2D g);
	
	public boolean onClick(MouseEvent e){
		return false;
	}
	
	public boolean onHover(MouseEvent e){
		return false;
	}
}
