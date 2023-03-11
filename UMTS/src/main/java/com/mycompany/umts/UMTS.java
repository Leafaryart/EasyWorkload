package com.mycompany.umts;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;

public class UMTS {
    public static void main(String[] args) {
    int wid =1920, hei=1080;
    JFrame frame= new JFrame();
    RectangleCanvas rc=new RectangleCanvas(wid,hei,Color.white);
    Container cp=frame.getContentPane();

    JLabel header = new JLabel("New Task", SwingConstants.CENTER);
    header.setForeground(Color.white);
    header.setBounds(-200,-480,wid,hei);
    header.setFont(new Font("Impact", Font.PLAIN, 100));
    frame.add(header);
    
    ImageIcon icon = new ImageIcon("Frame (2).png");
    JLabel label = new JLabel();
    label.setIcon(icon);
    Dimension size = label.getPreferredSize();
    label.setBounds(0,-130,wid,hei);
    cp.add(label, BorderLayout.CENTER);
    
    JLabel attributes = new JLabel("Title", SwingConstants.CENTER);
    attributes.setForeground(Color.white);
    attributes.setBounds(-680,-340,wid,hei);
    attributes.setFont(new Font("Impact", Font.PLAIN, 60));
    frame.add(attributes);
    
    frame.add(rc);    
    frame.setTitle("EasyWorkload");
    cp.add(rc,BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    
    rc.setUpTimer();
}
}
