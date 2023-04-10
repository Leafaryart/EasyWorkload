/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DailyNotifs;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sqliteapi.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Notifications {
    private void updateFinalLabel(JSlider hourSlider, JSlider minuteSlider, JLabel finalLabel) {
    finalLabel.setText("Selected Time: " + hourSlider.getValue() + " hour/s, " + minuteSlider.getValue() + " minute/s");
}
    
  public static void main(String[] args) {
     
    JFrame frame = new JFrame("Notifications");
    frame.setSize(660, 800);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    //create alarm panel
    
    
    JLabel label = new JLabel("Notifications");
    label.setAlignmentX(Component.CENTER_ALIGNMENT); // Set label alignment to center
    panel.add(label);
    
    JToggleButton toggleButton = new JToggleButton("Off");
    toggleButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Set toggle button alignment to center
    toggleButton.addActionListener(e -> {
      if (toggleButton.isSelected()) {
        toggleButton.setText("On");
        // Do something when toggle is turned on
      } else {
        toggleButton.setText("Off");
        // Do something when toggle is turned off
      }
    });
    panel.add(Box.createVerticalStrut(5));
    panel.add(toggleButton);
    
    //CREATE AN ALARM PANEL 
    JPanel alarmPanel = new JPanel(new BorderLayout());
    alarmPanel.setPreferredSize(new Dimension(600, 220));
    alarmPanel.setMaximumSize(new Dimension(600, 220));
// Make the panel a borderlayout type with  dimensions
    alarmPanel.setBackground(Color.LIGHT_GRAY);

//make the label and put it north
    JLabel alarmLabel = new JLabel("Alarm");
    alarmLabel.setHorizontalAlignment(JLabel.CENTER);
    alarmPanel.add(alarmLabel, BorderLayout.NORTH);

//make the hour slider
    JSlider hourSlider = new JSlider(JSlider.HORIZONTAL, 0, 23, 0);
    hourSlider.setMajorTickSpacing(4);
    hourSlider.setMinorTickSpacing(1);
    hourSlider.setPaintTicks(true);
    hourSlider.setPaintLabels(true);
//setMajorTickSpacing() and setMinorTickSpacing() methods set the spacing of the slider
//setPaintTicks() and setPaintLabels() methods make the ticks and labels visible.
    hourSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
 

  
    
    JLabel hourValueLabel = new JLabel(Integer.toString(hourSlider.getValue()) + " hour/s", JLabel.CENTER);
    hourValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    hourSlider.addChangeListener(e -> hourValueLabel.setText(Integer.toString(hourSlider.getValue()) + " hour/s"));

//make the minute slider
    JSlider minuteSlider = new JSlider(JSlider.HORIZONTAL, 0, 59, 0);
    minuteSlider.setMajorTickSpacing(10);
    minuteSlider.setMinorTickSpacing(1);
    minuteSlider.setPaintTicks(true);
    minuteSlider.setPaintLabels(true);
//setMajorTickSpacing() and setMinorTickSpacing() methods set the spacing of the slider
//setPaintTicks() and setPaintLabels() methods make the ticks and labels visible.
    minuteSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    

    JLabel minuteValueLabel = new JLabel(Integer.toString(minuteSlider.getValue()) + " minute/s", JLabel.CENTER);
    
    minuteValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    minuteSlider.addChangeListener(e -> minuteValueLabel.setText(Integer.toString(minuteSlider.getValue()) + " minute/s"));
 
   
    
 //make the final label to display the hours and minutes
// Create the final label
    JLabel finalLabel = new JLabel("Selected Time: " + hourSlider.getValue() + " hour, " + minuteSlider.getValue() + " minute");
// add change listeners to update the final value
    hourSlider.addChangeListener(e -> {
        hourValueLabel.setText(Integer.toString(hourSlider.getValue()) + " hour/s");
        finalLabel.setText("Selected Time: " + hourSlider.getValue() + " hour/s, " + minuteSlider.getValue() + " minute/s");
});

    minuteSlider.addChangeListener(e -> {
        minuteValueLabel.setText(Integer.toString(minuteSlider.getValue()) + " minute/s");
        finalLabel.setText("Selected Time: " + hourSlider.getValue() + " hour/s, " + minuteSlider.getValue() + " minute/s");
});


   //make a button for confirmation
JButton confirmButton = new JButton("Confirm");
confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);

confirmButton.addActionListener(e -> {
    // Do something when confirm button is clicked
    int hours = hourSlider.getValue();
    int minutes = minuteSlider.getValue();
    System.out.println("Alarm set for " + hours + " hour/s and " + minutes + " minute/s");
});
    
        confirmButton.addActionListener(e -> {
    JOptionPane.showMessageDialog(frame, "Date confirmed");
});
    
//add hour and minute sliders to a panel
    JPanel sliderPanel = new JPanel();
    
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
    sliderPanel.add(hourSlider);
    sliderPanel.add(hourValueLabel);
    
    sliderPanel.add(minuteSlider);
    sliderPanel.add(minuteValueLabel);
    sliderPanel.add(finalLabel);
        
//////////////////////////////////////////////
    alarmPanel.add(sliderPanel);
    alarmPanel.add(confirmButton, BorderLayout.SOUTH);
    
    // FOR LATER
    int hours = hourSlider.getValue();
    int minutes = minuteSlider.getValue();
    
    //
    

    panel.add(alarmPanel);
    panel.add(Box.createVerticalStrut(15));
 //////////////////////////////////////////////////////////////

    JPanel dailyPanel = new JPanel();
    dailyPanel.setPreferredSize(new Dimension(600, 200));
    dailyPanel.setMaximumSize(new Dimension(600, 200));
    dailyPanel.setLayout(new BorderLayout());
    dailyPanel.setBackground(new Color(25, 25, 112));
    
    JLabel headerLabel = new JLabel("Daily Notification");
    headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
    headerLabel.setForeground(Color.WHITE);
    headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //////////////////////////////////////////////////////////////////
    dailyPanel.add(Box.createVerticalStrut(20));
    dailyPanel.add(headerLabel, BorderLayout.NORTH);
    dailyPanel.add(Box.createVerticalStrut(20));
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setBackground(new Color(25, 25, 112));
    JRadioButton button1 = new JRadioButton("1 minute");
    button1.setActionCommand("60000");
    JRadioButton button2 = new JRadioButton("2 minutes");
    button2.setActionCommand("120000");
    JRadioButton button3 = new JRadioButton("3 minutes");
    button3.setActionCommand("180000");
    
    //////////////////////////////////////////////;
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(button1);
    buttonPanel.add(Box.createHorizontalStrut(10));
    buttonPanel.add(button2);
    buttonPanel.add(Box.createHorizontalStrut(10));
    buttonPanel.add(button3);
    buttonPanel.add(Box.createHorizontalGlue());
    dailyPanel.add(buttonPanel, BorderLayout.CENTER);
    
    
    //////////////////////////////////////////////////////;
    
    JButton dconfirmButton = new JButton("Confirm");
    dconfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    ButtonGroup bgroup = new ButtonGroup();
    bgroup.add(button1);
    bgroup.add(button2);
    bgroup.add(button3);

    
dconfirmButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
         button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        dconfirmButton.setEnabled(false);
        int delay = 0;
        if (button1.isSelected()) {
            delay = Integer.parseInt(button1.getActionCommand());
        } else if (button2.isSelected()) {
            delay = Integer.parseInt(button2.getActionCommand());
        } else if (button3.isSelected()) {
            delay = Integer.parseInt(button3.getActionCommand());
        } else {
            JOptionPane.showMessageDialog(dailyPanel, "Please select a duration.");
            button1.setEnabled(true);
            button2.setEnabled(true);
            button3.setEnabled(true);
            dconfirmButton.setEnabled(true);
            return;
        }
        
        JOptionPane.showMessageDialog(dailyPanel, "Reminder set for " + bgroup.getSelection().getActionCommand() + " second(s).");
        
        Timer timer = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dailyPanel, "Daily Reminder");
            }
        });
        timer.start();
        
        int finalDelay = delay; // declare a final variable to store the delay value
            new Thread(new Runnable() {
                 public void run() {
                   int timeLeft = finalDelay / 1000; // use the final variable inside the Runnable class
                   while (timeLeft > 0) {
                  headerLabel.setText("Daily Notification - " + timeLeft + " seconds remaining");
                 try {
                         Thread.sleep(1000);
                      } catch (InterruptedException ex) {
                 ex.printStackTrace();
            }
             timeLeft--;
        }
        headerLabel.setText("Daily Notification");
         button1.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                dconfirmButton.setEnabled(true);
    }
}).start();
    }
});
  ////////////////////////////////////
  
   button1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        button1.setEnabled(false);
        button2.setEnabled(true);
        button3.setEnabled(true);
        
    }
});

    button2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1.setEnabled(true);
        button2.setEnabled(false);
        button3.setEnabled(true);
    }
});

    button3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(false);
    }
});

    dconfirmButton.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }
});
    
  
  //////////////////////////////////
    
    JPanel dconfirmPanel = new JPanel();
    dconfirmPanel.setLayout(new BorderLayout());
    dconfirmPanel.setBackground(new Color(25, 25, 112));
    
    
    
    
    
    
    
    //////////////////////////////////////////////////////////
    dconfirmPanel.add(dconfirmButton, BorderLayout.SOUTH);
    dailyPanel.add(dconfirmPanel, BorderLayout.SOUTH);
    

    
    
    
    //////////////////////////////////////

   
    
   
    panel.add(dailyPanel);
    frame.getContentPane().add(panel);
     
    
    frame.pack();
    frame.setMinimumSize(new Dimension(660, 800));
    frame.setVisible(true);
  }
}