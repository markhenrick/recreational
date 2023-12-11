package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StatUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.List;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

/**
 * Alternative implementation of day 9 that generates a coefficient vector first, then computes the dot at the end.
 * This is pointless at first, but the idea is to refine the generation until hopefully it's a closed form function of the list size, if that's possible.
 * I'm still working out the algebra on paper
 *
 * At the very least this is theoretically superior for large inputs where |unique list sizes| < |lists|, since you can cache vectors for given n's
 */
public class Day09Alt {
	// TODO this would be enhanced by that TODO in IntVec2/3 about generalising it into IntVecN

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
			.map(input1 -> StringUtil.spaceSeparatedInts(input1).boxed().toList())
			.map(FunctionalUtil.zipApply(List::size))
			.map(FunctionalUtil.Pair.rightMapper(Day09Alt::getCoefficients))
			.map(FunctionalUtil.Pair.curry(Day09Alt::dotProduct))
			.mapToInt(x -> x)
			.sum();
	}

	static List<Integer> getCoefficients(int n) {
		val pascal = StatUtil.pascalRow(n).limit(n);
		val signs = IntStream.iterate(1, x -> -x).skip(n % 2 + 1);
		// TODO zipWith(IntStream)
		return FunctionalUtil.zipWith((x, y) -> x * y, pascal.boxed(), signs.boxed())
			.toList();
	}

	private static int dotProduct(List<Integer> a, List<Integer> b) {
		assert a.size() == b.size();
		return FunctionalUtil.zipWith((x, y) -> x * y, a.stream(), b.stream())
			.mapToInt(x -> x)
			.sum();
	}
}
