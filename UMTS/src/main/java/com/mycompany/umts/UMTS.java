package com.mycompany.umts;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;



/**
/**
 * {@code UMTS} class is where.....
 * 
 * 
 * @author Marc Gerald Simeon
 * @since release
 * @version 1.0
 */
public class UMTS {
    public static void main(String[] args) {
    //For the Canvas
    int wid =1920, hei=1080;
    
    RectangleCanvas rc=new RectangleCanvas(wid,hei,Color.white);
    JFrame frame= rc.getFrame();
    Container cp=rc.getContentPane();
    frame.add(rc); 
    frame.setTitle("EasyWorkload");
    cp.add(rc,BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    rc.setUpTimer();
}
}
