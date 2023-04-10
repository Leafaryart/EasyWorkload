/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package main;
import sqliteapi.*;
import umts.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.Month;

/**
 *
 * @author Ray Rafael Abenido
 */
public class EasyWorkload {
    public static void main(String[] args) {
        String connectionURL = "C:\\Users\\Teddy IV\\OneDrive\\Desktop\\School Apps\\CSCI 42\\app_storage.db";
        SubjectTableManager stm = new SubjectTableManager("subject", connectionURL, "subjectID");
        TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
        
        ResultSet rs = ttm.getAllRecords();
        try {
            while (rs.next()) {
                
                
                
                
                
                
                
                
                
                
                
                
                
            } } catch (SQLException ex) {
            Logger.getLogger(EasyWorkload.class.getName()).log(Level.SEVERE, null, ex);
        }


    
    }
}
