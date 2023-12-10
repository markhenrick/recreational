package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;

import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day09 {
	// TODO probably a more efficient way than the method described in the problem descr
	// i.e. not O(n^2)

	static int part1(String input) {
		return extrapolationSum(input, Day09::extrapolateForwards);
	}

	static int part2(String input) {
		return extrapolationSum(input, Day09::extrapolateBackwards);
	}

	static int extrapolationSum(String input, ToIntFunction<Stream<List<Integer>>> extrapolator) {
		return LINE_SPLITTER.apply(input)
			.map(Day09::parseLine)
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
		// TODO. Of all the functions that need zip/with, this one needs it the most
		// Zip with IntStream.iterate(1, Integer::negate)
		val list = triangle.toList();
		return IntStream.range(0, list.size())
			.map(i -> (i % 2 == 0 ? 1 : -1) * list.get(i).get(0))
			.sum();
	}

	static Stream<List<Integer>> recursiveDeltas(List<Integer> input) {
		return Stream.iterate(input, Day09::listNonZero, Day09::deltas);
	}

	private static boolean listNonZero(List<Integer> list) {
		return !list.isEmpty() && !list.stream().allMatch(x -> x.equals(0));
	}

	static List<Integer> deltas(List<Integer> input) {
		// TODO another one that will be easier when I finish zip/with
		return IntStream.range(0, input.size() - 1)
			.map(i -> input.get(i + 1) - input.get(i))
			.boxed()
			.toList();
	}

	static List<Integer> parseLine(String input) {
		return input.isBlank() ? Collections.emptyList() : WORD_SPLITTER.apply(input)
			.map(Integer::parseInt)
			.toList();
	}
}
