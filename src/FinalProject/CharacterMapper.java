package FinalProject;


public interface CharacterMapper {
	public void INSERT(Characters character);
	public void UPDATE(String setWhat, String whatValue, String whereWhat, String criteriaWhat);
	public void DELETE(String cols, String value);

	
}
