package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;

public class MustacheTest {

	@Test
	public void testMustacheJsonOutput() throws IOException {
		// we need to support trailing comma's because we didn't care to avoid them
		JsonFactory jsonFactory = JsonFactory.builder()
				.enable(JsonReadFeature.ALLOW_TRAILING_COMMA)
				.build();
		ObjectMapper mapper = JsonMapper.builder(jsonFactory).build();

		Path outputJson = Paths.get("build", "test-mustache", "output.json");
		assertTrue(Files.exists(outputJson));

		List<?> json = mapper.readValue(outputJson.toFile(), List.class);

		assertNotNull(json);
		assertFalse(json.isEmpty());
		assertNotNull(json.get(0));

		Map<?, ?> pkg = (Map<?, ?>) json.get(0);

		assertEquals("Core", pkg.get("name"));
		assertFalse(((List<?>) pkg.get("entityList")).isEmpty());
	}

}
