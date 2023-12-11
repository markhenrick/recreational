package site.markhenrick.recreational.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.FunctionalUtil.negate;

@UtilityClass
public class StringUtil {
	public static final Function<String, Stream<String>> LINE_SPLITTER = splitToStream("\n");
	public static final Function<String, Stream<String>> RECORD_SPLITTER = splitToStream("\n\n");
	public static final Function<String, Stream<String>> WORD_SPLITTER = splitToStream(" ");
	public static final Function<String, Stream<String>> COMMA_SPLITTER = splitToStream(",");
	public static final Function<String, Stream<String>> SEMICOLON_SPLITTER = splitToStream("; ");

	public static Function<String, Stream<String>> splitToStream(final String regex) {
		final var pattern = Pattern.compile(regex);
		return input -> Arrays.stream(pattern.split(input)).filter(negate(String::isBlank));
	}

	public static Stream<Character> charStream(CharSequence input) {
		return IntStream.range(0, input.length())
			.mapToObj(input::charAt);
	}

	public static IntStream spaceSeparatedInts(String input) {
		return input.isBlank() ? IntStream.empty() : WORD_SPLITTER.apply(input)
			.mapToInt(Integer::parseInt);
	}

	// like String.substring().equals() but without all the allocation
	public static boolean substringMatches(String input, int offset, String expected) {
		if (input.length() - offset < expected.length()) {
			return false;
		}
		// Also not using streams to avoid allocation
		for (int i = 0; i < expected.length(); i++) {
			if (input.charAt(i + offset) != expected.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public static int getSingleIndexOf(String input, char character) {
		val index = input.indexOf(character);
		assert index != -1;
		assert input.lastIndexOf(character) == index;
		return index;
	}

	public class StringCollector implements Collector<Character, StringBuilder, String> {
		@Override
		public Supplier<StringBuilder> supplier() {
			return StringBuilder::new;
		}

		@Override
		public BiConsumer<StringBuilder, Character> accumulator() {
			return StringBuilder::append;
		}

		@Override
		public BinaryOperator<StringBuilder> combiner() {
			return StringBuilder::append;
		}

		@Override
		public Function<StringBuilder, String> finisher() {
			return StringBuilder::toString;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.emptySet();
		}
	}
}
