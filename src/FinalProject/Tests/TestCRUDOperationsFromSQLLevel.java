package FinalProject.Tests;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import FinalProject.Characters;
import FinalProject.SQLiteDatabaseSingleton;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCRUDOperationsFromSQLLevel {
	SQLiteDatabaseSingleton instance = SQLiteDatabaseSingleton.getInstance();
	Characters character= new Characters(0, "Antas", "Jain", "jainSahab", "Shahi Khandan");
	String query = "SELECT * FROM CHARACTERS WHERE TITLE = \"BOSS\";";
	@Test
	public void test1_insertion() {
		int lengthOfCurrentTable = instance.selectAllStatement().size();
		
		instance.insertStatement(character.getFIRSTNAME(), character.getLASTNAME(), character.getTITLE(), character.getFAMILY());
		int newLengthOftable = instance.selectAllStatement().size();
		assertTrue("Data inserted Successfully",newLengthOftable==lengthOfCurrentTable+1);
	}
	@Test
	public void test2_updation() {
		instance.update("TITLE", "BOSS", "TITLE", character.getTITLE());
		assertFalse(instance.executeQuery(query).isEmpty());
	}
	@Test
	public void test3_deletion() {
		int lengthBeforeDeletion = instance.selectAllStatement().size();
		instance.deleteStatement("FIRSTNAME", character.getFIRSTNAME());
		int lengthAfterDeletion = instance.selectAllStatement().size();
		assertTrue(lengthBeforeDeletion>lengthAfterDeletion);
	}

}
