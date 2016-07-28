package adobe.analytics.ws.logs.adapt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogCleaner {

	public static void main(String[] args) throws IOException {
		printLog(getObjList());
	}

	private static void printLog(List<LogObj> objList) {
		System.out.println(objList.size());

		for (LogObj obj : objList) {
			System.out.println("url:" + obj.getUrl());
			System.out.println("method:" + obj.getMethod());
			System.out.println("requestbody:" + obj.getReqBody());
			System.out.println("response:" + obj.getResponse());
			System.out.println();
		}
	}

	private static List<LogObj> getObjList() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:/Work/logs/run.log"));

		String sCurrentLine;
		List<LogObj> obList = new ArrayList<LogObj>();
		int count = 0;
		while ((sCurrentLine = br.readLine()) != null) {
			String tempStr = sCurrentLine;
			LogObj log = null;
			while (tempStr != null && !tempStr.startsWith("---------") && (sCurrentLine.trim().length() != 0)) {
				System.out.println(++count);
				log = new LogObj();
				if (tempStr.startsWith("url:")) {
					log.setUrl(tempStr.substring(4));
					tempStr = br.readLine();
				}
				if (tempStr.startsWith("method:")) {
					log.setMethod(tempStr.substring(7));
					tempStr = br.readLine();
				}
				if (tempStr.startsWith("requestbody:")) {
					log.setReqBody(tempStr.substring(12));
					tempStr = br.readLine();
				}
				if (tempStr.startsWith("response:")) {
					log.setResponse(tempStr.substring(9));
					tempStr = br.readLine();
				}
			}
			if (log != null)
				obList.add(log);
		}
		br.close();
		return obList;
	}
}