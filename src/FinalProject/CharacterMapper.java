package FinalProject;


public interface CharacterMapper {
	public void INSERT(Characters character);
	public void DELETE(Characters character);
	public void UPDATE(String setWhat, String whatValue, String whereWhat, String criteriaWhat);

	
}
