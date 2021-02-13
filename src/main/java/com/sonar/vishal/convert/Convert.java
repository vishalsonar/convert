package com.sonar.vishal.convert;

import javax.xml.XMLConstants;
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
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			documentFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			documentFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
			Transformer transformer = transformerFactory.newTransformer();
			xml = new XML(documentBuilder, transformer);
			json = new JSON(documentBuilder);
		} catch (Exception e) {
			// Do Nothing.
		}
	}

	public String toXml(String json) {
		String result = null;
		try {
			result = xml.generate(json);
		} catch (Exception e) {
			// Do Nothing.
		}
		return result;
	}

	public String toJson(String xml) {
		String result = null;
		try {
			result = json.generate(xml);
		} catch (Exception e) {
			// Do Nothing.
		}
		return result;
	}
}