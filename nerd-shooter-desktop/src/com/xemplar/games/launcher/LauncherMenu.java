package com.xemplar.games.launcher;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.xemplar.games.launcher.action.OnButtonPress;

public class LauncherMenu extends JPanel implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = -3968795921698453310L;
	private final Dimension SIZE = new Dimension(640, 360);
	private final ArrayList<LauncherView> views = new ArrayList<LauncherView>();
	private JFrame frame;
	
	public LauncherMenu(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setUndecorated(true);
		
		this.setMinimumSize(SIZE);
		this.setMaximumSize(SIZE);
		this.setPreferredSize(SIZE);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		frame.add(this);
		frame.pack();
		
		Random rand = new Random();
		
		int lblHeight = 25, lblWidth = 100;
		
		OnButtonPress press = new OnButtonPress(){
			public void onButtonPress(LauncherButton button, MouseEvent e) {
				
			}
		};
		
		for(int i = 0; i < this.getHeight() / lblHeight; i++){
			for(int b = 0; b < this.getWidth() / lblWidth; b++){
				int prime = rand.nextInt(0xFFFFFF);
				int second = (~rand.nextInt(0xFFFFFF)) & 0xFFFFFF;
				
				LauncherButton lbl = new LauncherButton(lblWidth, lblHeight, "Try");
				lbl.setPosition(b * lbl.width, i * lbl.height);
				lbl.setTextColor(prime);
				lbl.setBackgroundColor(second);
				lbl.addOnButtonPressListener(press);
				
				views.add(lbl);
			}
		}
		
		frame.setVisible(true);
	}
	
	public void paint(Graphics in){
		Graphics2D g = (Graphics2D) in;
		
		for(LauncherView view : views){
			view.render(g);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for(LauncherView view : views){
			if(view.isHoverable()) view.onHover(e);
		}
		
		repaint();
	}

	public void mouseClicked(MouseEvent e) {
		for(LauncherView view : views){
			if(view.isClickable()) view.onClick(e);
		}
		
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	public static void start(){
		new LauncherMenu();
	}

	public void mouseEntered(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
}
