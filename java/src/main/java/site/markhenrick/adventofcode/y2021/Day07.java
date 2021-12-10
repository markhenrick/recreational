package site.markhenrick.adventofcode.y2021;

import site.markhenrick.adventofcode.common.StatUtil;
import site.markhenrick.adventofcode.common.StringUtil;

import java.util.Arrays;

public class Day07 {
	static long[] parse(String input) {
		return StringUtil.COMMA_SPLITTER.apply(input.trim())
			.mapToLong(Long::parseLong)
			.toArray();
	}

	static long part1(long[] input) {
		var optimalPosition = StatUtil.median(input);
		return Arrays.stream(input)
			.map(x -> StatUtil.distance(x, optimalPosition))
			.sum();
	}

	static long part2(long[] input) {
		var optimalPosition = StatUtil.mean(input);
		var floor = (int) Math.floor(optimalPosition);
		var ceil = (int) Math.ceil(optimalPosition);
		return floor != ceil ? Math.min(totalCostP2(input, floor), totalCostP2(input, ceil)) : totalCostP2(input, ceil);
	}

	static long totalCostP2(long[] input, int position) {
		return Arrays.stream(input)
			.map(x -> StatUtil.distance(x, position))
			.map(StatUtil::intSummation)
			.sum();
	}

}
