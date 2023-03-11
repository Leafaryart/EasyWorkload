package sqliteapi;

public class SubjectTableManager extends TableManager {
	public static void main(String[] args) {
		String connectionURL = "C:\\Users\\Ray Rafael Abenido\\Desktop\\Rafael\\College\\Ateneo\\Third Year - Second Semester\\CSCI 42 O\\project\\app_storage.db";
		String tableName = "subject";
		String primaryKey = "subjectID";
		SubjectTableManager tm = new SubjectTableManager(tableName, connectionURL, primaryKey);
		tm.generateINSERTQuery(8, "hello", "test");
		tm.generateUPDATEQuery(8, "asdf", "qwert");
	}
	
	public SubjectTableManager(String tableName, String connectionURL, String primaryKey) {
		super(tableName, connectionURL, primaryKey);
	}
	
	private void generateINSERTQuery(int subjectID, String subject_title, String instructor_name) {
		String query = "INSERT INTO " + tableName + "(subjectID, subject_title, instructor_name)" + 
			" VALUES (" + subjectID + ",'" + subject_title + "', '" + instructor_name + "')";
		this.modifyRecord(query);
	}
	
	private void generateUPDATEQuery(int subjectID, String subject_title, String instructor_name) {
		String query = 
				"UPDATE " + tableName + " SET "
				+ "subject_title = '" + subject_title + "', "
				+ "instructor_name = '" + instructor_name + "' "
				+ "WHERE subjectID = " + subjectID
				+ ";";
		this.modifyRecord(query);
	}
}