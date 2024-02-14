package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;

public class SimpleTemplateTest {

	@Test
	public void testTemplate3() throws IOException {
		Map<String, String> testMap = Map.of(
				"package.json", "Package",
				"entity.json", "Entity",
				"attribute.json", "Attribute",
				"enum.json", "Enum",
				"value.json", "Value"
		);

		// we need to support trailing comma's because we didn't care to avoid them
		JsonFactory jsonFactory = JsonFactory.builder()
				.enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
				.build();
		ObjectMapper mapper = JsonMapper.builder(jsonFactory).build();

		testMap.forEach((filename, name) -> {
			Path outputJson = Paths.get("build", "test-templates", filename);
			assertTrue(Files.exists(outputJson));

			Map<?, ?> json = null;
			try {
				json = mapper.readValue(outputJson.toFile(), Map.class);
			} catch (IOException e) {
				throw new AssertionError("Failed to read '" + filename + "'.");
			}

			assertNotNull(json);
			assertFalse(json.isEmpty());
			assertNotNull(json.get("name"));
			assertEquals(name, json.get("name"));
		});

	}

}
