package com.mycompany.umts;

import java.awt.*;

/**
/**
 * {@code MyText}  class is where..
 * 
 * @author Marc Gerald Simeon
 * @since release
 * @version 1.0
 */
public class MyText {
    
    String string;
    int size;
    String font;
    Color color;
    int x;
    int y;
  
    public MyText(int si, int dx, int dy, Color co, String st){
            this.string=st;
            this.size=si;
            this.color=co;
            this.x = dx;
            this.y = dy;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getSize(){
        return size;
    }
    public String getString(){
        return string;
    }
    public Color getColor(){
        return color;
    }
    public void setString(String str){
        string =str;
    }
    
}
