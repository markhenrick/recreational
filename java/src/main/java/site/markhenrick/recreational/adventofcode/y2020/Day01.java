package site.markhenrick.recreational.adventofcode.y2020;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	static int part2(final List<Integer> numbers) {
		final var sumsToProducts = computeTable(numbers);
		for (final var number : numbers) {
			final var complement = SUM - number;
			final var twoProduct = sumsToProducts.get(complement);
			if (twoProduct != null) {
				return number * twoProduct;
			}
		}
		throw new AssertionError("No solution found");
	}

	private static Map<Integer, Integer> computeTable(final List<Integer> numbers) {
		final Map<Integer, Integer> sumsToProducts = new HashMap<>();
		for (var i = 0; i < numbers.size(); i++) {
			final var iVal = numbers.get(i);
			for (var j = i + 1; j < numbers.size(); j++) {
				final var jVal = numbers.get(j);
				sumsToProducts.put(iVal + jVal, iVal * jVal);
			}
		}
		return sumsToProducts;
	}
}
