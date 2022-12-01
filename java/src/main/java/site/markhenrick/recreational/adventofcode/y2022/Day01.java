package site.markhenrick.recreational.adventofcode.y2022;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.RECORD_SPLITTER;

import java.util.stream.LongStream;

public class Day01
{
	private static LongStream parseElves(String input) {
		return RECORD_SPLITTER.apply(input)
				.mapToLong(elf -> LINE_SPLITTER.apply(elf)
						.mapToInt(Integer::parseInt)
						.sum()
				);
	}

	public static long part1(String input) {
		return parseElves(input)
				.max()
				.orElse(0L);
	}

	public static long part2(String input) {
		// TODO write a custom Collector or reducer that doesn't do a full sort
		return -parseElves(input)
				.map(number -> -number) // Stupid hack to reverse sort. No method that lets me pass a custom Comparator, and this is probably faster than boxing then unboxing
				.sorted()
				.limit(3)
				.sum();
	}
}
