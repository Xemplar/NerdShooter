package com.xemplar.games.pc.nerdshooter;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xemplar.games.android.nerdshooter.NerdShooter;
import com.xemplar.games.android.nerdshooter.utils.InterScreenData;

public class Main{
	private static InterScreenData data;
	
	public static void main (String[] args) {
		data = InterScreenData.getInstance("desktop_keys");
		int[] keys = new int[]{Keys.LEFT, Keys.RIGHT, Keys.SPACE, Keys.Z};
		data.setData(keys);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.allowSoftwareMode = true;
		config.fullscreen = false;
		config.width = 600;
		config.height = 350;
		
        new LwjglApplication(new NerdShooter(), config);
    }
}
