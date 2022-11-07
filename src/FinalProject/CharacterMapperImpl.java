package FinalProject;


import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CharacterMapperImpl implements CharacterMapper {
	
	private static CharacterMapperImpl singleton;
	private CharacterMapperImpl() {
		
	}
	public static CharacterMapperImpl getInstance() {
		if(singleton==null) {
			singleton = new CharacterMapperImpl();
		}
		return singleton;
	}
	SQLiteDatabaseSingleton db = SQLiteDatabaseSingleton.getInstance();
	SetupAPI apiData = SetupAPI.getInstance();
	@Override
	public void INSERT(Characters character) {
		db.insertStatement(character.getFIRSTNAME(), character.getLASTNAME(), character.getTITLE(), character.getFAMILY());
	}

	@Override
	public void DELETE(Characters character) {
		db.deleteStatement(character.getId());
	}

	@Override
	public void UPDATE(String what, String newVal, String whereClauseVal, String oldVal) {
		db.update(what, newVal, whereClauseVal, oldVal);
	}

	public Characters[] SELECT() {
		List<Characters> list = db.selectAllStatement();
		Characters li[] =new Characters[list.size()];
		int x =0;
		for(Characters i:list) {
			li[x++]=i;
		}
		System.out.println(list);
		return li;
	}
	public void init(){
		JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(apiData.getResponse());
		
		System.out.println(jsonArray);
		for(int i =0;i<jsonArray.length();i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			System.out.println(obj.getInt("id")+" "+obj.getString("firstName")+" "+obj.getString("lastName")+" "+obj.getString("title")+" "+obj.getString("family"));
			Characters ch = new Characters(obj.getInt("id"),obj.getString("firstName"),obj.getString("lastName"),obj.getString("title"),obj.getString("family"));
			INSERT(ch);
		}
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
