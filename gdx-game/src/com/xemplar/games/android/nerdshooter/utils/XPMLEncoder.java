package com.xemplar.games.android.nerdshooter.utils;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.*;

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
