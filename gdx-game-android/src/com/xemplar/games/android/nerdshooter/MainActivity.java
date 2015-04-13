package com.xemplar.games.android.nerdshooter;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    private NerdShooter shooter;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = true;
        shooter = new NerdShooter();
        initialize(shooter, config);
    }
    
    public void onBackPressed() {
        
    }
}
