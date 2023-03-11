package com.mycompany.umts;
import java.awt.*;
public class MyRectangle {
    private int x;
    private int y;
    private int wid;
    private int hei;
    private int hs;
    private int vs;
    
    public MyRectangle(int xstart, int ystart,int xend, int yend, int hsped, int vsped){
        this.x =xstart;
        this.y =ystart;
        this.wid =xend;
        this.hei =yend;
        this.hs=hsped;
        this.vs =vsped;
    }
    public void Teleport(int x1, int y1){
        x=x1;
        y=y1;
    }
    public void Vroom(){
        x=x+hs;
        y=y+vs;
    }
    public void NewDirection(int newxs, int newys){
        hs=newxs;
        vs=newys;
    }
    public void RL(){
        hs=hs*-1;
    }
    public void UD(){
        vs=vs*-1;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getWidth(){
        return wid;
    }
    public double getHeight(){
        return hei;
    }
    public void draw(Graphics g){
        g.fillRect(x, y, wid, hei);
    }
    
    
}
