package FinalProject;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLIteDatabase {
	
	public Connection initializeConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:GOTDB.sqlite");
		}
		catch(Exception e) {
			System.out.println("Failed to Create Connection!");
		}
		return conn;
	}
	
}
