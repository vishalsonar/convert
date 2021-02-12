package com.sonar.vishal.convert.logic;

import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sonar.vishal.convert.exception.FailedToConvertException;
import com.sonar.vishal.convert.util.Inflector;

public class XML {

	private static final String version = "1.0";
	private Transformer transformer;
	private Document document;
	private Inflector inflector;
	private boolean isJsonObject;

	public XML(DocumentBuilder documentBuilder, Transformer xmlTransformer) {
		document = documentBuilder.newDocument();
		document.setXmlStandalone(true);
		document.setXmlVersion(version);
		inflector = new Inflector();
		transformer = xmlTransformer;
	}

	public String generate(String json) throws FailedToConvertException {
		JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
		String key = (String) jsonObject.keySet().toArray()[0];
		JsonElement element = jsonObject.get(key);
		if (!(element.isJsonObject() || element.isJsonArray())) {
			throw new FailedToConvertException("root child must be a json object or json array");
		}
		document.appendChild(setJsonObjectXml(key, jsonObject));
		if (element.isJsonObject()) {
			isJsonObject = true;
		}
		return convertXmlToString();
	}

	private String convertXmlToString() {
		String result = null;
		DOMSource domSource = null;
		try {
			StringWriter writer = new StringWriter();
			if (isJsonObject) {
				domSource = new DOMSource(document.getFirstChild().getFirstChild());
			} else {
				domSource = new DOMSource(document);
			}
			StreamResult streamResult = new StreamResult(writer);
			transformer.transform(domSource, streamResult);
			result = writer.toString();
		} catch (Exception e) {
			result = "";
		}
		return result;
	}

	private Element setJsonObjectXml(String key, JsonObject element) throws FailedToConvertException {
		String innerKey = null;
		Element tempParentElement = document.createElement(key);
		Iterator<String> iterator = element.keySet().iterator();
		while (iterator.hasNext()) {
			innerKey = iterator.next();
			JsonElement innerKeyElement = element.get(innerKey);
			if (innerKeyElement.isJsonPrimitive()) {
				tempParentElement.appendChild(setJsonPrimitiveXml(innerKey, innerKeyElement));
			}
			if (innerKeyElement.isJsonObject()) {
				JsonObject innerObject = innerKeyElement.getAsJsonObject();
				tempParentElement.appendChild(setJsonObjectXml(innerKey, innerObject));
			}
			if (innerKeyElement.isJsonArray()) {
				JsonArray innerArray = innerKeyElement.getAsJsonArray();
				Element innerElement = setJsonArrayXml(innerKey, innerArray);
				setJsonArrayXmlChild(tempParentElement, innerElement);
			}
		}
		return tempParentElement;
	}

	private Element setJsonArrayXml(String key, JsonArray element) throws FailedToConvertException {
		Element tempParentElement = document.createElement(key);
		String singular = inflector.singularize(key);
		Iterator<JsonElement> iterator = element.iterator();
		JsonElement array = null;
		while (iterator.hasNext()) {
			array = iterator.next();
			if (array.isJsonPrimitive()) {
				throw new FailedToConvertException("array of json primitive not supported");
			}
			if (array.isJsonArray()) {
				JsonArray innerArray = array.getAsJsonArray();
				tempParentElement.appendChild(setJsonArrayXml(singular, innerArray));
			}
			if (array.isJsonObject()) {
				JsonObject innerObject = array.getAsJsonObject();
				tempParentElement.appendChild(setJsonObjectXml(singular, innerObject));
			}
		}
		return tempParentElement;
	}

	private Element setJsonPrimitiveXml(String key, JsonElement element) {
		String data = element.getAsString();
		Element keyElement = document.createElement(key);
		keyElement.appendChild(document.createTextNode(data));
		return keyElement;
	}

	private void setJsonArrayXmlChild(Element parentElement, Element childElement) {
		NodeList list = childElement.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; i++) {
			parentElement.appendChild(list.item(0));
		}
	}
}