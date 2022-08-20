package site.markhenrick.recreational.adventofcode.y2015;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Day02 {
	private static final Pattern INPUT_PATTERN = Pattern.compile("(\\d+)x(\\d+)x(\\d+)");
	static final Function<CharSequence, Long> SOLVE_PART_1 = reducePresents(Day02::surfaceArea);
	static final Function<CharSequence, Long> SOLVE_PART_2 = reducePresents(Day02::ribbonRequired);

	// This one will be particularly nice to do in Haskell when I get round to that

	static long surfaceArea(final List<Integer> sides) {
		// zipWith pls!!
		final var stats = IntStream.range(0, sides.size())
			.map(i -> sides.get(i) * sides.get((i + 1) % sides.size()))
			.summaryStatistics();
		return 2 * stats.getSum() + stats.getMin();
	}

	private static int shortestPerimeter(final Iterable<Integer> sides) {
		var firstSmallest = Integer.MAX_VALUE;
		var secondSmallest = Integer.MAX_VALUE;
		for (final var side : sides) {
			if (side <= firstSmallest) {
				secondSmallest = firstSmallest;
				firstSmallest = side;
			} else if (side < secondSmallest) {
				secondSmallest = side;
			}
		}
		return 2 * (firstSmallest + secondSmallest);
	}

	private static int volume(final Collection<Integer> sides) {
		return sides.stream().reduce(1, (l, r) -> l * r);
	}

	static long ribbonRequired(final Collection<Integer> sides) {
		return shortestPerimeter(sides) + volume(sides);
	}

	private static Function<CharSequence, Long> reducePresents(final Function<? super List<Integer>, Long> sideReducer) {
		return input -> {
			final var matches = INPUT_PATTERN.matcher(input);
			var total = 0L;
			while (matches.find()) {
				assert matches.groupCount() == 3;
				final var sides = IntStream.range(1, matches.groupCount() + 1)
					.mapToObj(matches::group)
					.map(Integer::parseInt)
					.collect(Collectors.toList());
				total += sideReducer.apply(sides);
			}
			return total;
		};
	}
}
