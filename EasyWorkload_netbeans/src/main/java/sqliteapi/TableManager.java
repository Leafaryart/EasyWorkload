package sqliteapi;


import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h2> Class Description </h2>
 * <p> {@code TableManager} is a class that manages a table in a SQLite database.
 * It provides the four CRUD functions (create, retrieve, update, delete)
 * needed to interact with rows in a table.
 *  
 * <p> Methods are also provided to compensate for the limited number of data types
 * that an SQLite database can store, as SQLite databases can only store integers,
 * strings, null values, decimal values, and BLOBs data types.
 * 
 * <p> Because tables vary significantly on the number of columns, their data types and
 * across databases there is no 'one-size fits all' solution that can cater to every
 * possible table. Therefore, this class should be extended by other classes dedicated
 * to managing a specific table. In this way, {@code TableManager}'s subclasses can take
 * full advantage of the useful functions this class provides. </p>
 * 
 * <h2> Using This Class </h2>
 * <p> As mentioned, for this class to be useful this needs to be extended by subclasses
 * because there is no 'one-size fits all' solution that can cater to every possible table.
 * Fortunately, most of the functions in this class can handle interactions with an SQLite
 * database well. Programmers need to write two functions:{code generateINSERTQuery()}
 * and {@code generateUPDATEQuery()}. As the name suggests, programmers need to
 * create a function that generate INSERT and UPDATE queries to a database.
 * 
 * 
 * 
 * @since 1.0
 * @version 1.0
 * @author Ray Rafael Abenido
 * @implNote Most functions in this class have {@code protected} access modifier so that only
 * subclasses can use them. Other classes other than the subclasses are not expected to use
 * the functions here.
 *
 */

public class TableManager
{
    Connection connectionURL;
    String tableName;
    String primaryKey;
    

    
    /**
     * <p> Creates a {@code TableManager} instance that manages a specific table in a 
     * database.
     *
     * @param connectionURL the absolute path of the database in the host computer
     * @param tableName name of the table this instance will manage
     * @param primaryKey name of primary key of the table
     * @author Ray Rafael Abenido
     * @since 1.0
     */
    
    public TableManager(String tableName, String connectionURL, String primaryKey)
    {
        try {
            this.connectionURL = DriverManager.getConnection("jdbc:sqlite:" + connectionURL);
            this.tableName = tableName;
            this.primaryKey = primaryKey;
            System.out.println("Connected successfully");
            
        } catch(SQLException e) {
            System.out.println("Failed to connect!");
            e.printStackTrace();
        }
    }
    
    
    /**
     * <p> Returns all the rows of table managed by this instance as a
     * {@code ResultSet} object. How the {@code ResultSet} are printed or output
     * is dependent on the class extending {@code TableManager}.
     * 
     * <p> Note on Use: When retrieving all the rows of a table, run this function
     * first followed by your code on printing the result.
     *
     * @return {@code ResultSet} instance
     * @author Ray Rafael Abenido
     * @implNote <b> This function does not handle cases where the
     * {@code ResultSet} is empty </b>. How these null values are resolved are
     * left to the extending class.
     */
    @SuppressWarnings("unused")
    public final ResultSet getAllRecords()
    {
    	try {
        	String query = "SELECT * FROM " + tableName;
        	System.out.println(query);
        	Statement s = connectionURL.createStatement();
        	ResultSet result = s.executeQuery(query);
                System.out.println("Finished retrieval");	
                return result;
    	} catch (SQLException e) {
    		System.out.println("Failed to retrieve records from " + tableName 
    				+ ". See stack trace.");
    		e.printStackTrace();
    		
    	}
    	
    	return null;
    }
    
    /**
     * <p> Returns the record with the specified primary key ({@code pKey}) as 
     * {@code ResultSet} with either exactly one row or no rows.
     * 
     * <p> Note on Use: When retrieving a specific row of a table, run this function
     * first followed by your code on printing the result. 
     * 
     * @param pKey the primary key of the record being retrieved
     * @return ResultSet containing exactly one row or no rows
     * @author Ray Rafael Abenido
     * @implNote <b> This function does not handle cases where the
     * {@code ResultSet} is empty </b>. How these null values are resolved are
     * left to the extending class.
     */
    @SuppressWarnings("unused")
    public final ResultSet getRecord(int pKey) {
    	try {
    		String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + "=" + pKey;
        	System.out.println(query);
        	Statement s = connectionURL.createStatement();
        	ResultSet result = s.executeQuery(query);
        	return result;	
    	} catch (SQLException e) {
    		System.out.println("Failed to get specified record. See stack trace,");
    		e.printStackTrace();
    	}
		return null;
    }

    public final ResultSet getRecordWhere(String whereClause) {
        try {
    		String query = "SELECT * FROM " + tableName + " WHERE " + primaryKey + "= " + whereClause;
        	System.out.println(query);
        	Statement s = connectionURL.createStatement();
        	ResultSet result = s.executeQuery(query);
        	return result;	
    	} catch (SQLException e) {
    		System.out.println("Failed to get specified record. See stack trace,");
    		e.printStackTrace();
    	}
		return null;
    }
    
    /**
     * <p> Inserts or updates a record into database.
     * 
     * <p> Note on Use: This function is used in tandem with either {@code generateINSERTQuery()} or 
     * {@code generateUPDATEQuery()}. Run the aforementioned functions first before
     * calling this one.
     * 
     * @implNote Because tables' columns vary significantly, their INSERT and UPDATE
     * statements vary significantly. INSERT statements can come in the 'INSERT INTO
     * table COLUMN (...) VALUES (...) format or 'INSERT INTO table VALUES(...). 
     * UPDATE statements can update multiple values at once. In other words, these
     * two statements are not uniform. It will be simply easier for subclasses
     * of {@code TableManager} should generate their INSERT or UDPATE query beforehand
     * then pass it to this function.
     * @see generateUPDATEQuery()
     * @see generateINSERTQuery()
     * @param query The SQL INSERT or UPDATE statement to be executed
     * @author Ray Rafael Abenido
     * 
     */
    
    public final void modifyRecord(String query) {
    	try {
    		Statement s = connectionURL.createStatement();
    		s.executeUpdate(query);
    		System.out.println("Successfully inserted or updated into table.");
    	} catch (SQLException e) {
    		System.out.println("Failed to insert record. See stack trace.");
    		e.printStackTrace();
    	}
    }
    
    /**
     * <p> Deletes the record on the table with the specified {@code key}
     * 
     * <p> Note on use: Simply pass the id of record to be deleted into this function.
     * 
     * @param key
     * @author Ray Rafael Abenido
     */
    public final void deleteRecord(int key) {
    	try {
    		String query = "DELETE FROM " + tableName +  " WHERE " + primaryKey + "= " + key;
    		Statement s = connectionURL.createStatement();
    		s.executeUpdate(query);
    		System.out.println("Successfully deleted record in table.");
    	} catch (SQLException e) {
    		System.out.println("Failed to delete record. See stack trace.");
    		e.printStackTrace();
    	}
    }

    /* ========================================================================
     * FUNCTIONS TO COMPENSATE FOR SQLite's DATA TYPE LIMITATIONS
     * ======================================================================== 
     */
    
    /**
     * <p> Because of SQLite's limited data types, it does not have a equivalent
     * for Java's boolean. This function then resolves this issue by storing
     * booleans as strings instead. These strings can be converted back to boolean
     * using {@code convertToBoolean()}.
     * 
     * @param b the boolean to convert
     * @return The string equivalent of the boolean
     * @author Ray Rafael Abenido
     */
    public String convertToSQLiteBoolean(Boolean b) {
    	if (b) {
    		return "True";
    	} else {
    		return "False";
    	}
    }
    
    /**
     * <p> Because of SQLite's limited data types, it does not have an equivalent
     * for Java's boolean. This function, along with {@code convertToSQLITEBoolean()}
     * resolves this problem.
     * 
     * <p> This function converts a {@code String} value representing a boolean back into
     * a boolean value in Java. 
     * 
     * @param s the String in the database to convert
     * @return The boolean equivalent of the String
     */
    public Boolean convertToJavaBoolean(String s) {
    	if (s == "True") {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * <p> Because of SQLite's limited data types, it does not have an equivalent for Java's
     * {@code LocalDateTime} data type. This function then converts an {@code LocalDateTime}
     * into a {@code String} so it could be stored in the SQLite.
     * 
     * @param A {@code LocalDateTime} value to be stored in the database
     * @return A {@code String} version
     * @author Ray Rafael Abenido
     */
    protected String convertToSQLiteLD(LocalDate dateTime) {
    	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String formattedDateTime = dateTime.format(format);
    	return formattedDateTime;
    }

    /**
     * <p> Because of SQLite's limited data types, it does not have an equivalent for Java's
     * {@code LocalDateTime} data type. As a workaround, this class' stores {@code LocalDateTime}
     * as a {@code String} in the database. This function then converts these {@code String} back
     * into their {@code LocalDateTime} equivalent.
     * 
     * @param formattedString to be converted
     * @return Its {@code LocalDateTime} equivalent
     * @author Ray Rafael Abenido
     */
    protected LocalDate convertToJavaLD(String formattedString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        return LocalDate.parse(formattedString, format);
    }
}