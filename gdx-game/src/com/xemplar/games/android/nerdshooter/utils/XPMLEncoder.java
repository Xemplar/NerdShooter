/*
 * NerdShooter is a pseudo libray project for future Xemplar 2D Side Scroller Games.
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.xemplar.games.android.nerdshooter.utils;
import com.badlogic.gdx.utils.Array;

public final class XPMLEncoder {
    private static final String lineSep = "<line_sep>";

    public XPMLEncoder(){
        
    }

    public String encodeString(Array<XPMLItem> tags){
        String lines = "";

        for(int i = 0; i < tags.size; i++){
            XPMLItem current = tags.get(i);
            lines += ("\n<" + current.getTag() + " ");

            for(int j = 0; j < current.elementLength(); j++){
                lines += ("\n" + current.getElementName(j) + ":" + current.getElementValue(j).replace("\n", lineSep) + ";");
            }
            for(int k = 0; k < current.childrenLength(); k++){
                lines += "\n" + encodeString(current.getAll());
            }
            lines += ("\n/>");
        }
        lines = lines.trim();

        return lines;
    }
}
