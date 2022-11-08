package FinalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseSingleton {
	
	Connection conn;
	Statement stmt;
	PreparedStatement insert,delete,update,create,select,execute;
	private static SQLiteDatabaseSingleton singleton;
	
	private SQLiteDatabaseSingleton() {
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
	};
	
	public static SQLiteDatabaseSingleton getInstance() {
		if(singleton==null) {
			singleton = new SQLiteDatabaseSingleton();
		}
		return singleton;
		
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
	public void deleteStatement(String cols,String value) {
		try {
		//	delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT);
		//	delete.setInt(1, value);
			if("ID".equals(cols)) {
					delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_ID);
					delete.setInt(1, Integer.valueOf(value));	
			}
			else if("FIRSTNAME".equals(cols)) {
				delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_FIRSTNAME);
				delete.setString(1, value);
			}
			else if("LASTNAME".equals(cols)) {
				
				delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_LASTNAME);
				delete.setString(1, value);
			}
			else if("TTILE".equals(cols)) {
				delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_TITLE);
				delete.setString(1, value);
			}
			else if("FAMILY".equals(cols)) {
				delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_FAMILY);
				delete.setString(1, value);
			}
			int deleted = delete.executeUpdate();
			System.out.println("Deleted "+deleted+" Row(s).");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void update(String toBeChanged, String newData, String criteria, String oldData) {
		String query = StringAssets.UPDATE_STATEMENT+toBeChanged+" = ";
		try {
			if(toBeChanged.equalsIgnoreCase("ID")){
				int querynewId = Integer.valueOf(newData);
				query+=querynewId;
			}else {
				query+="\""+newData+"\"";
			}
			query+=StringAssets.WHERE_CLAUSE+criteria+" = ";
			
			if(criteria.equalsIgnoreCase("ID")) {
				int queryOldData = Integer.valueOf(oldData);
				query+=queryOldData+";";
			}
			else {
				query+="\""+oldData+"\""+";";
			}
			System.out.println(query);
			System.out.println(criteria);
			update= conn.prepareStatement(query);
			int updated = update.executeUpdate();
			System.out.println("Updated "+updated+" Row(s).");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public List<Characters> executeQuery(String qry) {
		List<Characters> listRes = new ArrayList<>();
		try {
			execute = conn.prepareStatement(qry);
			ResultSet rs = execute.executeQuery();
			while(rs.next()) {
				Characters ch = new Characters(rs.getInt("ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("TITLE"),rs.getString("FAMILY"));
				listRes.add(ch);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listRes;
		
	}
	public List<Characters> selectAllStatement(){
		List<Characters> listRes = new ArrayList<>();
		try {
			
			select=conn.prepareStatement(StringAssets.SELECT_ALL);
			ResultSet rs = select.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getInt("ID")+"\t"+rs.getString("FIRSTNAME")+"\t"+rs.getString("LASTNAME")+"\t"+rs.getString("TITLE")+"\t"+rs.getString("FAMILY"));
				Characters ch = new Characters(rs.getInt("ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("TITLE"),rs.getString("FAMILY"));
				listRes.add(ch);
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return listRes;
	}

}
