package com.xemplar.games.android.nerdshooter.utils;
import com.badlogic.gdx.utils.*;

public final class InterScreenData {
    private static Array<InterScreenData> screenDatas = new Array<InterScreenData>();
    
    private String name = "";
    private Object data = null;
    
    private InterScreenData(String name){
        this.name = name;
    }
    
    public static InterScreenData getInstance(String name){
        for(InterScreenData data : screenDatas){
            if(data.getName().equals(name)) return data;
        }
        
        InterScreenData data = new InterScreenData(name);
        screenDatas.add(data);
        
        return data;
    }
    
    public String getName(){
        return name;
    }
    
    public void setData(Object data){
        this.data = data;
    }
    
    public Object getData(){
        return data;
    }
}
