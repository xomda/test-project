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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class JteTest {

	@Test
	public void testJteJsonOutput() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		Path outputJson = Paths.get("build", "test-jte", "output.json");
		assertTrue(Files.exists(outputJson));

		Map<?, ?> json = mapper.readValue(outputJson.toFile(), Map.class);

		assertNotNull(json);
		assertFalse(json.isEmpty());

		assertEquals("XOMDA", json.get("name"));
	}

}
