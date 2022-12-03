package site.markhenrick.recreational.adventofcode.y2022;

import site.markhenrick.recreational.common.data.TopKCollector;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.RECORD_SPLITTER;

public class Day01 {
	private static int topKElfSum(String input, int k) {
		return RECORD_SPLITTER.apply(input)
			.map(elf -> LINE_SPLITTER.apply(elf)
				.mapToInt(Integer::parseInt)
				.sum()
			)
			.collect(new TopKCollector<>(k, Integer::compareTo))
			.stream()
			.reduce(0, Integer::sum);
	}

	public static int part1(String input) {
		return topKElfSum(input, 1);
	}

	public static int part2(String input) {
		return topKElfSum(input, 3);
	}
}
