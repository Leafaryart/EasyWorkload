package sqliteapi;

public class SubjectTableManager extends TableManager {
	public SubjectTableManager(String tableName, String connectionURL, String primaryKey) {
		super(tableName, connectionURL, primaryKey);
	}
	
	private void generateINSERTQuery(int subjectID, String subject_title, String instructor_name) {
		String query = "INSERT INTO " + tableName + "(subjectID, subject_title, instructor_name)" + 
			" VALUES (" + subjectID + ",'" + subject_title + "', '" + instructor_name + "')";
	}
	
	private void generateUPDATEQuery(int subjectID, String subject_title, String instructor_name) {
		String query = 
				"UPDATE " + tableName + " SET "
				+ "subject_title = '" + subject_title + "', "
				+ "instructor_name = '" + instructor_name + "' "
				+ "WHERE subjectID = " + subjectID
				+ ";";
	}
}
