package com.xemplar.games.android.nerdshooter.blocks;
import com.badlogic.gdx.math.*;
import com.xemplar.games.android.nerdshooter.screens.*;
import com.xemplar.games.android.nerdshooter.entities.*;

public class ExitBlock extends Block {
    public static final int EXIT_NORMAL = 0x000001;
    public static final int EXIT_SPECIAL = 0x000002;
    public static final int EXIT_NOCLEAR = 0x000003;
    
    private int exitCode;
    
    public ExitBlock(Vector2 position, String regionID, int exitCode){
        super(position, regionID);
        this.exitCode = exitCode;
    }
    
    public boolean isCollideable() {
        return false;
    }
    
    public boolean isTouchable() {
        return true;
    }
    
    public void onTouch(Entity e) {
        System.out.println("Done");
        GameScreen.finishLevel(exitCode);
    }
}
