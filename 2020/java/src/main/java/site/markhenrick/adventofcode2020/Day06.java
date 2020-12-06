package site.markhenrick.adventofcode2020;

import java.util.BitSet;

import static site.markhenrick.adventofcode2020.common.MiscUtil.RECORD_SPLITTER;

class Day06 {

	static int decodeGroup(final String input) {
		final var answeredQuestions = new BitSet();
		for (final var character : input.toCharArray()) {
			answeredQuestions.set(character);
		}
		answeredQuestions.clear('\n');
		return answeredQuestions.cardinality();
	}

	static int solvePart1(final String input) {
		return RECORD_SPLITTER.apply(input)
			.mapToInt(Day06::decodeGroup)
			.sum();
	}
}
