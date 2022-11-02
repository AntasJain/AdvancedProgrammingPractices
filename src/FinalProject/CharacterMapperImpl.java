package FinalProject;

import java.util.List;

public class CharacterMapperImpl implements CharacterMapper {
	SQLiteDatabaseSingleton db = SQLiteDatabaseSingleton.getInstance();
	@Override
	public void INSERT(Characters character) {
		db.insertStatement(character.getFIRSTNAME(), character.getLASTNAME(), character.getTITLE(), character.getFAMILY());
	}

	@Override
	public void DELETE(Characters character) {
		db.deleteStatement(character.getId());
	}

	@Override
	public void UPDATE(Characters character) {
		
	}

	@Override
	public void SELECT() {
		List<Characters> list = db.selectAllStatement();
		for(Characters character: list) {
			System.out.println(character.toString());
		}
	}
	
	

}
