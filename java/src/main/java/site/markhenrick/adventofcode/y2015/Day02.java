package site.markhenrick.adventofcode.y2015;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Day02 {
	private static final Pattern INPUT_PATTERN = Pattern.compile("(\\d+)x(\\d+)x(\\d+)");

	// This one will be particularly nice to do in Haskell when I get round to that

	static long surfaceArea(final List<Integer> sides) {
		// zipWith pls!!
		final var stats = IntStream.range(0, sides.size())
			.map(i -> sides.get(i) * sides.get((i + 1) % sides.size()))
			.summaryStatistics();
		return 2 * stats.getSum() + stats.getMin();
	}

	static long solvePart1(final CharSequence input) {
		final var matches = INPUT_PATTERN.matcher(input);
		var total = 0;
		while (matches.find()) {
			assert matches.groupCount() == 3;
			final var sides = IntStream.range(1, matches.groupCount() + 1)
				.mapToObj(matches::group)
				.map(Integer::parseInt)
				.collect(Collectors.toList());
			total += surfaceArea(sides);
		}
		return total;
	}
}
