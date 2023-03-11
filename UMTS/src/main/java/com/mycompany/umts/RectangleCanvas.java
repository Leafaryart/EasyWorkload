package com.mycompany.umts;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;

public class RectangleCanvas extends JComponent{
    
    private int width;
    private int height;
    private Color bg;
    private MyRectangle r1;
    private MyRectangle r2;
    private MyRectangle rec;
    private Timer timer;
    
    private Color color = Color.getHSBColor(0.55f, 0.67f, 0.84f);
    public RectangleCanvas(int w, int h, Color bgc){
        width=w;
        height=h;
        bg=bgc;
        setPreferredSize(new Dimension(width,height)); 
        rec=new MyRectangle(0,0,1920,1080,0,0);
        
        
    }
    
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d= (Graphics2D) g;
        
        RenderingHints rh=new RenderingHints (RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        Rectangle2D.Double background =new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(bg);
        g2d.fill(background);
        
        timer = new Timer(20,tp);
        timer.setRepeats(false);
        timer.start();
        
        g.setColor(color);
        rec.draw(g);  
        
    }
    ActionListener tp = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                
                
                timer.stop();
            }
    };
    
    public void setUpTimer(){
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                
                            
                
                repaint();
            }
            
        };
        timer=new Timer(20,al);
        timer.start();
    }
    
    
}