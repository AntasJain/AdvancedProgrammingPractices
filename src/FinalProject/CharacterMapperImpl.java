package FinalProject;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CharacterMapperImpl implements CharacterMapper{
	private Connection conn;
	private SetupAPI apiInstance;
	private SQLIteDatabase dbInstance;
	private static CharacterMapperImpl obj;
	private CharacterMapperImpl(){
		dbInstance = new SQLIteDatabase();
		apiInstance = SetupAPI.getInstance();
		conn = dbInstance.initializeConnection();
	}
	
	public static CharacterMapperImpl getInstance() {
		if(obj==null) {
			obj = new CharacterMapperImpl();
		}
		return obj;
	}
	public void init() {
		File f = new File("GOTDB.sqlite");
		if(f.exists() && !f.isDirectory()) {
			return;
		}
		else{
			System.out.println("Creating Table");
			JSONArray jsonArray;
			try {
				PreparedStatement CREATE = conn.prepareStatement(StringAssets.CREATE_STATEMENT);
				CREATE.execute();
				jsonArray = new JSONArray(apiInstance.getResponse());
			
			for(int i =0;i<jsonArray.length();i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				System.out.println(obj.getInt("id")+" "+obj.getString("firstName")+" "+obj.getString("lastName")+" "+obj.getString("title")+" "+obj.getString("family"));
				Characters ch = new Characters(obj.getInt("id"),obj.getString("firstName"),obj.getString("lastName"),obj.getString("title"),obj.getString("family"));
				INSERT(ch);
			}
			
			} catch (JSONException | SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	
	}
	@Override
	public void INSERT(Characters character) {
		try {
			PreparedStatement insertQuery = conn.prepareStatement(StringAssets.INSERT_STATEMENT);
			insertQuery.setString(1, character.getFIRSTNAME());
			insertQuery.setString(2, character.getLASTNAME());
			insertQuery.setString(3, character.getTITLE());
			insertQuery.setString(4, character.getFAMILY());
			int n = insertQuery.executeUpdate();
			System.out.println("Inserted "+n+" Row(s).");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void UPDATE(String toBeChanged, String newData, String criteria, String oldData) {
		String query = StringAssets.UPDATE_STATEMENT+toBeChanged+" = ";
		try {
			PreparedStatement update = null;
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
			update= conn.prepareStatement(query);
			int updated = update.executeUpdate();
			System.out.println("Updated "+updated+" Row(s).");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void DELETE(String cols, String value) {
		try {
		PreparedStatement delete = conn.prepareStatement(StringAssets.DELETE_STATEMENT_ID);
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
		else if("TITLE".equals(cols)) {
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
	
	public Characters[] EXECUTE(String qry) {
		List<Characters> listRes = new ArrayList<>();
		try {
			PreparedStatement execute = null;
			execute = conn.prepareStatement(qry);
			ResultSet rs = execute.executeQuery();
			while(rs.next()) {
				Characters ch = new Characters(rs.getInt("ID"),rs.getString("FIRSTNAME"),rs.getString("LASTNAME"),rs.getString("TITLE"),rs.getString("FAMILY"));
				listRes.add(ch);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		Characters li[] = new Characters[listRes.size()];
		int x =0;
		for(Characters i : listRes) {
			li[x++]=i;
		}
		return li;
	}
	public Characters[] SELECT() {
		return EXECUTE(StringAssets.SELECT_ALL);
	}
	public void closeDBConnection() {
		try {
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
