package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StatUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.List;
import java.util.function.Function;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day09 {
	// TODO this would be enhanced by that TODO in IntVec2/3 about generalising it into IntVecN

	public static long part1(String input) {
		return extrapolate(Day09::getCoefficientsP1, input);
	}

	public static long part2(String input) {
		return extrapolate(Day09::getCoefficientsP2, input);
	}

	public static long extrapolate(Function<Integer, List<Long>> coefficientGenerator, String input) {
		// TODO so there's a hidden constraint here - every row has the same length (in terms of numbers). So I only need to find the coefficients once
		return LINE_SPLITTER.apply(input)
			.map(input1 -> StringUtil.spaceSeparatedInts(input1).mapToLong(x -> x).boxed().toList())
			.map(FunctionalUtil.zipApply(List::size))
			.map(FunctionalUtil.Pair.rightMapper(coefficientGenerator))
			.map(FunctionalUtil.Pair.curry(Day09::dotProduct))
			.mapToLong(x -> x)
			.sum();
	}

	static List<Long> getCoefficientsP1(int n) {
		val pascal = StatUtil.pascalRow(n).limit(n);
		val signs = StatUtil.alternatingSigns().skip(n % 2 + 1);
		// TODO zipWith(IntStream)
		return FunctionalUtil.zipWith((x, y) -> x * y, pascal.boxed(), signs.boxed())
			.toList();
	}

	static List<Long> getCoefficientsP2(int n) {
		val pascal = StatUtil.pascalRow(n).skip(1).limit(n);
		val signs = StatUtil.alternatingSigns();
		return FunctionalUtil.zipWith((x, y) -> x * y, pascal.boxed(), signs.boxed())
			.toList();
	}

	private static long dotProduct(List<Long> a, List<Long> b) {
		assert a.size() == b.size();
		return FunctionalUtil.zipWith((x, y) -> x * y, a.stream(), b.stream())
			.mapToLong(x -> x)
			.sum();
	}
}
