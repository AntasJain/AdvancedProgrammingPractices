package FinalProject;

public class StringAssets {
	static String TABLE_NAME = "Characters";
	static String INSERT_STATEMENT = "INSERT INTO "+TABLE_NAME+" (FIRSTNAME,LASTNAME,TITLE,FAMILY) VALUES(?,?,?,?);";
	static String DELETE_STATEMENT = "DELETE FROM "+TABLE_NAME+" WHERE ID = ?;";
	static String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+
										"ID INTEGER PRIMARY KEY,"+
										"FIRSTNAME TEXT NOT NULL,"+
										"LASTNAME TEXT NOT NULL,"+
										"TITLE TEXT NOT NULL,"+
										"FAMILY TEXT NOT NULL);";
	static String SELECT_ALL = "Select * FROM "+TABLE_NAME+";";
	static String UPDATE_STATEMENT = "UPDATE "+TABLE_NAME+" SET ";
	static String WHERE_CLAUSE = " WHERE ";
}
