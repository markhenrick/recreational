package site.markhenrick.adventofcode.y2015;

import com.codepoetics.protonpack.StreamUtils;

import java.util.Locale;
import java.util.Set;

import static site.markhenrick.adventofcode.common.FunctionalUtil.charStream;

public class Day05 {
	private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
	private static final int NEEDED_VOWELS = 3;
	private static final Set<String> FORBIDDEN_DOUBLES = Set.of("ab", "cd", "pq", "xy");

	// General disclaimer that this Java version priorities expressibility over performance, hence the duplicated iteration

	static boolean part1(String input) {
		assert input.equals(input.trim().toLowerCase(Locale.ROOT));
		var asCharArray = input.toCharArray();
		return contains3Vowels(asCharArray) && containsDoubleLetter(asCharArray) && doesNotContainForbiddenDouble(asCharArray);
	}

	static boolean contains3Vowels(char[] input) {
		return charStream(input)
			.filter(VOWELS::contains) // VOWELS is too small to be worth doing anything more complex than a linear search
			.limit(NEEDED_VOWELS)
			.count() == NEEDED_VOWELS;
	}

	static boolean containsDoubleLetter(char[] input) {
		return StreamUtils.zip(charStream(input), charStream(input).skip(1), (a, b) -> a == b)
			.anyMatch(x -> x);
	}

	static boolean doesNotContainForbiddenDouble(char[] input) {
		return StreamUtils.zip(charStream(input), charStream(input).skip(1), (a, b) -> "" + a + b)
			.noneMatch(FORBIDDEN_DOUBLES::contains);
	}
}
