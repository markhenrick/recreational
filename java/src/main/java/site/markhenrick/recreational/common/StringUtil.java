package site.markhenrick.recreational.common;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringUtil {
	public static final Function<String, Stream<String>> LINE_SPLITTER = splitToStream("\n");
	public static final Function<String, Stream<String>> RECORD_SPLITTER = splitToStream("\n\n");
	public static final Function<String, Stream<String>> WORD_SPLITTER = splitToStream(" ");
	public static final Function<String, Stream<String>> COMMA_SPLITTER = splitToStream(",");

	public static Function<String, Stream<String>> splitToStream(final String regex) {
		final var pattern = Pattern.compile(regex);
		return input -> Arrays.stream(pattern.split(input));
	}

	public static Stream<Character> charStream(final CharSequence input) {
		return IntStream.range(0, input.length())
			.mapToObj(input::charAt);
	}

}
