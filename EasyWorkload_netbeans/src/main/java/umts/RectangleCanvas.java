package umts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

public class RectangleCanvas extends JComponent{
    
    private int width;
    private int height;
    private Color bg;
    private MyRectangle rec;
    private MyText task_name;
    private MyText notes;
    private MyText notes2;
    private Timer timer;
    
    JFrame frame= new JFrame();
    Container content=frame.getContentPane();
    
    int wid =1920, hei=1080;
    
    private Color color = Color.getHSBColor(0.55f, 0.67f, 0.84f);
    
    ImageIcon icon = new ImageIcon("Frame (2).png");
    JLabel pic = new JLabel();
    JLabel header;
    
    private MyRectangle r1;
    private MyRectangle r2;
    public RectangleCanvas(int w, int h, Color bgc){
        width=w;
        height=h;
        bg=bgc;
        setPreferredSize(new Dimension(width,height)); 
        rec=new MyRectangle(0,0,1920,1080,100,100);
        
        //For the Header, TEDDDY LOOK HERE
        task_name=new MyText(100,-200,-480,Color.WHITE, "Task Title");
        notes=new MyText(60,180,-340,Color.WHITE, "Priority");
        notes2=new MyText(60,180,-240,Color.WHITE, "Due Date");
        //For the Header, TEDDDY LOOK HERE
        
        JLabel header = new JLabel(task_name.getString(), SwingConstants.CENTER);
        header.setForeground(task_name.getColor());
        header.setBounds(task_name.getX(),task_name.getY(),wid,hei);
        header.setFont(new Font("Impact", Font.PLAIN, task_name.getSize()));
        frame.add(header);
        
        //For the Attributes Notes of the Task
        JLabel attributes = new JLabel(notes.getString(), SwingConstants.LEFT);
        attributes.setForeground(notes.getColor());
        attributes.setBounds(notes.getX(),notes.getY(),wid,hei);
        attributes.setFont(new Font("Impact", Font.PLAIN, notes.getSize()));
        frame.add(attributes);
        //For the Attributes2 Notes of the Task
        JLabel attributes2 = new JLabel(notes2.getString(), SwingConstants.LEFT);
        attributes2.setForeground(notes2.getColor());
        attributes2.setBounds(notes2.getX(),notes2.getY(),wid,hei);
        attributes2.setFont(new Font("Impact", Font.PLAIN, notes2.getSize()));
        frame.add(attributes2);
        
        
        //For importing the image
        pic.setIcon(icon);
        Dimension size = pic.getPreferredSize();
        pic.setBounds(0,-130,wid,hei);
        frame.add(pic, BorderLayout.CENTER);
        
        //ignore these 2 rectangles they are only testing if animations work
        //r1=new MyRectangle(600,100,100,120,3,-6);
        //r2=new MyRectangle(200,400,140,130,-4,4);
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
                
                task_name.setString("Left");
                frame.add(header);
                rec.Vroom();
                
            }
    };
    
    public void setUpTimer(){
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                
                task_name.setString("PLS WORK");
                
                
                repaint();
            }
            
        };
        timer=new Timer(20,al);
        timer.start();
    }
    
    
    
    
    
    
    
    
    
    
    public JFrame getFrame(){
        return frame;
    }
    public JLabel getLabel(){
        return pic;
    }
    public Container getContentPane(){
        return content;
    }
    
    public void setUpKL(){
        KeyListener kl = new KeyListener(){
            public void keyTyped(KeyEvent ke){
            }
            public void keyPressed(KeyEvent ke){
                int keyCode = ke.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_LEFT:
                        r1.Vroom();
                
                        r2.Vroom();   
                        break;
                    case KeyEvent.VK_RIGHT:
                        task_name.setString("Right");
                        break;
                }
            }
            public void keyReleased(KeyEvent ke){
                int keyCode = ke.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_LEFT:
                        r1.Vroom();
                
                        r2.Vroom(); 
                        
                        break;
                    case KeyEvent.VK_RIGHT:
                        task_name.setString("Let go");
                        break;
                }
            }
            
        };
        content.addKeyListener(kl);
        content.setFocusable(true);
        content.requestFocusInWindow();
    }
    
}