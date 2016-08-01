package adobe.analytics.ws.logs.adapt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogBeautifier {

	public static void main(String[] args) throws IOException {
		convertLog();
		/*Files.walk(Paths.get("C:/Users/vishwsin/Desktop/work/logs")).forEach(filePath -> {
			if (Files.isRegularFile(filePath)) {
				convertLog(filePath.toString());
			}
		});
		*/// convertLog();
	}
	
	private static List<String> convertLog() {

		try (BufferedReader br = new BufferedReader(
				new FileReader("C:/Users/vishwsin/Desktop/work/logs/Virtual_RS_20_Requests.log"))) {

			String sCurrentLine;
			List<String> sList = new ArrayList<String>();
			while ((sCurrentLine = br.readLine()) != null) {

				if (sCurrentLine.contains("[URL]:")) {
					System.out.println("------------------------------------------------------------------------------------------------"+"\n\n");
					String tempURL = "url: " + sCurrentLine.split((Pattern.quote(" [URL]:")))[1].trim();
					sList.add(tempURL);
					System.out.println(tempURL);
					{
					}
				}

				if (sCurrentLine.contains("[Post]:") || sCurrentLine.contains("[Get]:")) {
					String reqBody = "requestbody: ";
					if (sCurrentLine.contains("{")) {
						reqBody = reqBody.concat("{");
						int openedBrackets = 1;
						String tempCurrentLine;
						while (openedBrackets != 0) {
							tempCurrentLine = br.readLine();
							reqBody = reqBody.concat(tempCurrentLine);
							if (tempCurrentLine.contains("}"))
								--openedBrackets;
							if (tempCurrentLine.contains("{"))
								++openedBrackets;
						}
						System.out.println(reqBody);
					}

				}

				if (sCurrentLine.contains("[Method]")) {
					String mString = "method: " + sCurrentLine.split((Pattern.quote("]:")))[1].trim();
					System.out.println(mString);
				}
				if (sCurrentLine.contains("[Response")) {
					String resp = "response: ";
					/*
					 * This works on assumption that {\n and \n} are properly
					 * written
					 */
					if (sCurrentLine.contains("{")) {
						resp = resp.concat("{");
						int openedBrackets = 1;
						String tempCurrentLine;
						while (openedBrackets != 0 && (tempCurrentLine = br.readLine()) != null) {

							resp = resp.concat(tempCurrentLine);
							if (tempCurrentLine.contains("}"))
								--openedBrackets;
							if (tempCurrentLine.contains("{"))
								++openedBrackets;
						}
						System.out.println(resp);
					} else if (sCurrentLine.contains("]: [")) {
						resp = resp.concat("[");
						int openedBrackets = 1;
						String tempCurrentLine;
						while (openedBrackets != 0 && (tempCurrentLine = br.readLine()) != null) {
							resp = resp.concat(tempCurrentLine);
							if (tempCurrentLine.contains("]"))
								--openedBrackets;
							if (tempCurrentLine.contains("["))
								++openedBrackets;
						}
						System.out.println(resp);
					} else {
						String sResp = "";
						try {
							sResp = sCurrentLine.split((Pattern.quote("]:")))[1].trim();
						} catch (ArrayIndexOutOfBoundsException ex) {
							sResp = "";
						}
						resp = resp.concat(sResp);
						System.out.println(resp);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}