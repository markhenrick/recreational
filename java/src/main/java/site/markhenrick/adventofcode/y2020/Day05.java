package site.markhenrick.adventofcode.y2020;

import java.util.BitSet;
import java.util.Comparator;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static site.markhenrick.adventofcode.common.StringUtil.LINE_SPLITTER;

// NB: This day's solution in particular is deliberately more complicated than it needs to be, to make it more fun. See the README
class Day05 {
	private static final Pattern SEAT_PATTERN = Pattern.compile("^[FB]{7}[LR]{3}$");
	@SuppressWarnings("SpellCheckingInspection")
	static final Comparator<String> SEAT_COMPARATOR = lexicographicComparator("FBLR");
	private static final Function<String, Short> SEAT_DECODER = binaryShortDecoder("BR");

	private static Comparator<String> lexicographicComparator(final String alphabet) {
		return (l, r) -> {
			assert l.length() == r.length();
			for (var i = 0; i < l.length(); i++) {
				final var lVal = l.charAt(i);
				final var rVal = r.charAt(i);
				assert alphabet.indexOf(lVal) != -1;
				assert alphabet.indexOf(rVal) != -1;
				if (lVal != rVal) {
					return alphabet.indexOf(lVal) - alphabet.indexOf(rVal);
				}
			}
			return 0;
		};
	}

	private static Function<String, Short> binaryShortDecoder(final String ones) {
		return input -> {
			assert input.length() <= 16;
			return (short) IntStream
				.range(0, input.length())
				.filter(i -> ones.indexOf(input.charAt(i)) != -1)
				.map(i -> (short) (1 << (input.length() - i - 1)))
				.reduce(0, (a, b) -> a | b);
		};
	}

	static int decodeSeat(final String seat) {
		assert SEAT_PATTERN.matcher(seat).matches();
		return SEAT_DECODER.apply(seat);
	}

	static int solvePart1(final String input) {
		//noinspection OptionalGetWithoutIsPresent
		return LINE_SPLITTER.apply(input)
			.max(SEAT_COMPARATOR)
			.map(Day05::decodeSeat)
			.get();
	}

	static BitSet listSeats(final String input) {
		final var seats = new BitSet();
		LINE_SPLITTER.apply(input)
			.map(Day05::decodeSeat)
			.forEach(seats::set);
		return seats;
	}

	static int findMySeat(final BitSet seats) {
		return seats.nextClearBit(seats.nextSetBit(0));
	}

	static int solvePart2(final String input) {
		return findMySeat(listSeats(input));
	}
}
