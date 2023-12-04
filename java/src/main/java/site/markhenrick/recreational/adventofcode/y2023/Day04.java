package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.BitSet;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.FunctionalUtil.Pair.curry;
import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;
import static site.markhenrick.recreational.common.StringUtil.*;

public class Day04 {
	// As usual the parsing code is somewhat inefficient, producing lots of new Strings

	public static int part1(String input) {
		return matchCounts(input)
				.mapToInt(Day04::scoreIntersection)
				.sum();
	}

	public static int part2(String input) {
		val cards = matchCounts(input)
				.map(count -> new int[] { 1, count })
				.toList();
		var total = 0;
		for (var i = 0; i < cards.size(); i++) {
			var cardPair = cards.get(i);
			val factor = cardPair[0];
			val count = cardPair[1];
			total += factor;
			for (var j = i + 1; j <= i + count && j < cards.size(); j++) {
				cards.get(j)[0] += factor;
			}
		}
		return total;
	}

	private static Stream<Integer> matchCounts(String input) {
		return LINE_SPLITTER.apply(input)
				.map(Day04::parseCard)
				.map(curry(applyAndReturnLeft(BitSet::and))) // Don't mutate in prod guys
				.map(BitSet::cardinality);
	}

	// Debatable if using BitSets is even any better given it requires parsing the numbers.
	// Since they aren't actually used for anything but eq, a Set<String> could be usef
	static Pair<BitSet, BitSet> parseCard(String card) {
		val colonIndex = getSingleIndexOf(card, ':');
		val pipeIndex = getSingleIndexOf(card, '|');
		val winning = card.substring(colonIndex + 2, pipeIndex);
		val have = card.substring(pipeIndex + 2);
		return new Pair<>(parseNumberList(winning), parseNumberList(have));
	}

	private static BitSet parseNumberList(String input) {
		return WORD_SPLITTER.apply(input)
				.filter(string -> !string.isBlank())
				.mapToInt(Integer::parseInt)
				.collect(BitSet::new, BitSet::set, BitSet::or);
	}

	static int scoreIntersection(int size) {
		return size == 0 ? 0 : 1 << (size - 1);
	}
}
