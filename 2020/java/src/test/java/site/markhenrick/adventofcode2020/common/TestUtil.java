package site.markhenrick.adventofcode2020.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {
	public static String getResourceAsString(final String filename) {
		final var resource = TestUtil.class.getClassLoader().getResource(filename);
		assertThat(resource).isNotNull();
		try {
			return Files.readString(Paths.get(resource.toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
