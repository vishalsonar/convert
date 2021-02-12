package com.sonar.vishal.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.sonar.vishal.convert.util.FileUtil;
import com.sonar.vishal.convert.util.ValidateUtil;

@RunWith(JUnit4.class)
public class JsonToXml {

	private String json;
	private Convert convert;

	public JsonToXml() {
		convert = new Convert();
	}

	/**
	 * Test null condition, empty string and other condition
	 */
	@Test
	public void toXml() {
		String xmlString = convert.toXml(json);
		assertNull(xmlString);
		xmlString = convert.toXml("");
		assertNull(xmlString);
		xmlString = convert.toXml("{}");
		assertNull(xmlString);
		json = "{ \"user\": { \"array\": [{ \"one\": \"one\" },[ \"two\" ]] } }";
		xmlString = convert.toXml(json);
		assertNull(xmlString);
	}

	/**
	 * Test for Valid Json but cannot be convert to xml
	 */
	@Test
	public void toXml0() {
		json = "{ \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\" }";
		String xmlString = convert.toXml(json);
		assertNull(xmlString);
	}

	/**
	 * Test to Convert simple Json string to xml string
	 */
	@Test
	public void toXml1() {
		json = "{ \"user\": { \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\" } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with object string to xml string
	 */
	@Test
	public void toXml2() {
		json = "{ \"user\": { \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\", \"object\": { \"para1\": \"one\", \"para2\": \"two\" } } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with inner object string to xml string
	 */
	@Test
	public void toXml3() {
		json = "{ \"user\": { \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\", \"object\": { \"para1\": \"one\", \"para2\": \"two\" }, \"parent\": { \"parent1\": \"one\", \"innerObject\": { \"inner\": \"innerOne\" } } } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with array object string to xml string
	 */
	@Test
	public void toXml4() {
		json = "{ \"user\": { \"name\": \"abc\", \"surname\": \"def\", \"height\": \"5.6\", \"other\": \"wxyz\", \"objects\": [ { \"para1\": \"one\", \"para2\": \"two\" }, { \"para1\": \"one\", \"para2\": \"two\" } ] } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with array string to xml string
	 */
	@Test
	public void toXml5() {
		json = "{ \"user\": { \"object\": [{ \"para1\": \"one\", \"para2\": \"two\" }, { \"para1\": \"one\", \"para2\": \"two\" }] } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with array of array string to xml string
	 */
	@Test
	public void toXml6() {
		json = "{ \"user\": { \"object\": [ [{ \"para1\": \"one\", \"para2\": \"two\" }, { \"para1\": \"one\", \"para2\": \"two\" }], [{ \"para1\": \"one2\", \"para2\": \"two2\" }, { \"para1\": \"one2\", \"para2\": \"two2\" }] ] } }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert Json with array string to xml string
	 */
	@Test
	public void toXml7() {
		json = "{ \"user\":{ \"array\":[ \"abc\", \"def\" ] } }";
		String xmlString = convert.toXml(json);
		assertNull(xmlString);
	}

	/**
	 * Test to Convert complex json to xml string
	 */
	@Test
	public void toXml8() {
		json = "{ \"problems\": [{ \"Diabetes\": [{ \"medications\": [{ \"medicationsClasses\": [{ \"className\": [{ \"associatedDrug\": [{ \"name\": \"asprin\", \"dose\": \"5\", \"strength\": \"500 mg\" }], \"associatedDrug2\": [{ \"name\": \"somethingElse\", \"dose\": \"3\", \"strength\": \"500 mg\" }] }], \"className2\": [{ \"associatedDrug\": [{ \"name\": \"asprin\", \"dose\": \"2\", \"strength\": \"500 mg\" }], \"associatedDrug2\": [{ \"name\": \"somethingElse\", \"dose\": \"1\", \"strength\": \"500 mg\" }] }] }] }], \"labs\": [{ \"missing_field\": \"missing_value\" }] }] }] }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert json with root array to xml string
	 */
	@Test
	public void toXml9() {
		json = "{ \"descendant\": [{ \"Firstname\": \"John\", \"Lastname\": \"Smith\", \"City\": \"Boston\", \"State\": \"MA\", \"Children\": [{ \"Name\": \"Callie\", \"Age\": 5 }, { \"Name\": \"Griffin\", \"Age\": 3 }, { \"Name\": \"Luke\", \"Age\": 1 }] }, { \"Firstname\": \"Henry\", \"Lastname\": \"Rhodes\", \"City\": \"New York\", \"State\": \"NY\", \"Children\": [{ \"Name\": \"Howard\", \"Age\": 15 }, { \"Name\": \"Robert\", \"Age\": 11 }] }, { \"Firstname\": \"Allison\", \"Lastname\": \"Berman\", \"City\": \"Los Angeles\", \"State\": \"CA\", \"Children\": [{ \"Name\": \"Jeff\", \"Age\": 35 }, { \"Name\": \"Roxanne\", \"Age\": 33 }, { \"Name\": \"Claudia\", \"Age\": 31 }, { \"Name\": \"Denzel\", \"Age\": 11 }] }] }";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert json with empty value to xml string
	 */
	@Test
	public void toXml10() {
		json = "{ \"problems\": [{ \"Diabetes\":[{ \"medications\":[{ \"medicationsClasses\":[{ \"className\":[{ \"associatedDrug\":[{ \"name\":\"asprin\", \"dose\":\"\", \"strength\":\"500 mg\" }], \"associatedDrug2\":[{ \"name\":\"somethingElse\", \"dose\":\"\", \"strength\":\"500 mg\" }] }], \"className2\":[{ \"associatedDrug\":[{ \"name\":\"asprin\", \"dose\":\"\", \"strength\":\"500 mg\" }], \"associatedDrug2\":[{ \"name\":\"somethingElse\", \"dose\":\"\", \"strength\":\"500 mg\" }] }] }] }], \"labs\":[{ \"missing_field\": \"missing_value\" }] }], \"Asthma\":[{}] }]}";
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}

	/**
	 * Test to Convert long length json string to xml
	 */
	@Test
	public void toXml11() {
		json = FileUtil.readAsString("long.json");
		String xmlString = convert.toXml(json);
		assertNotNull(xmlString);
		assertNotEquals(xmlString, "");
		assertEquals(true, ValidateUtil.isValidateXml(xmlString));
	}
}