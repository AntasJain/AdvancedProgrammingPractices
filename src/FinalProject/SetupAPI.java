package FinalProject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SetupAPI {
	private static SetupAPI singleton;
	private HttpResponse<String> response;
	private SetupAPI() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://game-of-thrones1.p.rapidapi.com/Characters"))
					.header("X-RapidAPI-Key", "4117811351mshf3172028556998ep1e721bjsn0565c8bc8dc7")
					.header("X-RapidAPI-Host", "game-of-thrones1.p.rapidapi.com")
					.method("GET", HttpRequest.BodyPublishers.noBody()).build();
			response = HttpClient.newHttpClient().send(request,
					HttpResponse.BodyHandlers.ofString());
			
//			System.out.println("Response Status "+response.statusCode());
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static SetupAPI getInstance() {
		if(singleton==null) {
			singleton = new SetupAPI();
		}
		return singleton;
		
	}
	public String getResponse() {
		return response.body();
	}
	public int getStatusCode() {
		return response.statusCode();
	}

}
