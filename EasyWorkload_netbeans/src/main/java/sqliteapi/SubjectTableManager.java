package sqliteapi;


/**
 * <h2> Class Description </h2>
 * <p> {@code SubjectTableManager} is a class that
 * 
 * @since 1.0
 * @version 1.0
 * @author Ray Rafael Abenido
 * @implNote Most functions in this class have {@code protected} access modifier so that only
 * subclasses can use them. Other classes other than the subclasses are not expected to use
 * the functions here.
 * @deprecated
 *
 */

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
