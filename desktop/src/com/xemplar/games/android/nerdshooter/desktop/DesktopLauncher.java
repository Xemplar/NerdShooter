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
 * along with this program.  If not, see <http:www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.desktop;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.utils.InterScreenData;

import java.awt.*;

public class DesktopLauncher {
	private static InterScreenData data;
	private static String[] res = new String[]{"640, 360", "1024, 576", "1280, 720", "Full Screen"};
	
	public static void main (String[] args) {
		data = InterScreenData.getInstance("desktop_keys");
		int[] keys = new int[]{Keys.LEFT, Keys.RIGHT, Keys.SPACE, Keys.Z};
		data.setData(keys);
		
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e){
			
		}
		
		String out = (String) JOptionPane.showInputDialog(null, 
		        "Setup",
		        "Screen Resolution",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        res, 
		        res[0]);
		
		String[] options = null;
		
		if(out == null){
			return;
		} else if(out.equals("Full Screen")){
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			options = new String[]{(int)screenSize.getWidth() + "", (int)screenSize.getWidth() + ""};
		} else {
			options = out.split(", ");
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.allowSoftwareMode = true;
		config.fullscreen = out.equals("Full Screen");
		config.width = Integer.parseInt(options[0]);
		config.height = Integer.parseInt(options[1]);
		
        new LwjglApplication(new NerdShooter(), config);
    }
}
