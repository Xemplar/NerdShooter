package com.xemplar.games.android.nerdshooter.blocks;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xemplar.games.android.nerdshooter.entities.Entity;
import com.xemplar.games.android.nerdshooter.screens.GameScreen;

public class LockedDoor extends DoorBlock implements Switchable, Uncloneable{
    public Array<Switchable> sw = new Array<Switchable>();
    public Array<Switchable> yet = new Array<Switchable>();
    public String regionACT;
    protected int state = Integer.MAX_VALUE;

    public LockedDoor(Vector2 pos, String regionID, String regionACT, Vector2 loc, int state) {
        super(pos, regionID, loc);
        this.regionACT = regionACT;
        this.state = state;
    }

    public LockedDoor(Vector2 pos, String regionID, String regionACT, Vector2 loc, int state, float size) {
        super(pos, regionID, loc, size);
        this.regionACT = regionACT;
        this.state = state;
    }

    public LockedDoor(Vector2 pos, String regionID, String regionACT, Vector2 loc, int state, float width, float height) {
        super(pos, regionID, loc, width, height);
        this.regionACT = regionACT;
        this.state = state;
    }

    public void addSwitchable(Switchable s){
        sw.add(s);
        yet.add(s);
    }

    public int getState(){
        int state = 0;
        for(Switchable s : sw){
            state += s.isActivated() ? 1 : 0;
        }
        return state;
    }

    public boolean isActivated() {
        return getState() >= state;
    }

    public void onTouch(Entity e){
        if(isActivated()) super.onTouch(e);
    }

    public TextureRegion getTexture() {
        return GameScreen.getTextureAltlas().findRegion(isActivated() ? regionACT : regionID);
    }

    public LockedDoor clone(Vector2 pos){
        return new LockedDoor(pos, regionID, regionACT, loc, state, this.bounds.getWidth(), this.bounds.getHeight());
    }
}
