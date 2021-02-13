package com.sonar.vishal.convert.util;

import java.io.File;
import java.util.Scanner;

public class FileUtil {

	private static final String DATA_PATH = "src/test/resources/";

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
			File file = new File(DATA_PATH + fileName);
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
