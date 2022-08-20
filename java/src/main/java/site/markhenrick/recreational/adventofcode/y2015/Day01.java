package site.markhenrick.recreational.adventofcode.y2015;

import site.markhenrick.recreational.common.StringUtil;

@SuppressWarnings({"CharUsedInArithmeticContext", "StatementWithEmptyBody"})
class Day01 {

	public static long solvePart1(final CharSequence input) {
		return input.length() - 2 * StringUtil.charStream(input)
			.filter(character -> character == ')')
			.count();
	}

	public static Integer solvePart2(final CharSequence input) {
		var i = 0;
		for (var floor = 0; i < input.length() && floor >= 0; floor += 81 - 2 * input.charAt(i++)); // Real K&R hours
		return i;
	}
}
