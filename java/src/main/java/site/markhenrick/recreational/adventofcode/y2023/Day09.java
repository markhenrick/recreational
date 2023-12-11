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
	// TODO port part 2 to Day09Alt then delet this

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
