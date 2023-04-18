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
    private MyText field1;
    private MyText field2;
    private MyText field3;
    private MyText field4;
    private MyText field5;
    private MyText field6;
    private Timer timer;
    private String connectionURL = "static\\app_storage.db";
    private TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
    private int id =1;
    private ResultSet rs = ttm.getRecord(id);
    private JLabel header = new JLabel("AAAA", SwingConstants.CENTER);
    private JLabel attributes1 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes2 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes3 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes4 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes5 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes6 = new JLabel("AAAA", SwingConstants.LEFT);
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
    ImageIcon cal_icon = new ImageIcon("static\\cat.jpg");
    JLabel cal_pic = new JLabel();
    
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    
    private MyRectangle r1;
    private MyRectangle r2;
    public RectangleCanvas(int w, int h, Color bgc){
        width=w;
        height=h;
        bg=bgc;
        setPreferredSize(new Dimension(width,height)); 
        rec=new MyRectangle(0,0,1920,1080,100,100);
        
        rs = ttm.getRecord(id);
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

        //For the Header, TEDDDY LOOK HERE
        task_name=new MyText(100,-200,-480,Color.WHITE, title);
        field1=new MyText(60,180,-340,Color.WHITE, "Deadline          : "+ deadline);
        field2=new MyText(60,180,-240,Color.WHITE, "Date Added        : " + date_added);
        field3=new MyText(60,180,-140,Color.WHITE, "Subject           : " + subject);
        field4=new MyText(60,180,-40,Color.WHITE,  "Completion Status : " + is_complete);
        field5=new MyText(60,180,40,Color.WHITE,   "Late Status       : " + is_late);
        field6=new MyText(60,180,140,Color.WHITE,  "Subtask           : " + is_subtask_of);
        
        //For the Header, TEDDDY LOOK HERE
        header.setForeground(task_name.getColor());
        header.setBounds(task_name.getX(),task_name.getY(),wid,hei);
        header.setFont(new Font("Impact", Font.PLAIN, task_name.getSize()));
        //For the Attributes Notes of the Task
        attributes1.setForeground(field1.getColor());
        attributes1.setBounds(field1.getX(),field1.getY(),wid,hei);
        attributes1.setFont(new Font("Impact", Font.PLAIN, field1.getSize()));
        //For the Attributes2 Notes of the Task
        attributes2.setForeground(field2.getColor());
        attributes2.setBounds(field2.getX(),field2.getY(),wid,hei);
        attributes2.setFont(new Font("Impact", Font.PLAIN, field2.getSize()));  
        //For the Attributes2 Notes of the Task
        attributes3.setForeground(field3.getColor());
        attributes3.setBounds(field3.getX(),field3.getY(),wid,hei);
        attributes3.setFont(new Font("Impact", Font.PLAIN, field3.getSize()));
        //For the Attributes2 Notes of the Task
        attributes4.setForeground(field4.getColor());
        attributes4.setBounds(field4.getX(),field4.getY(),wid,hei);
        attributes4.setFont(new Font("Impact", Font.PLAIN, field4.getSize())); 
        //For the Attributes2 Notes of the Task
        attributes5.setForeground(field5.getColor());
        attributes5.setBounds(field5.getX(),field5.getY(),wid,hei);
        attributes5.setFont(new Font("Impact", Font.PLAIN, field5.getSize()));
        //For the Attributes2 Notes of the Task
        attributes6.setForeground(field6.getColor());
        attributes6.setBounds(field6.getX(),field6.getY(),wid,hei);
        attributes6.setFont(new Font("Impact", Font.PLAIN, field6.getSize()));  

        //For importing the image
        task_pic.setIcon(task_icon);
        task_pic.setBounds(0,-130,wid,hei);
        cal_pic.setVisible(false);
        content.add(cal_pic, BorderLayout.CENTER); 
        content.add(header);
        content.add(attributes1);
        content.add(attributes2);
        content.add(attributes3);
        content.add(attributes4);
        content.add(attributes5);
        content.add(attributes6);
        content.add(task_pic, BorderLayout.CENTER);
    }
    
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d= (Graphics2D) g;
        
        RenderingHints rh=new RenderingHints (RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        AffineTransform reset=g2d.getTransform();
        
        Rectangle2D.Double background =new Rectangle2D.Double(0,0,width,height);
        g2d.setColor(bg);
        g2d.fill(background);
        timer = new Timer(20,tp);
        timer.setRepeats(false);
        timer.start();
        g.setColor(color);
        rec.draw(g);   
        frame.repaint();
    }
    ActionListener tp = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {         
                task_name.setString("Left");
            }
    };
    
    public void setUpTimer(){
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                task_name.setString("PLS WORK");  
                frame.repaint();
                content.repaint();
                
                
                rs = ttm.getRecord(id);
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
                header.setText(title);
                attributes1.setText("Deadline                       : "+ deadline);
                attributes2.setText("Date Added                  : " + date_added);
                attributes3.setText("Subject                          : " + subject);
                attributes4.setText("Completion Status : " + is_complete);
                attributes5.setText("Late Status                   : " + is_late);
                attributes6.setText("Description                 : " + description);
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
                        id +=1;
                        rs = ttm.getRecord(id);
                        frame.repaint();
                        header.setText(title);
                        repaint();
                        cal_pic.setVisible(false);
                        

                        break;
                    case KeyEvent.VK_DOWN:
                        id -=1;
                        cal_pic.setVisible(true);
                        break;    
                        
                    case KeyEvent.VK_RIGHT:
                        break;    
                    case KeyEvent.VK_LEFT:
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
                    case KeyEvent.VK_RIGHT:
                        id +=1;
                        break;    
                    case KeyEvent.VK_LEFT:
                        id -=1;
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