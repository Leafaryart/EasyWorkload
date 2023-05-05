/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package umts;
import java.awt.Color;
import javax.swing.JTextArea;
import java.util.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import sqliteapi.*;
/**
 *
 * @author Ray Rafael Abenido
 * @author Geoffrey Co
 */
public class Calendar extends javax.swing.JFrame {

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        String connectionURL = "static\\app_storage.db";
        TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
        ResultSet rs = ttm.getAllRecords();
        int monthNumber = 3;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calendar(rs, 3).setVisible(true);
            }
        });
    }
    
    private int monthNumber;
    private ArrayList<JTextArea> days;
    private ArrayList<String> information;
    private ResultSet rs;

    public Calendar(ResultSet rs, int monthNumber) {
        days = new ArrayList<JTextArea>();
        this.rs = rs;
        this.monthNumber = monthNumber;
        initComponents();
        setMonthName();
        append_days();
        format_calendar();  
        appendTasks();
    }
    
    private void append_days() {
        // first row
        days.add(txtSunday1);
        days.add(txtMonday1);
        days.add(txtTuesday1);
        days.add(txtWednesday1);
        days.add(txtThursday1);
        days.add(txtFriday1);
        days.add(txtSaturday1);
        
        // second row
        days.add(txtSunday2);
        days.add(txtMonday2);
        days.add(txtTuesday2);
        days.add(txtWednesday2);
        days.add(txtThursday2);
        days.add(txtFriday2);
        days.add(txtSaturday2);
        
        // third row
        days.add(txtSunday3);
        days.add(txtMonday3);
        days.add(txtTuesday3);
        days.add(txtWednesday3);
        days.add(txtThursday3);
        days.add(txtFriday3);
        days.add(txtSaturday3);
        
        // fourth row
        days.add(txtSunday4);
        days.add(txtMonday4);
        days.add(txtTuesday4);
        days.add(txtWednesday4);
        days.add(txtThursday4);
        days.add(txtFriday4);
        days.add(txtSaturday4);
        
        // fifth row
        days.add(txtSunday5);
        days.add(txtMonday5);
        days.add(txtTuesday5);
        days.add(txtWednesday5);
        days.add(txtThursday5);
        days.add(txtFriday5);
        days.add(txtSaturday5);
    }
   /**
    * 
    * java.Time.*; - for showing the number of days in a month
    * 
    * if first day of the month is on a Tuesday
    *   get the position of that JTextArea representing Tuesday in the days list
    *   iterate through list and assign each JTextArea a day number until
    * you reach the max. number of days for that month
    * 
    */
    private void format_calendar() {
        for (JTextArea day: days) {
            day.setText("");
        }
        
        try {
            LocalDate today = LocalDate.now();
            LocalDate firstDayOfMonth = LocalDate.of(LocalDate.now().getYear(), monthNumber, 1);
            int firstDayIndex = firstDayOfMonth.getDayOfWeek().getValue() % 7;
            if (firstDayIndex == 0) {
                firstDayIndex = 7;
            }
            for (int i = 0; i < days.size(); i++) {
                if (i >= firstDayIndex && i < firstDayIndex + firstDayOfMonth.lengthOfMonth()) {
                    JTextArea dayTextArea = days.get(i);
                    int dayNumber = i - firstDayIndex + 1;
                    dayTextArea.setText(Integer.toString(dayNumber));
                    LocalDate parsedDate = LocalDate.of(LocalDate.now().getYear(), monthNumber, dayNumber);
                    if (parsedDate.isEqual(today)) {
                        dayTextArea.setBackground(Color.YELLOW);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while formatting");
            e.printStackTrace();
        }
    }
    
    private void appendTasks() {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            while(rs.next()) {
               String title = rs.getString("title");
               String deadline = rs.getString("deadline");
               String[] temp = {title, deadline};
               list.add(temp);
            }
            
        for (int i = 0; i < days.size(); i++) {
            JTextArea day = days.get(i);
            String dayText = day.getText().trim();
            if (!dayText.isEmpty()) {
                for (String[] task : list) {
                    String deadline = task[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy");
                    LocalDate deadlineDate = LocalDate.parse(task[1], formatter);
                    int dayNumber = Integer.parseInt(dayText);
                    if (deadlineDate.getDayOfMonth() == dayNumber) {
                        String title = task[0];
                        String taskInfo = day.getText() + "\n" + title;
                        day.setText(taskInfo);
                        day.setBackground(Color.getHSBColor(0.55f, 0.67f, 0.84f));
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    private void setMonthName() {
        Month month = Month.of(monthNumber);
        lblMonth.setText(month.getDisplayName(TextStyle.FULL, Locale.getDefault()));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtSunday1 = new javax.swing.JTextArea();
        txtSunday2 = new javax.swing.JTextArea();
        txtSunday3 = new javax.swing.JTextArea();
        txtSunday4 = new javax.swing.JTextArea();
        txtSunday5 = new javax.swing.JTextArea();
        txtMonday1 = new javax.swing.JTextArea();
        txtMonday2 = new javax.swing.JTextArea();
        txtMonday3 = new javax.swing.JTextArea();
        txtMonday4 = new javax.swing.JTextArea();
        txtMonday5 = new javax.swing.JTextArea();
        txtTuesday1 = new javax.swing.JTextArea();
        txtTuesday2 = new javax.swing.JTextArea();
        txtTuesday3 = new javax.swing.JTextArea();
        txtTuesday4 = new javax.swing.JTextArea();
        txtTuesday5 = new javax.swing.JTextArea();
        txtWednesday1 = new javax.swing.JTextArea();
        txtWednesday2 = new javax.swing.JTextArea();
        txtWednesday3 = new javax.swing.JTextArea();
        txtWednesday4 = new javax.swing.JTextArea();
        txtWednesday5 = new javax.swing.JTextArea();
        txtThursday1 = new javax.swing.JTextArea();
        txtThursday2 = new javax.swing.JTextArea();
        txtThursday3 = new javax.swing.JTextArea();
        txtThursday4 = new javax.swing.JTextArea();
        txtThursday5 = new javax.swing.JTextArea();
        txtFriday1 = new javax.swing.JTextArea();
        txtFriday2 = new javax.swing.JTextArea();
        txtFriday3 = new javax.swing.JTextArea();
        txtFriday4 = new javax.swing.JTextArea();
        txtFriday5 = new javax.swing.JTextArea();
        txtSaturday1 = new javax.swing.JTextArea();
        txtSaturday2 = new javax.swing.JTextArea();
        txtSaturday3 = new javax.swing.JTextArea();
        txtSaturday4 = new javax.swing.JTextArea();
        txtSaturday5 = new javax.swing.JTextArea();
        lblMonth = new javax.swing.JLabel();
        prevButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EasyWorkload - Calendar");
        setBackground(new java.awt.Color(0, 0, 0));
        setName("JCalendar"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 485));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 485));
        getContentPane().setLayout(null);

        txtSunday1.setEditable(false);
        txtSunday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday1.setColumns(20);
        txtSunday1.setForeground(new java.awt.Color(255, 255, 255));
        txtSunday1.setRows(5);
        txtSunday1.setText("sample\nsample\nsample\nsample");
        txtSunday1.setBorder(null);
        txtSunday1.setCaretColor(new java.awt.Color(255, 255, 255));
        txtSunday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday1.setOpaque(false);
        getContentPane().add(txtSunday1);
        txtSunday1.setBounds(80, 90, 90, 70);

        txtSunday2.setEditable(false);
        txtSunday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday2.setColumns(20);
        txtSunday2.setForeground(new java.awt.Color(255, 255, 255));
        txtSunday2.setRows(5);
        txtSunday2.setText("sample\nsample\nsample\nsample");
        txtSunday2.setBorder(null);
        txtSunday2.setOpaque(false);
        getContentPane().add(txtSunday2);
        txtSunday2.setBounds(80, 160, 90, 70);

        txtSunday3.setEditable(false);
        txtSunday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday3.setColumns(20);
        txtSunday3.setForeground(new java.awt.Color(255, 255, 255));
        txtSunday3.setRows(5);
        txtSunday3.setText("sample\nsample\nsample\nsample");
        txtSunday3.setBorder(null);
        txtSunday3.setOpaque(false);
        getContentPane().add(txtSunday3);
        txtSunday3.setBounds(80, 230, 90, 70);

        txtSunday4.setEditable(false);
        txtSunday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday4.setColumns(20);
        txtSunday4.setForeground(new java.awt.Color(255, 255, 255));
        txtSunday4.setRows(5);
        txtSunday4.setText("sample\nsample\nsample\nsample");
        txtSunday4.setBorder(null);
        txtSunday4.setOpaque(false);
        getContentPane().add(txtSunday4);
        txtSunday4.setBounds(80, 300, 90, 70);

        txtSunday5.setEditable(false);
        txtSunday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSunday5.setColumns(20);
        txtSunday5.setForeground(new java.awt.Color(255, 255, 255));
        txtSunday5.setRows(5);
        txtSunday5.setText("sample\nsample\nsample\nsample");
        txtSunday5.setBorder(null);
        txtSunday5.setOpaque(false);
        getContentPane().add(txtSunday5);
        txtSunday5.setBounds(80, 370, 90, 70);

        txtMonday1.setEditable(false);
        txtMonday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday1.setColumns(20);
        txtMonday1.setForeground(new java.awt.Color(255, 255, 255));
        txtMonday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday1.setRows(5);
        txtMonday1.setText("sample\nsample\nsample\nsample");
        txtMonday1.setBorder(null);
        txtMonday1.setOpaque(false);
        getContentPane().add(txtMonday1);
        txtMonday1.setBounds(170, 90, 90, 70);

        txtMonday2.setEditable(false);
        txtMonday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday2.setColumns(20);
        txtMonday2.setForeground(new java.awt.Color(255, 255, 255));
        txtMonday2.setRows(5);
        txtMonday2.setText("sample\nsample\nsample\nsample");
        txtMonday2.setBorder(null);
        txtMonday2.setOpaque(false);
        getContentPane().add(txtMonday2);
        txtMonday2.setBounds(170, 160, 90, 70);

        txtMonday3.setEditable(false);
        txtMonday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday3.setColumns(20);
        txtMonday3.setForeground(new java.awt.Color(255, 255, 255));
        txtMonday3.setRows(5);
        txtMonday3.setText("sample\nsample\nsample\nsample");
        txtMonday3.setBorder(null);
        txtMonday3.setOpaque(false);
        getContentPane().add(txtMonday3);
        txtMonday3.setBounds(170, 230, 90, 70);

        txtMonday4.setEditable(false);
        txtMonday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday4.setColumns(20);
        txtMonday4.setForeground(new java.awt.Color(255, 255, 255));
        txtMonday4.setRows(5);
        txtMonday4.setText("sample\nsample\nsample\nsample");
        txtMonday4.setBorder(null);
        txtMonday4.setOpaque(false);
        getContentPane().add(txtMonday4);
        txtMonday4.setBounds(170, 300, 90, 70);

        txtMonday5.setEditable(false);
        txtMonday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtMonday5.setColumns(20);
        txtMonday5.setForeground(new java.awt.Color(255, 255, 255));
        txtMonday5.setRows(5);
        txtMonday5.setText("sample\nsample\nsample\nsample");
        txtMonday5.setBorder(null);
        txtMonday5.setOpaque(false);
        getContentPane().add(txtMonday5);
        txtMonday5.setBounds(170, 370, 90, 70);

        txtTuesday1.setEditable(false);
        txtTuesday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtTuesday1.setColumns(20);
        txtTuesday1.setForeground(new java.awt.Color(255, 255, 255));
        txtTuesday1.setRows(5);
        txtTuesday1.setText("sample\nsample\nsample\nsample");
        txtTuesday1.setBorder(null);
        txtTuesday1.setOpaque(false);
        getContentPane().add(txtTuesday1);
        txtTuesday1.setBounds(260, 90, 90, 70);

        txtTuesday2.setEditable(false);
        txtTuesday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtTuesday2.setColumns(20);
        txtTuesday2.setForeground(new java.awt.Color(255, 255, 255));
        txtTuesday2.setRows(5);
        txtTuesday2.setText("sample\nsample\nsample\nsample");
        txtTuesday2.setBorder(null);
        txtTuesday2.setOpaque(false);
        getContentPane().add(txtTuesday2);
        txtTuesday2.setBounds(260, 160, 90, 70);

        txtTuesday3.setEditable(false);
        txtTuesday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtTuesday3.setColumns(20);
        txtTuesday3.setForeground(new java.awt.Color(255, 255, 255));
        txtTuesday3.setRows(5);
        txtTuesday3.setText("sample\nsample\nsample\nsample");
        txtTuesday3.setBorder(null);
        txtTuesday3.setOpaque(false);
        getContentPane().add(txtTuesday3);
        txtTuesday3.setBounds(260, 230, 90, 70);

        txtTuesday4.setEditable(false);
        txtTuesday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtTuesday4.setColumns(20);
        txtTuesday4.setForeground(new java.awt.Color(255, 255, 255));
        txtTuesday4.setRows(5);
        txtTuesday4.setText("sample\nsample\nsample\nsample");
        txtTuesday4.setBorder(null);
        txtTuesday4.setOpaque(false);
        getContentPane().add(txtTuesday4);
        txtTuesday4.setBounds(260, 300, 90, 70);

        txtTuesday5.setEditable(false);
        txtTuesday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtTuesday5.setColumns(20);
        txtTuesday5.setForeground(new java.awt.Color(255, 255, 255));
        txtTuesday5.setRows(5);
        txtTuesday5.setText("sample\nsample\nsample\nsample");
        txtTuesday5.setBorder(null);
        txtTuesday5.setOpaque(false);
        getContentPane().add(txtTuesday5);
        txtTuesday5.setBounds(260, 370, 90, 70);

        txtWednesday1.setEditable(false);
        txtWednesday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtWednesday1.setColumns(20);
        txtWednesday1.setForeground(new java.awt.Color(255, 255, 255));
        txtWednesday1.setRows(5);
        txtWednesday1.setText("sample\nsample\nsample\nsample");
        txtWednesday1.setBorder(null);
        txtWednesday1.setOpaque(false);
        getContentPane().add(txtWednesday1);
        txtWednesday1.setBounds(360, 90, 90, 70);

        txtWednesday2.setEditable(false);
        txtWednesday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtWednesday2.setColumns(20);
        txtWednesday2.setForeground(new java.awt.Color(255, 255, 255));
        txtWednesday2.setRows(5);
        txtWednesday2.setText("sample\nsample\nsample\nsample");
        txtWednesday2.setBorder(null);
        txtWednesday2.setOpaque(false);
        getContentPane().add(txtWednesday2);
        txtWednesday2.setBounds(360, 160, 90, 70);

        txtWednesday3.setEditable(false);
        txtWednesday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtWednesday3.setColumns(20);
        txtWednesday3.setForeground(new java.awt.Color(255, 255, 255));
        txtWednesday3.setRows(5);
        txtWednesday3.setText("sample\nsample\nsample\nsample");
        txtWednesday3.setBorder(null);
        txtWednesday3.setOpaque(false);
        getContentPane().add(txtWednesday3);
        txtWednesday3.setBounds(360, 230, 90, 70);

        txtWednesday4.setEditable(false);
        txtWednesday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtWednesday4.setColumns(20);
        txtWednesday4.setForeground(new java.awt.Color(255, 255, 255));
        txtWednesday4.setRows(5);
        txtWednesday4.setText("sample\nsample\nsample\nsample");
        txtWednesday4.setBorder(null);
        txtWednesday4.setOpaque(false);
        getContentPane().add(txtWednesday4);
        txtWednesday4.setBounds(360, 300, 90, 70);

        txtWednesday5.setEditable(false);
        txtWednesday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtWednesday5.setColumns(20);
        txtWednesday5.setForeground(new java.awt.Color(255, 255, 255));
        txtWednesday5.setRows(5);
        txtWednesday5.setText("sample\nsample\nsample\nsample");
        txtWednesday5.setBorder(null);
        txtWednesday5.setOpaque(false);
        getContentPane().add(txtWednesday5);
        txtWednesday5.setBounds(360, 370, 90, 70);

        txtThursday1.setEditable(false);
        txtThursday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtThursday1.setColumns(20);
        txtThursday1.setForeground(new java.awt.Color(255, 255, 255));
        txtThursday1.setRows(5);
        txtThursday1.setText("sample\nsample\nsample\nsample");
        txtThursday1.setBorder(null);
        txtThursday1.setOpaque(false);
        getContentPane().add(txtThursday1);
        txtThursday1.setBounds(450, 90, 90, 70);

        txtThursday2.setEditable(false);
        txtThursday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtThursday2.setColumns(20);
        txtThursday2.setForeground(new java.awt.Color(255, 255, 255));
        txtThursday2.setRows(5);
        txtThursday2.setText("sample\nsample\nsample\nsample");
        txtThursday2.setBorder(null);
        txtThursday2.setOpaque(false);
        getContentPane().add(txtThursday2);
        txtThursday2.setBounds(450, 160, 90, 70);

        txtThursday3.setEditable(false);
        txtThursday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtThursday3.setColumns(20);
        txtThursday3.setForeground(new java.awt.Color(255, 255, 255));
        txtThursday3.setRows(5);
        txtThursday3.setText("sample\nsample\nsample\nsample");
        txtThursday3.setBorder(null);
        txtThursday3.setOpaque(false);
        getContentPane().add(txtThursday3);
        txtThursday3.setBounds(450, 230, 90, 70);

        txtThursday4.setEditable(false);
        txtThursday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtThursday4.setColumns(20);
        txtThursday4.setForeground(new java.awt.Color(255, 255, 255));
        txtThursday4.setRows(5);
        txtThursday4.setText("sample\nsample\nsample\nsample");
        txtThursday4.setBorder(null);
        txtThursday4.setOpaque(false);
        getContentPane().add(txtThursday4);
        txtThursday4.setBounds(450, 300, 90, 70);

        txtThursday5.setEditable(false);
        txtThursday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtThursday5.setColumns(20);
        txtThursday5.setForeground(new java.awt.Color(255, 255, 255));
        txtThursday5.setRows(5);
        txtThursday5.setText("sample\nsample\nsample\nsample");
        txtThursday5.setBorder(null);
        txtThursday5.setOpaque(false);
        getContentPane().add(txtThursday5);
        txtThursday5.setBounds(450, 370, 90, 70);

        txtFriday1.setEditable(false);
        txtFriday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday1.setColumns(20);
        txtFriday1.setForeground(new java.awt.Color(255, 255, 255));
        txtFriday1.setRows(5);
        txtFriday1.setText("sample\nsample\nsample\nsample");
        txtFriday1.setBorder(null);
        txtFriday1.setOpaque(false);
        getContentPane().add(txtFriday1);
        txtFriday1.setBounds(540, 90, 90, 70);

        txtFriday2.setEditable(false);
        txtFriday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday2.setColumns(20);
        txtFriday2.setForeground(new java.awt.Color(255, 255, 255));
        txtFriday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday2.setRows(5);
        txtFriday2.setText("sample\nsample\nsample\nsample");
        txtFriday2.setBorder(null);
        txtFriday2.setOpaque(false);
        getContentPane().add(txtFriday2);
        txtFriday2.setBounds(540, 160, 90, 70);

        txtFriday3.setEditable(false);
        txtFriday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday3.setColumns(20);
        txtFriday3.setForeground(new java.awt.Color(255, 255, 255));
        txtFriday3.setRows(5);
        txtFriday3.setText("sample\nsample\nsample\nsample");
        txtFriday3.setBorder(null);
        txtFriday3.setOpaque(false);
        getContentPane().add(txtFriday3);
        txtFriday3.setBounds(540, 230, 90, 70);

        txtFriday4.setEditable(false);
        txtFriday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday4.setColumns(20);
        txtFriday4.setForeground(new java.awt.Color(255, 255, 255));
        txtFriday4.setRows(5);
        txtFriday4.setText("sample\nsample\nsample\nsample");
        txtFriday4.setBorder(null);
        txtFriday4.setOpaque(false);
        getContentPane().add(txtFriday4);
        txtFriday4.setBounds(540, 300, 90, 70);

        txtFriday5.setEditable(false);
        txtFriday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtFriday5.setColumns(20);
        txtFriday5.setForeground(new java.awt.Color(255, 255, 255));
        txtFriday5.setRows(5);
        txtFriday5.setText("sample\nsample\nsample\nsample");
        txtFriday5.setBorder(null);
        txtFriday5.setOpaque(false);
        getContentPane().add(txtFriday5);
        txtFriday5.setBounds(540, 370, 90, 70);

        txtSaturday1.setEditable(false);
        txtSaturday1.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday1.setColumns(20);
        txtSaturday1.setForeground(new java.awt.Color(255, 255, 255));
        txtSaturday1.setRows(5);
        txtSaturday1.setText("sample\nsample\nsample\nsample");
        txtSaturday1.setBorder(null);
        txtSaturday1.setOpaque(false);
        getContentPane().add(txtSaturday1);
        txtSaturday1.setBounds(630, 90, 90, 70);

        txtSaturday2.setEditable(false);
        txtSaturday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday2.setColumns(20);
        txtSaturday2.setForeground(new java.awt.Color(255, 255, 255));
        txtSaturday2.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday2.setRows(5);
        txtSaturday2.setText("sample\nsample\nsample\nsample");
        txtSaturday2.setBorder(null);
        txtSaturday2.setOpaque(false);
        getContentPane().add(txtSaturday2);
        txtSaturday2.setBounds(630, 160, 90, 70);

        txtSaturday3.setEditable(false);
        txtSaturday3.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday3.setColumns(20);
        txtSaturday3.setForeground(new java.awt.Color(255, 255, 255));
        txtSaturday3.setRows(5);
        txtSaturday3.setText("sample\nsample\nsample\nsample");
        txtSaturday3.setBorder(null);
        txtSaturday3.setOpaque(false);
        getContentPane().add(txtSaturday3);
        txtSaturday3.setBounds(630, 230, 90, 70);

        txtSaturday4.setEditable(false);
        txtSaturday4.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday4.setColumns(20);
        txtSaturday4.setForeground(new java.awt.Color(255, 255, 255));
        txtSaturday4.setRows(5);
        txtSaturday4.setText("sample\nsample\nsample\nsample");
        txtSaturday4.setBorder(null);
        txtSaturday4.setOpaque(false);
        getContentPane().add(txtSaturday4);
        txtSaturday4.setBounds(630, 300, 90, 70);

        txtSaturday5.setEditable(false);
        txtSaturday5.setBackground(new java.awt.Color(0, 0, 0,0));
        txtSaturday5.setColumns(20);
        txtSaturday5.setForeground(new java.awt.Color(255, 255, 255));
        txtSaturday5.setRows(5);
        txtSaturday5.setText("sample\nsample\nsample\nsample");
        txtSaturday5.setBorder(null);
        txtSaturday5.setOpaque(false);
        getContentPane().add(txtSaturday5);
        txtSaturday5.setBounds(630, 370, 90, 70);

        lblMonth.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblMonth.setForeground(new java.awt.Color(255, 255, 255));
        lblMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonth.setText("month");
        lblMonth.setToolTipText("");
        lblMonth.setName("lblMonth"); // NOI18N
        getContentPane().add(lblMonth);
        lblMonth.setBounds(200, 10, 390, 40);
        lblMonth.getAccessibleContext().setAccessibleName("");

        prevButton.setBackground(new java.awt.Color(255, 255, 255));
        prevButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        prevButton.setForeground(new java.awt.Color(0, 0, 0));
        prevButton.setText("<<");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        getContentPane().add(prevButton);
        prevButton.setBounds(20, 240, 50, 50);

        nextButton.setBackground(new java.awt.Color(255, 255, 255));
        nextButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nextButton.setForeground(new java.awt.Color(0, 0, 0));
        nextButton.setText(">>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nextButton);
        nextButton.setBounds(730, 240, 50, 50);

        lblBackground.setIcon(new javax.swing.ImageIcon("static\\Calendar_Frame_downsized.png"));
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 820, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        monthNumber--;
        if (monthNumber < 1) {
            monthNumber = 12;
        }
        Calendar newCalendar = new Calendar(rs, monthNumber);
        newCalendar.appendTasks();
        newCalendar.setVisible(true);    
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        monthNumber++;
        if (monthNumber > 12) {
            monthNumber = 1;
        }
        Calendar newCalendar = new Calendar(rs, monthNumber);
        newCalendar.appendTasks();
        newCalendar.setVisible(true);    
    }//GEN-LAST:event_nextButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JTextArea txtFriday1;
    private javax.swing.JTextArea txtFriday2;
    private javax.swing.JTextArea txtFriday3;
    private javax.swing.JTextArea txtFriday4;
    private javax.swing.JTextArea txtFriday5;
    private javax.swing.JTextArea txtMonday1;
    private javax.swing.JTextArea txtMonday2;
    private javax.swing.JTextArea txtMonday3;
    private javax.swing.JTextArea txtMonday4;
    private javax.swing.JTextArea txtMonday5;
    private javax.swing.JTextArea txtSaturday1;
    private javax.swing.JTextArea txtSaturday2;
    private javax.swing.JTextArea txtSaturday3;
    private javax.swing.JTextArea txtSaturday4;
    private javax.swing.JTextArea txtSaturday5;
    private javax.swing.JTextArea txtSunday1;
    private javax.swing.JTextArea txtSunday2;
    private javax.swing.JTextArea txtSunday3;
    private javax.swing.JTextArea txtSunday4;
    private javax.swing.JTextArea txtSunday5;
    private javax.swing.JTextArea txtThursday1;
    private javax.swing.JTextArea txtThursday2;
    private javax.swing.JTextArea txtThursday3;
    private javax.swing.JTextArea txtThursday4;
    private javax.swing.JTextArea txtThursday5;
    private javax.swing.JTextArea txtTuesday1;
    private javax.swing.JTextArea txtTuesday2;
    private javax.swing.JTextArea txtTuesday3;
    private javax.swing.JTextArea txtTuesday4;
    private javax.swing.JTextArea txtTuesday5;
    private javax.swing.JTextArea txtWednesday1;
    private javax.swing.JTextArea txtWednesday2;
    private javax.swing.JTextArea txtWednesday3;
    private javax.swing.JTextArea txtWednesday4;
    private javax.swing.JTextArea txtWednesday5;
    // End of variables declaration//GEN-END:variables
    

}
