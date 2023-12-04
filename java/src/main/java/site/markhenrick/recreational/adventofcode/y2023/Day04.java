package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
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
		// TODO just use int[][]
		// type inference was just giving me List<Object> here
		List<Pair<Integer, Integer>> cards = matchCounts(input)
				.map(card -> new Pair<>(1, card))
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll); // can't use normal one since we need mutability
		for (var i = 0; i < cards.size(); i++) {
			var cardPair = cards.get(i);
			val factor = cardPair.l();
			val score = cardPair.r();
			for (var j = i + 1; j <= i + score && j < cards.size(); j++) {
				val otherCard = cards.get(j);
				cards.set(j, new Pair<>(otherCard.l() + factor, otherCard.r()));
			}
		}
		return cards.stream()
				.mapToInt(Pair::l)
				.sum();
	}

	private static Stream<Integer> matchCounts(String input) {
		return LINE_SPLITTER.apply(input)
				.map(Day04::parseCard)
				.map(curry(applyAndReturnLeft(BitSet::and))) // Don't mutate in prod guys
				.map(BitSet::cardinality);
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
