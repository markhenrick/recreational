package site.markhenrick.adventofcode2020;

import site.markhenrick.adventofcode2020.common.MiscUtil;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class Day05 {
	private static final Pattern SEAT_PATTERN = Pattern.compile("^[FB]{7}[LR]{3}$");
	@SuppressWarnings("SpellCheckingInspection")
	static final Comparator<String> SEAT_COMPARATOR = lexicographicComparator("FBLR");
	private static final Function<String, Integer> SEAT_DECODER = binaryDecoder("BR");

	private static Comparator<String> lexicographicComparator(final String alphabetString) {
		final var alphabet = asCharList(alphabetString);
		return (l, r) -> {
			assert l.length() == r.length();
			for (var i = 0; i < l.length(); i++) {
				final var lVal = l.charAt(i);
				final var rVal = r.charAt(i);
				assert alphabet.contains(lVal);
				assert alphabet.contains(rVal);
				if (lVal != rVal) {
					return alphabet.indexOf(lVal) - alphabet.indexOf(rVal);
				}
			}
			return 0;
		};
	}

	private static Function<String, Integer> binaryDecoder(final String onesString) {
		final var ones = asCharList(onesString);
		return input -> IntStream.range(0, input.length())
			.filter(i -> ones.contains(input.charAt(i)))
			.map(i -> 1 << (input.length() - i - 1))
			.sum();
	}

	static int decodeSeat(final String seat) {
		assert SEAT_PATTERN.matcher(seat).matches();
		return SEAT_DECODER.apply(seat);
	}

	static int solvePart1(final String input) {
		//noinspection OptionalGetWithoutIsPresent
		return MiscUtil.lines(input).stream()
			.max(SEAT_COMPARATOR)
			.map(Day05::decodeSeat)
			.get();
	}

	/** Because Arrays.asList doesn't work on primitives */
	private static List<Character> asCharList(final String string) {
		final List<Character> list = new ArrayList<>(string.length());
		for (final var character : string.toCharArray()) {
			list.add(character);
		}
		return list;
	}
}
