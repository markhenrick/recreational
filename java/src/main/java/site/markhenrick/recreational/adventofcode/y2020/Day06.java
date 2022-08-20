package site.markhenrick.recreational.adventofcode.y2020;

import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.BitSet;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;

@SuppressWarnings("CharUsedInArithmeticContext")
class Day06 {
	static final Function<String, Integer> SOLVE_PART_1 = reduceRecords(FunctionalUtil.applyAndReturnLeft(BitSet::or));
	static final Function<String, Integer> SOLVE_PART_2 = reduceRecords(FunctionalUtil.applyAndReturnLeft(BitSet::and));

	private static BitSet decodePerson(final String input) {
		final var answeredQuestions = new BitSet();
		for (final var character : input.toCharArray()) {
			answeredQuestions.set(character - 'a');
		}
		return answeredQuestions;
	}

	private static Function<String, Integer> reduceRecords(final BinaryOperator<BitSet> accumulator) {
		return input -> StringUtil.RECORD_SPLITTER.apply(input)
			.map(record -> StringUtil.LINE_SPLITTER.apply(record)
				.map(Day06::decodePerson)
				.reduce(accumulator)
			).map(Optional::get) // yolo
			.mapToInt(BitSet::cardinality)
			.sum();
	}

}
