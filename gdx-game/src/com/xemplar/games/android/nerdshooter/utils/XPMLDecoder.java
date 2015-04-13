package com.xemplar.games.android.nerdshooter.utils;
import com.badlogic.gdx.utils.*;

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
