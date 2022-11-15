package FinalProject;


public class Characters {
	private int id;
	@Override
	public String toString() {
		return "Character [id=" + id + ", FIRSTNAME=" + FIRSTNAME + ", LASTNAME=" + LASTNAME + ", TITLE=" + TITLE
				+ ", FAMILY=" + FAMILY + "]";
	}
	private String FIRSTNAME;
	private String LASTNAME;
	private String TITLE;
	private String FAMILY;
	public Characters(int id, String firstName, String lastName, String title, String family) {
		FIRSTNAME = firstName;
		LASTNAME = lastName;
		TITLE = title;
		FAMILY = family;
		this.id = id;
	}
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}
	public String getLASTNAME() {
		return LASTNAME;
	}
	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getFAMILY() {
		return FAMILY;
	}
	public void setFAMILY(String fAMILY) {
		FAMILY = fAMILY;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
