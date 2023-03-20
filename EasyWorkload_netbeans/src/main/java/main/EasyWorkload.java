/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package main;
import sqliteapi.*;
import umts.*;


/**
 *
 * @author Ray Rafael Abenido
 */
public class EasyWorkload {
    public static void main(String[] args) {
        String connectionURL = "C:\\Users\\Ray Rafael Abenido\\Desktop\\Rafael\\College\\Ateneo\\Third Year - Second Semester\\CSCI 42 O\\project\\app_storage.db";
        SubjectTableManager stm = new SubjectTableManager("subject", connectionURL, "subjectID");
        TaskTableManager ttm = new TaskTableManager("task_list", connectionURL, "taskID");
    }
}
