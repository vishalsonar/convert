package com.sonar.vishal.convert.util;

import java.io.File;
import java.util.Scanner;

public class FileUtil {

	private static final String DATA_PATH = "/com/sonar/vishal/convert/data/";

	/**
	 * Returns File data as String.
	 * 
	 * @param fileName
	 * @return file data as String
	 */
	public static String readAsString(String fileName) {
		StringBuilder builder = new StringBuilder();
		Scanner sc;
		try {
			String filePath = FileUtil.class.getResource(DATA_PATH + fileName).getFile();
			if (filePath.contains("%20")) {
				filePath = filePath.replaceAll("%20", " ");
			}
			File file = new File(filePath);
			sc = new Scanner(file);
			while (sc.hasNext()) {
				builder.append(sc.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
