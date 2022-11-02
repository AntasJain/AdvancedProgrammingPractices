package FinalProject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class SetupAPI {
	static String responseStr;
	public static void fetchAPI() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://game-of-thrones1.p.rapidapi.com/Characters"))
					.header("X-RapidAPI-Key", "4117811351mshf3172028556998ep1e721bjsn0565c8bc8dc7")
					.header("X-RapidAPI-Host", "game-of-thrones1.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody()).build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request,
					HttpResponse.BodyHandlers.ofString());
			responseStr=response.body();
			System.out.println(response.body());
			
			//PrintWriter out = new PrintWriter(new FileWriter("response.json", true));
			//out.println(response.body());
			//out.close();
			JSONArray jArray = new JSONArray(responseStr);
			System.out.println(jArray.length());
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
