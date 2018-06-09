package search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WikipediaOSuerte {

	public String solve(String tema) throws IOException {
		String respuesta;
		
		try {
			respuesta = buscarEnWikipedia(tema);
		} catch(FileNotFoundException e) {
			respuesta = voyATenerSuerte(tema);
		}
		
		return respuesta;
	}
	
	private String buscarEnWikipedia(String tema) throws IOException {
		String urlString = "https://es.wikipedia.org/wiki/" + tema;
		getContent(urlString);
		return urlString;
	}
	
	private String voyATenerSuerte(String tema) throws MalformedURLException {
		return "https://google.com.ar/search?btnI&q=" + tema;
	}
	
	private String getContent(String urlString) throws IOException {
		System.setProperty("http.agent", "Brave");
		URL url = new URL(urlString);
		URLConnection urlConnection = url.openConnection();
		InputStream is = urlConnection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String content = "";
		String linea = br.readLine();
		while(linea != null) {
			content += linea;
			linea = br.readLine();
		}
		return content;
	}
	
}
