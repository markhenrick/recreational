package site.markhenrick.recreational.adventofcode.y2015;

import com.codepoetics.protonpack.StreamUtils;
import site.markhenrick.recreational.common.FunctionalUtil;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

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

	static boolean part2(String input) {
		assert input.equals(input.trim().toLowerCase(Locale.ROOT));
		var asCharArray = input.toCharArray();
		return containsDoubleLetterWithBreak(asCharArray) && containsDoubleDouble(asCharArray);
	}

	static boolean contains3Vowels(char[] input) {
		return FunctionalUtil.charStream(input)
			.filter(VOWELS::contains) // VOWELS is too small to be worth doing anything more complex than a linear search
			.limit(NEEDED_VOWELS)
			.count() == NEEDED_VOWELS;
	}

	static boolean containsDoubleLetter(char[] input) {
		return containsRepeatingElement(FunctionalUtil.charStream(input), FunctionalUtil.charStream(input).skip(1));
	}

	static boolean containsDoubleDouble(char[] input) {
		// There must be a smarter way to do this
		var doubles = splitIntoDoubles(input).toList();
		String previous = null;
		HashSet<String> seen = new HashSet<>(doubles.size());
		for (var aDouble : doubles) {
			if (!Objects.equals(previous, aDouble)) {
				if (!seen.add(aDouble)) {
					return true;
				}
			}
			previous = aDouble;
		}
		return false;
	}

	static boolean containsDoubleLetterWithBreak(char[] input) {
		return containsRepeatingElement(FunctionalUtil.charStream(input), FunctionalUtil.charStream(input).skip(2));
	}

	static boolean doesNotContainForbiddenDouble(char[] input) {
		return splitIntoDoubles(input)
			.noneMatch(FORBIDDEN_DOUBLES::contains);
	}

	static Stream<String> splitIntoDoubles(char[] input) {
		return StreamUtils.zip(FunctionalUtil.charStream(input), FunctionalUtil.charStream(input).skip(1), (a, b) -> "" + a + b);
	}

	static <T> boolean containsRepeatingElement(Stream<T> stream1, Stream<T> stream2) {
		return StreamUtils.zip(stream1, stream2, Object::equals)
			.anyMatch(x -> x);
	}
}
