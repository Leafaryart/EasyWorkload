/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sqliteapi;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 * This class is mainly for testing the SQLiteAPI package if its working
 * Don't use this in the actual project
 * @author Ray Rafael Abenido
 */
public class DatabaseTest extends javax.swing.JFrame {

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
            java.util.logging.Logger.getLogger(DatabaseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabaseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabaseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabaseTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabaseTest().setVisible(true);
            }
        });
    }
    TaskTableManager ttm;
    
    public DatabaseTest() {
        String connectionURL = "static\\app_storage.db";
        ttm = new TaskTableManager("task_list", connectionURL, "taskID");
        initComponents();
        populateTable();
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtTitle = new javax.swing.JTextField();
        txtDateAdded = new javax.swing.JTextField();
        txtDeadline = new javax.swing.JTextField();
        txtisComplete = new javax.swing.JTextField();
        txtIsLate = new javax.swing.JTextField();
        btnGetRecord = new javax.swing.JButton();
        txtDescription = new javax.swing.JTextField();
        btnUpdateRecord = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnInsertRecord = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRecords = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setForeground(new java.awt.Color(60, 63, 65));
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(1000, 545));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 30, 20, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("IsLate");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 340, 101, 32);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Title:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 70, 30, 40);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Description:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 110, 101, 32);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Date Added:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 220, 70, 32);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Deadline:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 260, 70, 32);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("IsComplete");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 300, 101, 32);
        getContentPane().add(txtID);
        txtID.setBounds(120, 40, 70, 30);
        getContentPane().add(txtTitle);
        txtTitle.setBounds(120, 80, 71, 30);
        getContentPane().add(txtDateAdded);
        txtDateAdded.setBounds(120, 220, 130, 30);
        getContentPane().add(txtDeadline);
        txtDeadline.setBounds(120, 260, 130, 30);
        getContentPane().add(txtisComplete);
        txtisComplete.setBounds(120, 300, 64, 30);
        getContentPane().add(txtIsLate);
        txtIsLate.setBounds(120, 340, 64, 30);

        btnGetRecord.setBackground(new java.awt.Color(0, 0, 0));
        btnGetRecord.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGetRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnGetRecord.setText("Get Record");
        btnGetRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnGetRecord);
        btnGetRecord.setBounds(250, 460, 170, 40);
        getContentPane().add(txtDescription);
        txtDescription.setBounds(120, 120, 320, 90);

        btnUpdateRecord.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdateRecord.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateRecord.setText("Update Record");
        btnUpdateRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdateRecord);
        btnUpdateRecord.setBounds(40, 460, 170, 40);

        btnDelete.setBackground(new java.awt.Color(0, 0, 0));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Record");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(40, 410, 170, 40);

        btnInsertRecord.setBackground(new java.awt.Color(0, 0, 0));
        btnInsertRecord.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnInsertRecord.setForeground(new java.awt.Color(255, 255, 255));
        btnInsertRecord.setText("Insert Record");
        btnInsertRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsertRecord);
        btnInsertRecord.setBounds(250, 410, 170, 40);

        tblRecords.setBackground(new java.awt.Color(255, 255, 255));
        tblRecords.setForeground(new java.awt.Color(0, 0, 0));
        tblRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblRecords.setName("tblRecords"); // NOI18N
        jScrollPane1.setViewportView(tblRecords);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(480, 40, 480, 420);

        jLabel7.setIcon(new javax.swing.ImageIcon("static\\task_editor.jpg"));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(-10, 10, 1000, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetRecordActionPerformed
        try {
            ResultSet rs = ttm.getRecord(Integer.parseInt(txtID.getText()));
            while(rs.next()) {
                txtID.setText(rs.getString("taskID"));
                txtTitle.setText(rs.getString("title"));
                txtDescription.setText(rs.getString("description"));
                txtDateAdded.setText(rs.getString("date_added"));
                txtDeadline.setText(rs.getString("deadline"));
                txtisComplete.setText(rs.getString("is_complete"));
                txtIsLate.setText(rs.getString("is_late"));
                System.out.println("Got record");
            }
        } catch (Exception e){
            System.out.println("Failed to get record. See stack trace.");
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not get specified record. Make sure" +
                    " ID box has a number and task exists.", "Process Failed", JOptionPane.INFORMATION_MESSAGE);

        }   
    }//GEN-LAST:event_btnGetRecordActionPerformed

    private void btnUpdateRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordActionPerformed
        try {
            int taskID = Integer.parseInt(txtID.getText());
            String task_title = txtTitle.getText();
            String description = txtDescription.getText();
            String date_added = txtDateAdded.getText();
            String deadline = txtDeadline.getText();
            String subject = null;
            Boolean is_complete = Boolean.parseBoolean(txtisComplete.getText());
            Boolean is_late = Boolean.parseBoolean(txtIsLate.getText());
            Boolean is_subtask_of = false;
            String query = ttm.generateUPDATEQuery(taskID, task_title, description, date_added, deadline, subject, is_complete, is_late, is_subtask_of);
            ttm.modifyRecord(query);
            System.out.println("updated");
            populateTable();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Could not update specified record. Make sure" +
                    " record exists and input correct.", "Process Failed", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnUpdateRecordActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            // Delete the record
            int id = Integer.parseInt(txtID.getText().trim());
            ttm.deleteRecord(id);
            populateTable();
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Could not delete specified record. Make sure" +
                    " task exists", "Process Failed", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Update the taskIDs of the following records
        /*ArrayList<Task> tasks = ttm.getAllRecords();
         for (Task task : tasks) {
         if (task.getTaskID() > id) {
            int newTaskID = task.getTaskID() - 1;
            ttm.updateTaskID(task.getTaskID(), newTaskID);
        }
    }*/
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertRecordActionPerformed
        try {
            String task_title = txtTitle.getText();
            String description = txtDescription.getText();
            String date_added = txtDateAdded.getText();
            String deadline = txtDeadline.getText();
            String subject = null;
            Boolean is_complete = Boolean.parseBoolean(txtisComplete.getText());
            Boolean is_late = Boolean.parseBoolean(txtIsLate.getText());
            Boolean is_subtask_of = false;
            if(task_title.isEmpty() || description.isEmpty() || date_added.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Could not insert new record. Make sure" +
                    " all input boxes are filled correctly.", "Process Failed", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String query = ttm.generateINSERTQuery(task_title, description, date_added, deadline, subject, is_complete, is_late, is_subtask_of);
                ttm.modifyRecord(query);
                populateTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Could not insert new record. Make sure" +
                    " your input is correct", "Process Failed", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnInsertRecordActionPerformed

    private void populateTable() {
        ResultSet rs = ttm.getAllRecords();
        DefaultTableModel model = new DefaultTableModel();
        // Add columns to the model
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnLabel(i));
            }

        // Add rows to the model
        while (rs.next()) {
            Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
            model.addRow(row);
            }

            tblRecords.setModel(model);
        } catch (Exception e) {
            System.out.println("Could not populate table. See stack trace");
            e.printStackTrace();;
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGetRecord;
    private javax.swing.JButton btnInsertRecord;
    private javax.swing.JButton btnUpdateRecord;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRecords;
    private javax.swing.JTextField txtDateAdded;
    private javax.swing.JTextField txtDeadline;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIsLate;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtisComplete;
    // End of variables declaration//GEN-END:variables
}

