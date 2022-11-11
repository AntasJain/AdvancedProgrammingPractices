package FinalProject.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import FinalProject.CharacterMapperImpl;
import FinalProject.Characters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCRUDOperationsFromMapperLevel {

	CharacterMapperImpl mapperInstance = CharacterMapperImpl.getInstance();
	Characters character= new Characters(0, "Priyansi", "Singh", "ProjectPartner", "Singh");
	String query = "SELECT * FROM CHARACTERS WHERE TITLE = \"BOSS\";";
	@Test
	public void test1_insertion() {
		int beforeInsertion = mapperInstance.SELECT().length;
		mapperInstance.INSERT(character);
		int afterInsertion = mapperInstance.SELECT().length;
		assertTrue(beforeInsertion==afterInsertion-1);
	}
	
	@Test
	public void test2_updation() {
		mapperInstance.UPDATE("TITLE", "BOSS", "TITLE", character.getTITLE());
		assertFalse(mapperInstance.EXECUTE(query).length==0);
	}
	
	@Test
	public void test3_deletion() {
		int lengthBeforeDeletion = mapperInstance.SELECT().length;
		mapperInstance.DELETE("FIRSTNAME", character.getFIRSTNAME());
		int lengthAfterDeletion = mapperInstance.SELECT().length;
		assertTrue(lengthBeforeDeletion>lengthAfterDeletion);
	}
	

}
