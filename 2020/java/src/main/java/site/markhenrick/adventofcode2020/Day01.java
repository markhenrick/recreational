package site.markhenrick.adventofcode2020;

import java.util.BitSet;

class Day01 {
	private static final int SUM = 2020;

	static int part1(final Iterable<Integer> numbers) {
		final var seen = new BitSet(SUM + 1);
		for (final var number : numbers) {
			final var complement = SUM - number;
			if (seen.get(complement)) {
				return number * complement;
			} else {
				seen.set(number);
			}
		}
		throw new AssertionError("No solution found");
	}
}
