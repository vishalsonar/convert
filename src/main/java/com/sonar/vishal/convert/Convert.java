package com.sonar.vishal.convert;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import com.sonar.vishal.convert.logic.JSON;
import com.sonar.vishal.convert.logic.XML;

public class Convert {

	private XML xml;
	private JSON json;

	public Convert() {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			xml = new XML(documentBuilder, transformer);
			json = new JSON(documentBuilder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toXml(String json) {
		String result = null;
		try {
			result = xml.generate(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String toJson(String xml) {
		String result = null;
		try {
			result = json.generate(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}