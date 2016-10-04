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

public final class XPMLDecoder {
    private static final String lineSep = "<line_sep>";

    public XPMLDecoder(){
        
    }

    
    public Array<XPMLItem> decodeString(String text){
        Array<XPMLItem> returnValue = new Array<XPMLItem>();

        String[] lines = text.split(System.getProperty("line.separator"));

        for(int i = 0; i < lines.length; i++){
            String current = lines[i].trim();

            if(current.startsWith("<")){
                String tag = current.trim().substring(1);
                XPMLItem item = new XPMLItem(tag);

                i++;

                while(!(current = lines[i]).equals("/>")){
                    if(current.startsWith("<")){
                        String in = current;
                        i++;
                        while(!(current = lines[i]).equals("/>")){
                            in += ("\n" + current);
                            i++;
                        }
                        in += ("\n/>");
                        item.addAllChildren(decodeString(in));
                    }

                    String attr_name = StringUtils.getBetween(current, "", ":").trim();
                    String attr_value = StringUtils.getBetween(current, ":", ";").trim().replace(lineSep, "\n");

                    if(attr_name != ""){
                        item.addElement(attr_name, attr_value);
                    }

                    i++;
                }

                returnValue.add(item);
                item = null;
            }
        }
        return returnValue;
    }
}
