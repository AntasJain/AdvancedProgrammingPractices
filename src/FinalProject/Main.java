package FinalProject;

//import java.io.File;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
//		File f = new File("response.json");
//		if(f.exists()) {
//			System.out.println("File's Ready for Operations");
//		}
//		else {
	//		SetupAPI.fetchAPI();
//		}
//		SetupAPI.fetchAPI();
//		SQLiteDatabase db = new SQLiteDatabase();
//		Thread.sleep(10);
//		db.insertStatement("Danaerys", "targaryn", "Mother of Dragons", "TARGARYN");
//		Thread.sleep(10);
//		//db.selectAllStatement();
//		db.deleteStatement(0);
//		Thread.sleep(10);
//		db.selectAllStatement();
//		db.closeDBConnection();
//		PushJSONToSQL obj = new PushJSONToSQL();
//		obj.pushResponseToDB();
		SQLiteDatabaseSingleton obj = SQLiteDatabaseSingleton.getInstance();
		obj.insertStatement("Danaerys", "targaryn", "Mother of Dragons", "TARGARYN");
		obj.selectAllStatement();
		
	}
	
	

}
