package umts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.EasyWorkload;
import sqliteapi.*;

public class RectangleCanvas extends JComponent{
    
    private int width;
    private int height;
    private Color bg;
    private MyRectangle rec;
    private MyText task_name;
    private MyText notes;
    private MyText notes2;
    private Timer timer;
    private String connectionURL = "static\\app_storage.db";
    private TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
    
    private int taskID;
    private String title;
    private String description;
    private String date_added;
    private String deadline;
    private String subject;
    private String is_complete;
    private String is_late;
    private String is_subtask_of;

    
    JFrame frame= new JFrame();
    Container content=frame.getContentPane();
    
    int wid =1920, hei=1080;
    
    private Color color = Color.getHSBColor(0.55f, 0.67f, 0.84f);
    
    //For the Task Frame
    ImageIcon task_icon = new ImageIcon("static\\Tasks_Frame.png");
    JLabel task_pic = new JLabel();
    //For the Calendar Frame
    ImageIcon cal_icon = new ImageIcon("static\\Calendar_Frame.png");
    JLabel cal_pic = new JLabel();
    JLabel header;
    
    private MyRectangle r1;
    private MyRectangle r2;
    public RectangleCanvas(int w, int h, Color bgc){
        width=w;
        height=h;
        bg=bgc;
        setPreferredSize(new Dimension(width,height)); 
        rec=new MyRectangle(0,0,1920,1080,100,100);
        
        ResultSet rs = ttm.getRecord(1);
        try {
            while (rs.next()) {
                taskID = rs.getInt("taskID");
                title = rs.getString("title");
                description = rs.getString("description");
                date_added = rs.getString("date_added");
                deadline = rs.getString("deadline");
                subject = rs.getString("subject");
                is_complete = rs.getString("is_complete");
                is_late = rs.getString("is_late");
                is_subtask_of = rs.getString("is_subtask_of");
                System.out.println(taskID + ", " + title + ", " + description +
                        ", " + date_added + ", " + deadline + ", " + subject + ", " + is_complete + ", " + is_late
                        + ", " + is_subtask_of);
            } } catch (SQLException ex) {
            Logger.getLogger(EasyWorkload.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(taskID + ", " + title + ", " + description +
                        ", " + date_added + ", " + deadline + ", " + subject + ", " + is_complete + ", " + is_late
                        + ", " + is_subtask_of);
        //For the Calendar
        cal_pic.setIcon(cal_icon);
        Dimension size = cal_pic.getPreferredSize();
        cal_pic.setBounds(0,-130,wid,hei);
        frame.add(cal_pic, BorderLayout.CENTER); 
        //For the Header, TEDDDY LOOK HERE
        task_name=new MyText(100,-200,-480,Color.WHITE, title);
        notes=new MyText(60,180,-340,Color.WHITE, "Priority");
        notes2=new MyText(60,180,-240,Color.WHITE, "Due Date: " + deadline);
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
        task_pic.setIcon(task_icon);
        task_pic.setBounds(0,-130,wid,hei);
        frame.add(task_pic, BorderLayout.CENTER);
        
        //ignore this rectangles this are only testing if animations work
        r1=new MyRectangle(600,100,100,120,3,-6);
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
    public void setUpKL(){
        KeyListener kl = new KeyListener(){
            
            public void keyTyped(KeyEvent ke){
            }
            public void keyPressed(KeyEvent ke){
                int keyCode = ke.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_UP:
                        cal_pic.setVisible(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        cal_pic.setVisible(true);
                        break;    
                }
            }
            public void keyReleased(KeyEvent ke){
                int keyCode = ke.getKeyCode();
                switch(keyCode){
                    case KeyEvent.VK_UP:
                        cal_pic.setVisible(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        cal_pic.setVisible(true);
                        break;    
                }
            }
        };
        content.addKeyListener(kl);
        content.setFocusable(true);
        content.requestFocusInWindow();
    }
   public JFrame getFrame(){
        return frame;
    }
    public JLabel getLabel(){
        return task_pic;
    }
    public Container getContentPane(){
        return content;
    }
}