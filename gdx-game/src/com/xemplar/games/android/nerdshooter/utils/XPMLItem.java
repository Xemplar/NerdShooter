package com.xemplar.games.android.nerdshooter.utils;
import com.badlogic.gdx.utils.*;

public class XPMLItem {
    private Array<XPMLItem> children = new Array<XPMLItem>();
    private Array<String> values = new Array<String>();
    private Array<String> ids = new Array<String>();
    private String tagID = "";

    public XPMLItem(String tag){
        this.tagID = tag;
    }

    public String getTag(){
        return tagID;
    }

    public void addElement(String elementID, String elementValue){
        for(int id = 0; id < ids.size; id++){
            if(ids.get(id).equals(elementID)){
                return;
            }
        }

        this.ids.add(elementID);
        this.values.add(elementValue);      
    }

    public void setElement(String elementID, String elementValue){
        for(int id = 0; id < ids.size; id++){
            if(ids.get(id).equals(elementID)){
                this.values.set(id, elementValue);
            }
        }
    }

    public String getElementName(int index){
        return ids.get(index);
    }

    public String getElementValue(int index){
        return values.get(index);
    }

    public String getElementValue(String id){
        for(int i = 0; i < ids.size; i++){
            if(ids.get(i).equals(id)){
                return values.get(i);
            }
        }

        return "";
    }

    public void removeElement(String element){
        for(int id = 0; id < ids.size; id++){
            if(ids.get(id).equals(element)){
                ids.removeIndex(id);
                values.removeIndex(id);
            }
        }
    }

    public void removeElement(int pos){
        this.ids.removeIndex(pos);
        this.values.removeIndex(pos);
    }

    public void addChild(XPMLItem child){

        this.children.add(child);
    }

    public void addAllChildren(Array<XPMLItem> children){
        for(XPMLItem item : children){
            this.children.add(item);
        }
    }

    public void removeChild(int pos){
        this.children.removeIndex(pos);
    }

    public void removeChild(String tag){
        for(int id = 0; id < children.size; id++){
            if(children.get(id).getTag().equals(tag)){
                children.removeIndex(id);
            }
        }
    }

    public void removeAllChildren(){
        for(int id = 0; id < children.size; id++){
            children.removeIndex(id);
        }
    }

    public XPMLItem getChild(int pos){
        return children.get(pos);
    }

    public XPMLItem getChild(String tag){
        for(XPMLItem item : children){
            if(item.getTag().equals(tag)){
                return item;
            }
        }

        return null;
    }

    public Array<XPMLItem> getAll(){
        return children;
    }

    public int elementLength(){
        return ids.size;
    }

    public int childrenLength(){
        return children.size;
    }

    public String toString() {
        String returnValue = "";

        returnValue += "Tag name: " + getTag();

        for(int i = 0; i < ids.size; i++){
            returnValue += "\nThe Element, " + ids.get(i) + ", value is: " + values.get(i);
        }

        return returnValue;
	}
}
