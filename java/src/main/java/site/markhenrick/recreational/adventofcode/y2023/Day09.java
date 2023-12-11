package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day09 {
	/*
	 TODO probably a more efficient way than the method described in the problem descr
	 i.e. not O(n^2)

	 Recurrence relations:
	 L_0 = input list, |L_0| = n
	 L_{i, j} = L_{i-1, j+1} - L_{i-1, j}
	 |L_i| = |L_{i-1}|-1 = n-i
	 |L| = n, with the last list being a singleton
	 L_{i, -1} = L_{i, |L_i|-1} = L_{i, n-i-1}
	 forward extrapolation, L_{0, n} = sum_{i = 0..n-1} L_{i, -1} = sum_{i = 0..n-1} L_{i, n-i-1}
	 	= L_{n-1, 0} + L_{n-2, 1} + L_{n-3, 3} ... L_{0, n-1}
	 	= (L_{n-2, 1} - L_{n-2, 0}) + L_{n-2, 1} + L_{n-3, 3} ... L_{0, n-1}
	 	= 2L_{n-2, 1} - L_{n-2, 0} + L_{n-3, 3} ... L_{0, n-1}
	 	= 2(L_{n-3, 2} - L_{n-3, 1}) - (L_{n-3, 1} - L_{n-3, 0}) + L_{n-3, 3} ... L_{0, n-1}
	 	= 2L_{n-3, 2} - 3L_{n-3, 1} + L_{n-3, 0} + L_{n-3, 3} ... L_{0, n-1}
	 Can we work out the vector to multiply L_0 by, when we reach L_{n-n}?
	*/

	static int part1(String input) {
		return extrapolationSum(input, Day09::extrapolateForwards);
	}

	static int part2(String input) {
		return extrapolationSum(input, Day09::extrapolateBackwards);
	}

	static int extrapolationSum(String input, ToIntFunction<Stream<List<Integer>>> extrapolator) {
		return LINE_SPLITTER.apply(input)
			.map(input1 -> StringUtil.spaceSeparatedInts(input1).boxed().toList())
			.map(Day09::recursiveDeltas)
			.mapToInt(extrapolator)
			.sum();
	}

	static int extrapolateForwards(Stream<List<Integer>> triangle) {
		return triangle
			.mapToInt(list -> list.get(list.size() - 1))
			.sum();
	}

	static int extrapolateBackwards(Stream<List<Integer>> triangle) {
		val alternatingSigns = IntStream.iterate(1, x -> -x).boxed();
		val firstColumn = triangle.map(list -> list.get(0));
		return FunctionalUtil.zipWith((x, y) -> x * y, alternatingSigns, firstColumn)
			.mapToInt(x -> x)
			.sum();
	}

	static Stream<List<Integer>> recursiveDeltas(List<Integer> input) {
		return Stream.iterate(input, Day09::listNonZero, Day09::deltas);
	}

	private static boolean listNonZero(List<Integer> list) {
		return !list.isEmpty() && !list.stream().allMatch(x -> x.equals(0));
	}

	static List<Integer> deltas(List<Integer> input) {
		return FunctionalUtil.zipWith((x, y) -> x - y, input.stream().skip(1), input.stream())
			.toList();
	}

}
