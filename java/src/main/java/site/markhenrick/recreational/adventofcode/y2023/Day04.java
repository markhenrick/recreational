package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.BitSet;

import static site.markhenrick.recreational.common.FunctionalUtil.Pair.curry;
import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;
import static site.markhenrick.recreational.common.StringUtil.*;

public class Day04 {
	// As usual the parsing code is somewhat inefficient, producing lots of new Strings

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.map(Day04::parseCard)
				.map(curry(applyAndReturnLeft(BitSet::and))) // Don't mutate in prod guys
				.mapToInt(BitSet::cardinality)
				.map(Day04::scoreIntersection)
				.sum();
	}

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
