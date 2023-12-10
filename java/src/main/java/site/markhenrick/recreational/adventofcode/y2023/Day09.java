package site.markhenrick.recreational.adventofcode.y2023;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day09 {
	// TODO probably a more efficient way than the method described in the problem descr
	// i.e. not O(n^2)

	static int part1(String input) {
		return LINE_SPLITTER.apply(input)
			.map(Day09::parseLine)
			.map(Day09::recursiveDeltas)
			.mapToInt(Day09::extrapolate)
			.sum();
	}

	static int extrapolate(Stream<List<Integer>> triangle) {
		return triangle
			.mapToInt(list -> list.get(list.size() - 1))
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
