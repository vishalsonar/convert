package com.sonar.vishal.convert.util;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.google.gson.JsonParser;

public class ValidateUtil {

	private static DocumentBuilder documentBuilder;

	public static boolean isValidateXml(String xml) {
		boolean state = true;
		try {
			if (documentBuilder == null) {
				documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			}
			documentBuilder.parse(new ByteArrayInputStream(xml.getBytes())).getChildNodes();
		} catch (Exception e) {
			state = false;
		}
		return state;
	}

	public static boolean isValidJson(String json) {
		boolean state = true;
		try {
			JsonParser.parseString(json).getAsJsonObject();
		} catch (Exception e) {
			state = false;
		}
		return state;
	}
}
