import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class GeneratedModelTest {

	private static final List<String> expected = List.of(
			"Test|Test Entity 1|Attribute 12[String]|Attribute 13[String]",
			"Test|Test Entity 2|Attribute 22[String]|Attribute 23[String]"
	);

	@Test
	public void testTemplateResult() throws Exception {
		Path testData = Paths
				.get("build", "test-model-data", "templateTest", "test-data.txt")
				.toAbsolutePath();
		try (
				FileReader reader = new FileReader(testData.toFile());
				BufferedReader br = new BufferedReader(reader);
				Stream<String> lines = br.lines()
		) {
			List<String> lst = lines.toList();
			assertEquals(expected.size(), lst.size());
			expected.forEach(e -> assertEquals(e, lst.get(expected.indexOf(e))));
		}
	}

}
