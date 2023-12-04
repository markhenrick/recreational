package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day04 {
	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.map(Day04::parseCard)
				.mapToInt(Day04::scoreCard)
				.sum();
	}

	static Pair<Set<Integer>, Set<Integer>> parseCard(String card) {
		val colonIndex = card.indexOf(':');
		assert colonIndex != -1;
		assert card.lastIndexOf(':') == colonIndex;
		val parts = card.substring(colonIndex + 2).split("\\|");
		assert parts.length == 2;
		return new Pair<>(parseNumberList(parts[0]), parseNumberList(parts[1]));
	}

	// TODO the fact that the score *is* the binary intersection is too perfect. Replace this with BitSets later

	private static Set<Integer> parseNumberList(String input) {
		return WORD_SPLITTER.apply(input)
				.filter(string -> !string.isBlank())
				.map(Integer::parseInt)
				.collect(Collectors.toSet());
	}

	static int scoreCard(Pair<Set<Integer>, Set<Integer>> card) {
		// Can't do this in place since the collected set is immutable. Plus destroying the input isn't great anyway
		val intersection = new HashSet<>(card.l());
		intersection.retainAll(card.r());
		return scoreIntersection(intersection.size());
	}

	static int scoreIntersection(int size) {
		// TODO int pow
		// Actually don't need a special case for size=0 since 0.5 truncates to 0
		return (int) Math.pow(2, size - 1);
	}
}
