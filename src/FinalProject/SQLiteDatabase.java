package FinalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase {
	Connection conn;
	Statement stmt;
	PreparedStatement insert,delete,update,create,select;
	public SQLiteDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn=DriverManager.getConnection("jdbc:sqlite:GOTDB.sqlite");
			System.out.println("DB Connected Successfully");
			Thread.sleep(20);
			create=conn.prepareStatement(StringAssets.CREATE_STATEMENT);
			create.execute();
			System.out.println("Table Connected Successfully");
		}
		catch(Exception e) {
			System.out.println("Failed to Proceed :"+e.getMessage());
		}
	}
	public void closeDBConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void insertStatement(String fName, String lName, String title, String family) {
		try {
			insert = conn.prepareStatement(StringAssets.INSERT_STATEMENT);
			insert.setString(1, fName);
			insert.setString(2, lName);
			insert.setString(3, title);
			insert.setString(4, family);
			insert.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteStatement(int value) {
		try {
			delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT);
			delete.setInt(1, value);
			int deleted = delete.executeUpdate();
			System.out.println("Deleted "+deleted+" Row(s).");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void selectAllStatement(){
		try {
			select=conn.prepareStatement(StringAssets.SELECT_ALL);
			ResultSet rs = select.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("ID")+"\t"+rs.getString("FIRSTNAME")+"\t"+rs.getString("LASTNAME")+"\t"+rs.getString("TITLE")+"\t"+rs.getString("FAMILY"));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
