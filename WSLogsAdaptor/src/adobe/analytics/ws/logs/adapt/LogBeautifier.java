package adobe.analytics.ws.logs.adapt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogBeautifier {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/vishwsin/Desktop/work/Dallas_19_Requests.log")))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.contains("[URL]:")){}
					
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
