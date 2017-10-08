package learning;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathTest {
	@Test
	public void testJsonPathLib() throws IOException {
		System.out.println(JsonPath
				.parse(IOUtils.toString(getClass().getClassLoader().getResourceAsStream("cases/test1-input.json")))
				.read("$.customers[*].deliveryAddresses[*]"));
	}
}
