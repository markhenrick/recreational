package site.markhenrick.recreational.common;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtil {

	private static final Pattern WINDOWS_NEWLINE = Pattern.compile("\r\n");

	public static String getResourceAsString(final String filename) {
		final var resource = TestUtil.class.getClassLoader().getResource(filename);
		assertThat(resource).isNotNull();
		try {
			return dos2unix(Files.readString(Paths.get(resource.toURI())));
		} catch (final IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	public static String dos2unix(final CharSequence input) {
		return WINDOWS_NEWLINE.matcher(input).replaceAll("\n");
	}
}
