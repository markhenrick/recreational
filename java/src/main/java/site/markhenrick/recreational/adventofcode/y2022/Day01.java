package site.markhenrick.recreational.adventofcode.y2022;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.RECORD_SPLITTER;

public class Day01
{
	public static long part1(String input) {
		return RECORD_SPLITTER.apply(input)
				.mapToLong(elf -> LINE_SPLITTER.apply(elf)
						.mapToInt(Integer::parseInt)
						.sum()
				)
				.max()
				.orElse(0L);
	}
}
