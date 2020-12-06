package site.markhenrick.adventofcode.common;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MiscUtil {
	public static final Function<String, Stream<String>> LINE_SPLITTER = splitToStream("\n");
	public static final Function<String, Stream<String>> RECORD_SPLITTER = splitToStream("\n\n");

	static Function<String, Stream<String>> splitToStream(final String regex) {
		final var pattern = Pattern.compile(regex);
		return input -> Arrays.stream(pattern.split(input));
	}

	// Probably in the stdlib somewhere
	public static <A> Predicate<A> constantPredicate(final boolean value) {
		return a -> value;
	}

	public static <A> BinaryOperator<A> applyAndReturnLeft(final BiConsumer<? super A, ? super A> mutatingOperator) {
		return (l, r) -> {
			mutatingOperator.accept(l, r);
			return l;
		};
	}
}
