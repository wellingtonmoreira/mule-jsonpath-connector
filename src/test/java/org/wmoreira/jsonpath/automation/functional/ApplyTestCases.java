package org.wmoreira.jsonpath.automation.functional;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;
import org.wmoreira.jsonpath.JsonpathConnector;
import org.wmoreira.jsonpath.exception.InvalidJsonPathException;

public class ApplyTestCases extends AbstractTestCase<JsonpathConnector> {

	public ApplyTestCases() {
		super(JsonpathConnector.class);
	}

	@Before
	public void setup() {
		// TODO
	}

	@After
	public void tearDown() {
		// TODO
	}

	public String fileContents(final String file) throws IOException {
		return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(file));
	}

	@Test
	public void testApplySuccess() throws IOException {
		assertEquals(fileContents("cases/test1-expected.json"),getConnector()
				.apply(fileContents("cases/test1-input.json"), "$.customers[*].deliveryAddresses[*]"));
		
		assertEquals(fileContents("cases/test2-expected.json"),getConnector()
				.apply(fileContents("cases/test2-input.json"), "$"));
		
		assertEquals(fileContents("cases/test3-expected.json"),getConnector()
				.apply(fileContents("cases/test3-input.json"), "$.customers"));
		
		assertEquals(fileContents("cases/test4-expected.json"),getConnector()
				.apply(fileContents("cases/test4-input.json"), "$.customers[*].deliveryAddresses[*].value"));
	}
	
	@Test
	public void testInvalidJson() {
		assertEquals("<html></html>", getConnector().apply("<html></html>", "$"));
		assertEquals("AAA123", getConnector().apply("AAA123", "$"));
		assertEquals("This is definitely not a Json", getConnector().apply("This is definitely not a Json", "$"));
	}
	
	@Test(expected = InvalidJsonPathException.class)
	public void testInvalidJsonpath() {
		getConnector().apply("{}", "$...");
	}
	
	@Test(expected = InvalidJsonPathException.class)
	public void testInvalidJsonpath2() {
		getConnector().apply("{}", "X");
	}
}