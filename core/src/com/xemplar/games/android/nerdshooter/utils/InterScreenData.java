/*
 * NerdShooter is a pseudo library project for future Xemplar 2D Side Scroller Games.
 * Copyright (C) 2016  Rohan Loomis
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.utils;

import com.badlogic.gdx.utils.Array;

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
