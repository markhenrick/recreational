package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day01 {
	// Decided to go with a nice streams approach rather than a faster but ugly state machine
	// time and space should only differ by a constant factor :)

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.mapToInt(Day01::processOneLine)
				.sum();
	}

	static int processOneLine(String line) {
		// TODO custom collector so I'm not allocating a whole list just to get [0] and [-1]
		val digits = StringUtil.charStream(line)
				.map(character -> character - '0')
				.filter(digit -> digit >= 0 && digit <= 9)
				.toList();
		assert !digits.isEmpty();
		return 10 * digits.get(0) + digits.get(digits.size() - 1);
	}
}
