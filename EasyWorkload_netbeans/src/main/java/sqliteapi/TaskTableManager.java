package sqliteapi;

import java.time.LocalDateTime;


/**
 * <h2> Class Description </h2>
 * <p> {@code TaskTableManager} is a class that
 * 
 * @since 1.0
 * @version 1.0
 * @author Ray Rafael Abenido
 * @implNote Most functions in this class have {@code protected} access modifier so that only
 * subclasses can use them. Other classes other than the subclasses are not expected to use
 * the functions here.
 *
 */
public class TaskTableManager extends TableManager {
	public TaskTableManager(String tableName, String connectionURL, String primaryKey) {
		super(tableName, connectionURL, primaryKey);
	}
		
	protected String generateINSERTQuery(String task_title, String description,
			String date_added, String deadline, String subject,
			boolean is_complete, boolean is_late, boolean is_subtask_of) {
		
		String s_is_complete = this.convertToSQLiteBoolean(is_complete);
		String s_is_late = this.convertToSQLiteBoolean(is_late);
		String s_subtask_of = this.convertToSQLiteBoolean(is_subtask_of);
		
		String query = 
				"INSERT INTO " + tableName + " (title, description, date_added, deadline, subject, is_complete, is_late, is_subtask_of) "
				+ "VALUES('"
                                + task_title + "'"
				+ ", '" + description + "'"
				+ ", '" + date_added + "'"
				+ ", '" + deadline + "'"
				+ ", '" + subject + "'"
				+ ", '" + s_is_complete + "'"
				+ ", '" + s_is_late + "'"
				+ ", '" + s_subtask_of + "'"
				+ ")";
                System.out.println(query);
                return query;
	}
	
	protected String generateUPDATEQuery(int taskID, String task_title, String description,
			String date_added, String deadline, String subject,
			boolean is_complete, boolean is_late, boolean is_subtask_of) {
		
		String s_is_complete = this.convertToSQLiteBoolean(is_complete);
		String s_is_late = this.convertToSQLiteBoolean(is_late);
		String s_subtask_of = this.convertToSQLiteBoolean(is_subtask_of);
		
		String query = 
				"UPDATE " + tableName + " SET "
				+ "title = '" + task_title + "', "
				+ "description = '" + description + "', "
				+ "date_added = '" + date_added + "', "
				+ "deadline = '" + deadline + "', "
				+ "subject = '" + subject + "', "
				+ "is_complete = '" + s_is_complete + "', "
				+ "is_late = '" + s_is_late + "', "
				+ "is_subtask_of = '" + s_subtask_of + "' "
				+ "WHERE taskID = " + taskID
				+ ";";
                System.out.println(query);
                return query;
	}
}
