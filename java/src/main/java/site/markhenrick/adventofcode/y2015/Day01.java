package site.markhenrick.adventofcode.y2015;

import static site.markhenrick.adventofcode.common.StringUtil.charStream;

@SuppressWarnings("CharUsedInArithmeticContext")
class Day01 {

	// Lookup tables are boring ok
	private static int decodeChar(final char character) {
		return 81 - 2*character;
	}

	public static long solvePart1(final CharSequence input) {
		return input.length() - 2 * charStream(input)
			.filter(character -> character == ')')
			.count();
	}

	public static Integer solvePart2(final CharSequence input) {
		// Java's functional builtins aren't stronk enough for this one
		var floor = 0;
		for (var i = 0; i < input.length(); i++) {
			floor += decodeChar(input.charAt(i));
			if (floor < 0) {
				return i + 1;
			}
		}
		return null;
	}
}
