package com.sonar.vishal.convert.logic;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JSON {

	private DocumentBuilder documentBuilder;

	public JSON(DocumentBuilder documentBuilderParam) {
		documentBuilder = documentBuilderParam;
	}

	public String generate(String xml) throws SAXException, IOException {
		Document document = null;
		JsonObject root = null;
		document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
		NodeList nodeList = document.getChildNodes();
		root = setXmlTree(nodeList).getAsJsonObject();
		return root.toString();
	}

	private JsonElement setXmlTree(NodeList nodeList) {
		Node node = null;
		String name = null;
		int length = 0;
		int loopLength = nodeList.getLength();
		JsonObject parentObject = new JsonObject();
		for (int i = 0; i < loopLength; i++) {
			node = nodeList.item(i);
			name = node.getNodeName();
			length = node.getChildNodes().getLength();
			if (name.contains("#text")) {
				continue;
			}
			if (length == 0 || length == 1) {
				parentObject.add(name, setXmlElement(node));
				continue;
			}
			if (length > 1) {
				JsonElement element = setXmlTree(node.getChildNodes());
				if (parentObject.has(name)) {
					JsonElement innerElement = parentObject.get(name);
					if (innerElement.isJsonObject()) {
						JsonArray array = new JsonArray();
						array.add(innerElement);
						array.add(element);
						parentObject.add(name, array);
					}
					if (innerElement.isJsonArray()) {
						innerElement.getAsJsonArray().add(element);
						parentObject.add(name, innerElement);
					}
				} else {
					parentObject.add(name, element);
				}
			}
		}
		return parentObject;
	}

	private JsonElement setXmlElement(Node node) {
		return new JsonPrimitive(node.getTextContent());
	}
}