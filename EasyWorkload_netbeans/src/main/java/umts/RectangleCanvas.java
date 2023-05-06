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
import java.io.IOException;
import DailyNotifs.Notifications;
import umts.Calendar;
import sqliteapi.DatabaseTest;
import umts.MyText;
import java.util.ArrayList;


public class RectangleCanvas extends JComponent{
    
    private int width;
    private int height;
    private Color bg;
    private MyRectangle rec;
    
    private int taskID;
    private String title;
    private String description;
    private String date_added;
    private String deadline;
    private String subject;
    private String is_complete;
    private String is_late;
    private String is_subtask_of;
    
    private String due;
    
    
    public MyText task_name= new MyText(100,0,-310,Color.WHITE, title);
    private MyText field1=new MyText(60,180,-190,Color.WHITE, "Deadline          : "+ deadline);
    private MyText field2=new MyText(60,180,-110,Color.WHITE, "Date Added        : " + date_added);
    private MyText field3=new MyText(60,180,-30,Color.WHITE, "Subject           : " + subject);
    private MyText field4=new MyText(60,180,50,Color.WHITE,  "Completion Status : " + is_complete);
    private MyText field5=new MyText(60,180,130,Color.WHITE,   "Late Status       : " + is_late);
    private MyText field6=new MyText(60,180,210,Color.WHITE,  "Subtask           : " + is_subtask_of);
    private Timer timer;
    private String connectionURL = "static\\app_storage.db";
    private TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
    private int id =3;
    private ResultSet rs = ttm.getRecord(id);
    private JLabel header = new JLabel("AAAA", SwingConstants.CENTER);
    private JLabel attributes1 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes2 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes3 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes4 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes5 = new JLabel("AAAA", SwingConstants.LEFT);
    private JLabel attributes6 = new JLabel("AAAA", SwingConstants.LEFT);
    
    private boolean view=true;
    private ArrayList<Integer> taskIDList = new ArrayList<Integer>();

    
    JFrame frame= new JFrame();
    Container content=frame.getContentPane();
    
    int wid =1280, hei=720;
    //int wid wid 1920, hei=1080;
    int p =1920/wid;
    private Color color = Color.getHSBColor(0.55f, 0.67f, 0.84f);
    
    //For the Task Frame
    ImageIcon task_icon = new ImageIcon("static\\TaskFrame.png");
    JLabel task_pic = new JLabel();
    //For the Calendar Frame
    ImageIcon cal_icon = new ImageIcon("static\\cat.jpg");
    JLabel cal_pic = new JLabel();
    
    int previousID=0;
    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    // also ray: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    
    private MyRectangle r1;
    private MyRectangle r2;
    public RectangleCanvas(int w, int h, Color bgc){
        width=w;
        height=h;
        bg=bgc;
        setPreferredSize(new Dimension(width,height)); 
        rec=new MyRectangle(0,0,wid,hei,100,100);
        
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
                }
            } catch (SQLException ex) {
            Logger.getLogger(EasyWorkload.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println(taskID + ", " + title + ", " + description +
        //                ", " + date_added + ", " + deadline + ", " + subject + ", " + is_complete + ", " + is_late
        //                + ", " + is_subtask_of);
        
        populateTaskIDList();
        
        //For the Calendar
        cal_pic.setIcon(cal_icon);
        Dimension size = cal_pic.getPreferredSize();
        cal_pic.setBounds(0,0,wid,hei);
        //For Header
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
        
        // Create right button
        JButton rightButton = new JButton(">>");
        rightButton.setBounds(1190, 373, 66, 66);
        rightButton.setFont(new Font("Arial", Font.BOLD, 26));
        rightButton.setBackground(color);
        rightButton.setForeground(Color.WHITE);
        rightButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*if (previousID != taskID) {
                id += 1;
                previousID = taskID;
            } else {
                id = taskID;
                previousID =taskID;
            }*/
            //id+=1;
            incrementTaskDisplay();
        }
        });
        content.add(rightButton);

        // Create left button
        JButton leftButton = new JButton("<<");
        leftButton.setBounds(30, 373, 66, 66);
        leftButton.setFont(new Font("Arial", Font.BOLD, 26));
        leftButton.setBackground(color);
        leftButton.setForeground(Color.WHITE);
        leftButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            /*if (id > 1) {
                id -= 1;
                taskID = id;
                }*/
            decrementTaskDisplay();
            }
        });
        content.add(leftButton);
        // Create delete button
        JButton deleteButton = new JButton("delete");
        deleteButton.setBounds(128, 655, 80, 56);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 10));
        deleteButton.setBackground(new Color(54,57,64));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ttm.deleteRecord(id);
            }
        });
        content.add(deleteButton);
        
        // Create edit button
        JButton editButton = new JButton("edit");
        editButton.setBounds(317, 655, 80, 56);
        editButton.setFont(new Font("Arial", Font.BOLD, 10));
        editButton.setBackground(new Color(54,57,64));
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DatabaseTest dt = new DatabaseTest();
            dt.main(new String[0]); // Call the main method of the Database class
            }
        });
        content.add(editButton);
    
        // Create notif button
        JButton notifButton = new JButton("notify");
        notifButton.setBounds(883, 655, 80, 56);
        notifButton.setFont(new Font("Arial", Font.BOLD, 10));
        notifButton.setBackground(new Color(54,57,64));
        notifButton.setForeground(Color.WHITE);
        notifButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Notifications notifications = new Notifications(); // Create an instance of the Notifications class
            Notifications.main(new String[0]); // Call the main method of the Notifications class
            }
        });
        content.add(notifButton);
        
        // Create cal button
        JButton calButton = new JButton("calendar");
        calButton.setBounds(1072, 655, 80, 56);
        calButton.setFont(new Font("Arial", Font.BOLD, 10));
        calButton.setBackground(new Color(54,57,64));
        calButton.setForeground(Color.WHITE);
        calButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                String connectionURL = "static\\app_storage.db";
                TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
                ResultSet rs = ttm.getAllRecords();
                int monthNumber = 3;
                Calendar calendar = new Calendar(rs, monthNumber);
                calendar.setVisible(true);
            }
        });
        content.add(calButton);
        
        // Create viewA button
        JButton viewA = new JButton("+");
        viewA.setBounds(1227, 110, 53, 50);
        viewA.setFont(new Font("Arial", Font.BOLD, 30));
        viewA.setBackground(new Color(54,57,64));
        viewA.setForeground(Color.WHITE);
        viewA.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                view=true;
            }
        });
        content.add(viewA);
        
        // Create viewB button
        JButton viewB = new JButton("-");
        viewB.setBounds(1227, 160, 53, 50);
        viewB.setFont(new Font("Arial", Font.BOLD, 30));
        viewB.setBackground(new Color(54,57,64));
        viewB.setForeground(Color.WHITE);
        viewB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                view=false;
                
            }
        });
        System.out.println(task_name.getString()+"\n");
        content.add(viewB);

        //For importing the image
        task_pic.setIcon(task_icon);
        task_pic.setBounds(0,0,wid,hei);
        cal_pic.setVisible(false);
        //adding all the components
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
                        
                    } } catch (SQLException ex) {
                    Logger.getLogger(EasyWorkload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if(view==true){
                    header.setText(title);
                    attributes1.setText("Deadline                       : "+ deadline);
                    attributes2.setText("Date Added                  : " + date_added);
                    attributes3.setText("Subject                          : " + subject);
                    attributes4.setText("Completion Status : " + is_complete);
                    attributes5.setText("Late Status                   : " + is_late);
                    attributes6.setText("Description                 : " + description);
                }
                else{
                    header.setText(deadline);
                    attributes1.setText("- "+ title);
                    attributes2.setText("");
                    attributes3.setText("");
                    attributes4.setText("");
                    attributes5.setText("");
                    attributes6.setText("");
                    

                }
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
                        rs = ttm.getRecord(id);
                        break;    
                    case KeyEvent.VK_LEFT:
                        id -=1;
                        rs = ttm.getRecord(id);
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
    
    // Ray's functions
    private void populateTaskIDList() {
        rs = ttm.getAllRecords();
        try {
            while(rs.next()) {
                taskIDList.add(rs.getInt("taskID"));
            }
        } catch (Exception e) {
            System.out.println("getTaskID failed to run");
        }
        
        System.out.println("TaskIDList populated with the following details: ");
        for (int i: taskIDList){
            System.out.println(i);
        }
    }
    
    private void incrementTaskDisplay() {
        int old_index = taskIDList.indexOf(id);
        try {
            id = taskIDList.get(old_index + 1);
            ResultSet rs = ttm.getRecord(id);
            
            while (rs.next()) {
                title = rs.getString("title");
                description = rs.getString("description");
                date_added = rs.getString("date_added");
                deadline = rs.getString("deadline");
                subject = rs.getString("subject");
                is_complete = rs.getString("is_complete");
                is_late = rs.getString("is_late");
            }
            
            if (view==true){
               header.setText(title);
               attributes1.setText("Deadline                       : "+ deadline);
               attributes2.setText("Date Added                  : " + date_added);
               attributes3.setText("Subject                          : " + subject);
               attributes4.setText("Completion Status : " + is_complete);
               attributes5.setText("Late Status                   : " + is_late);
               attributes6.setText("Description                 : " + description);
        } 
        } catch(Exception e) {
            System.out.println("Error occured trying to update task display");
        }
        
    }
    
    private void decrementTaskDisplay() {
        int old_index = taskIDList.indexOf(id);
        try {
            id = taskIDList.get(old_index - 1);
            ResultSet rs = ttm.getRecord(id);
            
            while (rs.next()) {
                title = rs.getString("title");
                description = rs.getString("description");
                date_added = rs.getString("date_added");
                deadline = rs.getString("deadline");
                subject = rs.getString("subject");
                is_complete = rs.getString("is_complete");
                is_late = rs.getString("is_late");
            }
            if (view==true){
               header.setText(title);
               attributes1.setText("Deadline                       : "+ deadline);
               attributes2.setText("Date Added                  : " + date_added);
               attributes3.setText("Subject                          : " + subject);
               attributes4.setText("Completion Status : " + is_complete);
               attributes5.setText("Late Status                   : " + is_late);
               attributes6.setText("Description                 : " + description);
        }
        } catch(Exception e) {
            System.out.println("Error occured trying to update task display");
        }
    }
    
}