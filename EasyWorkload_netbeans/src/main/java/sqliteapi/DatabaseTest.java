/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sqliteapi;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(720, 525));
        getContentPane().setLayout(null);

        jLabel1.setText("ID:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 50, 20, 40);

        jLabel2.setText("IsLate");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 360, 101, 32);

        jLabel3.setText("Title:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 90, 30, 40);

        jLabel4.setText("Description:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 130, 101, 32);

        jLabel5.setText("Date Added:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 240, 70, 32);

        jLabel6.setText("Deadline:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 280, 70, 32);

        jLabel8.setText("IsComplete");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 320, 101, 32);
        getContentPane().add(txtID);
        txtID.setBounds(90, 60, 70, 30);
        getContentPane().add(txtTitle);
        txtTitle.setBounds(90, 100, 71, 30);
        getContentPane().add(txtDateAdded);
        txtDateAdded.setBounds(130, 242, 130, 30);
        getContentPane().add(txtDeadline);
        txtDeadline.setBounds(130, 280, 130, 30);
        getContentPane().add(txtisComplete);
        txtisComplete.setBounds(130, 320, 64, 30);
        getContentPane().add(txtIsLate);
        txtIsLate.setBounds(130, 360, 64, 30);

        btnGetRecord.setText("Get Record");
        btnGetRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnGetRecord);
        btnGetRecord.setBounds(480, 120, 170, 40);
        getContentPane().add(txtDescription);
        txtDescription.setBounds(130, 140, 320, 90);

        btnUpdateRecord.setText("Update Record");
        btnUpdateRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdateRecord);
        btnUpdateRecord.setBounds(480, 60, 170, 40);

        btnDelete.setText("Delete Record");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(480, 180, 170, 50);

        btnInsertRecord.setText("Insert Record");
        btnInsertRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertRecordActionPerformed(evt);
            }
        });
        getContentPane().add(btnInsertRecord);
        btnInsertRecord.setBounds(480, 250, 170, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetRecordActionPerformed
        System.out.println(txtID.getText());
        ResultSet rs = ttm.getRecord(Integer.parseInt(txtID.getText()));
        try {
            while(rs.next()) {
                txtID.setText(rs.getString("taskID"));
                txtTitle.setText(rs.getString("title"));
                txtDescription.setText(rs.getString("description"));
                txtDateAdded.setText(rs.getString("date_added"));
                txtDeadline.setText(rs.getString("deadline"));
                txtisComplete.setText(rs.getString("is_complete"));
                txtIsLate.setText(rs.getString("is_late"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Got record");
    }//GEN-LAST:event_btnGetRecordActionPerformed

    private void btnUpdateRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordActionPerformed
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
    }//GEN-LAST:event_btnUpdateRecordActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int id = Integer.parseInt(txtID.getText().trim());

        // Delete the record
         ttm.deleteRecord(id);

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

        String task_title = txtTitle.getText();
        String description = txtDescription.getText();
        String date_added = txtDateAdded.getText();
        String deadline = txtDeadline.getText();
        String subject = null;
        Boolean is_complete = Boolean.parseBoolean(txtisComplete.getText());
        Boolean is_late = Boolean.parseBoolean(txtIsLate.getText());
        Boolean is_subtask_of = false;
        String query = ttm.generateINSERTQuery(task_title, description, date_added, deadline, subject, is_complete, is_late, is_subtask_of);
        ttm.modifyRecord(query);
    }//GEN-LAST:event_btnInsertRecordActionPerformed

   
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtDateAdded;
    private javax.swing.JTextField txtDeadline;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIsLate;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtisComplete;
    // End of variables declaration//GEN-END:variables
}

