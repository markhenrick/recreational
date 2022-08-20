package site.markhenrick.recreational.adventofcode.y2021;

import org.javatuples.Pair;
import site.markhenrick.recreational.common.StatUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.*;
import java.util.stream.Stream;

public class Day10 {
	private static final Map<Character, Character> OPENING_TO_CLOSING = Map.of(
		'(', ')',
		'[', ']',
		'{', '}',
		'<', '>'
	);

	private static final Map<Character, Integer> P1_SCORES = Map.of(
		')', 3,
		']', 57,
		'}', 1197,
		'>', 25137
	);

	private static final String P2_SCORES = " )]}>";

	static int part1(String input) {
		return StringUtil.LINE_SPLITTER.apply(input.trim())
			.map(Day10::findErrorAndAutocomplete)
			.map(Pair::getValue0)
			.filter(Objects::nonNull)
			.mapToInt(P1_SCORES::get)
			.sum();
	}

	static long part2(String input) {
		var scores = StringUtil.LINE_SPLITTER.apply(input.trim())
			.map(Day10::findErrorAndAutocomplete)
			.map(Pair::getValue1)
			.filter(Objects::nonNull)
			.map(Collection::stream)
			.mapToLong(Day10::scoreStack)
			.toArray();
		assert scores.length % 2 == 1;
		return StatUtil.median(scores);
	}

	// proper sum types pls...
	static Pair<Character, ArrayDeque<Character>> findErrorAndAutocomplete(String input) {
		var stack = new ArrayDeque<Character>(input.length());
		for (var character : input.toCharArray()) {
			var closingOfThisBlock = OPENING_TO_CLOSING.get(character);
			if (closingOfThisBlock != null) {
				// Is an opening
				stack.push(closingOfThisBlock);
			} else {
				// Is a closing
				assert OPENING_TO_CLOSING.containsValue(character);
				var closingOfLastBlock = stack.pollFirst();
				if (!Objects.equals(closingOfLastBlock, character)) {
					return Pair.with(character, null);
				}
			}
		}
		return Pair.with(null, stack);
	}

	static long scoreStack(Stream<Character> stream) {
		return stream
			.mapToLong(P2_SCORES::indexOf)
			.reduce(0, (score, newChar) -> 5 * score + newChar);
	}
}
