package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.BitSet;

import static site.markhenrick.recreational.common.StringUtil.*;

public class Day04 {
	// As usual the parsing code is somewhat inefficient, producing lots of new Strings

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.map(Day04::parseCard)
				.mapToInt(Day04::scoreCard)
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

	// TODO split this into intersection, map size, map score

	/** Destroys card.l */
	static int scoreCard(Pair<BitSet, BitSet> card) {
		val intersection = card.l(); // Another thing to not do in prod code
		intersection.and(card.r());
		return scoreIntersection(intersection.cardinality());
	}

	static int scoreIntersection(int size) {
		return size == 0 ? 0 : 1 << (size - 1);
	}
}
