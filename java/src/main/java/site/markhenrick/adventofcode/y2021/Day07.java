package site.markhenrick.adventofcode.y2021;

import site.markhenrick.adventofcode.common.StringUtil;

import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Day07 {
	static List<Integer> parse(String input) {
		return StringUtil.COMMA_SPLITTER.apply(input.trim())
				.map(Integer::parseInt)
				.toList();
	}

	static int solve(List<Integer> input, IntBinaryOperator crabCost) {
		var max = input.stream().max(Integer::compare).orElseThrow();
		return IntStream.range(0, max + 1)
				.map(p -> input.stream()
						.mapToInt(x -> crabCost.applyAsInt(p, x))
						.sum()
				)
				.min()
				.orElseThrow();
	}

	static int part1(List<Integer> input) {
		return solve(input, Day07::distance);
	}

	static int part2(List<Integer> input) {
		return solve(input, (p, x) -> intSummation(distance(p, x)));
	}

	static int distance(int a, int b) {
		return Math.abs(a - b);
	}

	static int intSummation(int limitInclusive) {
		return limitInclusive * (limitInclusive + 1) / 2;
	}
}
