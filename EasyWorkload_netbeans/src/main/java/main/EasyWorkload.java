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
        String connectionURL = "C:\\Users\\Ray Rafael Abenido\\Desktop\\Rafael\\College\\Ateneo\\Third Year - Second Semester\\CSCI 42 O\\project\\app_storage.db";
        TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");

        ResultSet rs = ttm.getAllRecords();
        try {
            while (rs.next()) {
                int taskID = rs.getInt("taskID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String date_added = rs.getString("date_added");
                String deadline = rs.getString("deadline");
                String subject = rs.getString("subject");
                String is_complete = rs.getString("is_complete");
                String is_late = rs.getString("is_late");
                String is_subtask_of = rs.getString("is_subtask_of");
                System.out.println(taskID + ", " + title + ", " + description +
                        ", " + date_added + ", " + deadline + ", "  + is_complete + ", " + is_late);
            } } catch (SQLException ex) {
            Logger.getLogger(EasyWorkload.class.getName()).log(Level.SEVERE, null, ex);
        }


    
    }
}
