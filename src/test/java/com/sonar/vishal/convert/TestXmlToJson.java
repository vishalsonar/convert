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
public class TestXmlToJson {

	private String xml;
	private Convert convert;

	public TestXmlToJson() {
		convert = new Convert();
	}

	/**
	 * Test null condition, empty string and other condition
	 */
	@Test
	public void toJson() {
		String jsonString = convert.toJson(null);
		assertNull(jsonString);
		jsonString = convert.toJson("");
		assertNull(jsonString);
		jsonString = convert.toJson("</document>");
		assertNull(jsonString);
		jsonString = convert.toJson("<?xml version=\"1.0\"?>");
		assertNull(jsonString);
		jsonString = convert.toJson("<?xml version=\"1.0\" encoding=\"UTF-8\"?></note>");
		assertNull(jsonString);
		jsonString = convert.toJson("<?xml version=\"1.0\"?><students></students>");
		assertNotNull(jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test simple xml string.
	 */
	@Test
	public void toJson0() {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <note> <to>Tove</to> <from>Jani</from> <heading>Reminder</heading> <body>Don't forget me this weekend!</body> </note> ";
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test xml with empty tag
	 */
	@Test
	public void toJson1() {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><note></note>";
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test xml with inner empty tag
	 */
	@Test
	public void toJson2() {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <note><test/></note>";
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
		xml = "<feeds> <id>2140</id> <title>gj</title> <description>ghj</description> <location>Hermannplatz 5-6, 10967 Berlin, Germany</location> <lng>0</lng> <lat>0</lat> <userId>4051</userId> <name>manoj</name> <isdeleted>false</isdeleted> <profilePicture>Images/9b291404-bc2e-4806-88c5-08d29e65a5ad.png</profilePicture> <videoUrl /> <images /> <mediatype>0</mediatype> <imagePaths /> <feedsComment /> <commentCount>0</commentCount> <multiMedia> <id>3240</id> <name /> <description /> <url>http://www.youtube.com/embed/mPhboJR0Llc</url> <mediatype>2</mediatype> <likeCount>0</likeCount> <place /> <createAt>0001-01-01T00:00:00</createAt> </multiMedia> <likeDislike> <likes>0</likes> <dislikes>0</dislikes> <userAction>2</userAction> </likeDislike> <createdAt>2020-01-02T13:32:16.7480006</createdAt> <code>0</code> <msg /> </feeds>";
		jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test xml containing array
	 */
	@Test
	public void toJson3() {
		xml = "<bookstore> <book category=\"COOKING\"> <title lang=\"en\">Everyday Italian</title> <author>Giada De Laurentiis</author> <year>2005</year> <price>30.00</price> </book> <book category=\"CHILDREN\"> <title lang=\"en\">Harry Potter</title> <author>J K. Rowling</author> <year>2005</year> <price>29.99</price> </book> <book category=\"WEB\"> <title lang=\"en\">Learning XML</title> <author>Erik T. Ray</author> <year>2003</year> <price>39.95</price> </book> </bookstore>";
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
		xml = "<breakfast_menu> <food> <name>Belgian Waffles</name> <price>$5.95</price> <description>Two of our famous Belgian Waffles with plenty of real maple syrup</description> <calories>650</calories> </food> <food> <name>Strawberry Belgian Waffles</name> <price>$7.95</price> <description>Light Belgian waffles covered with strawberries and whipped cream</description> <calories>900</calories> </food> <food> <name>Berry-Berry Belgian Waffles</name> <price>$8.95</price> <description>Light Belgian waffles covered with an assortment of fresh berries and whipped cream</description> <calories>900</calories> </food> <food> <name>French Toast</name> <price>$4.50</price> <description>Thick slices made from our homemade sourdough bread</description> <calories>600</calories> </food> <food> <name>Homestyle Breakfast</name> <price>$6.95</price> <description>Two eggs, bacon or sausage, toast, and our ever-popular hash browns</description> <calories>950</calories> </food> </breakfast_menu>";
		jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test xml containing multiple array
	 */
	@Test
	public void toJson4() {
		xml = FileUtil.readAsString("data.xml");
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}

	/**
	 * Test complex xml
	 */
	@Test
	public void toJson5() {
		xml = FileUtil.readAsString("data1.xml");
		String jsonString = convert.toJson(xml);
		assertNotNull(jsonString);
		assertNotEquals("", jsonString);
		assertEquals(true, ValidateUtil.isValidJson(jsonString));
	}
}