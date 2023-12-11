package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.FunctionalUtil.negate;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

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
			.map(Day09Alt::parseLine)
			.mapToInt(Day09Alt::part1OneLine)
			.sum();
	}

	public static int part1OneLine(List<Integer> numbers) {
		val n = numbers.size();
		val initial = createInitialSequence(n);
		val triangle = recursiveDeltas(initial);
		val coefficients = extrapolateForwards(triangle);
		return dotProduct(numbers, coefficients);
	}

	private static List<List<Integer>> createInitialSequence(int n) {
		return IntStream.range(0, n)
			.mapToObj(i -> unitVector(n, i))
			.toList();
	}

	private static List<Integer> extrapolateForwards(Stream<List<List<Integer>>> triangle) {
		return triangle
			.map(list -> list.get(list.size() - 1))
			.reduce(Day09Alt::add)
			.get();
	}

	private static Stream<List<List<Integer>>> recursiveDeltas(List<List<Integer>> sequence) {
		return Stream.iterate(sequence, negate(List::isEmpty), Day09Alt::delta);
	}

	private static List<List<Integer>> delta(List<List<Integer>> sequence) {
		return FunctionalUtil.zipWith(Day09Alt::subtract, sequence.stream().skip(1), sequence.stream())
			.toList();
	}

	private static List<Integer> unitVector(int order, int dimension) {
		return IntStream.range(0, order)
			.map(i -> i == dimension ? 1 : 0)
			.boxed()
			.toList();
	}

	// Simpler to just have these two rather than implementing and composing -1*
	private static List<Integer> add(List<Integer> a, List<Integer> b) {
		assert a.size() == b.size();
		return FunctionalUtil.zipWith(Integer::sum, a.stream(), b.stream())
			.collect(Collectors.toList());
	}

	private static List<Integer> subtract(List<Integer> a, List<Integer> b) {
		assert a.size() == b.size();
		return FunctionalUtil.zipWith((x, y) -> x - y, a.stream(), b.stream())
			.collect(Collectors.toList());
	}

	private static int dotProduct(List<Integer> a, List<Integer> b) {
		assert a.size() == b.size();
		return FunctionalUtil.zipWith((x, y) -> x * y, a.stream(), b.stream())
			.mapToInt(x -> x)
			.sum();
	}

	static List<Integer> parseLine(String input) {
		return input.isBlank() ? Collections.emptyList() : WORD_SPLITTER.apply(input)
			.map(Integer::parseInt)
			.toList();
	}
}
