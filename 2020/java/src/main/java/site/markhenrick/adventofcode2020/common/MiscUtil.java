package site.markhenrick.adventofcode2020.common;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MiscUtil {
	public static final Function<String, Stream<String>> LINE_SPLITTER = splitToStream("\n");
	public static final Function<String, Stream<String>> RECORD_SPLITTER = splitToStream("\n\n");

	static Function<String, Stream<String>> splitToStream(final String regex) {
		final var pattern = Pattern.compile(regex);
		return input -> Arrays.stream(pattern.split(input));
	}
}
