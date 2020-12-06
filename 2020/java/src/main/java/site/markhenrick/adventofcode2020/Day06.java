package site.markhenrick.adventofcode2020;

import java.util.BitSet;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static site.markhenrick.adventofcode2020.common.MiscUtil.LINE_SPLITTER;
import static site.markhenrick.adventofcode2020.common.MiscUtil.RECORD_SPLITTER;

class Day06 {
	static final Function<String, Integer> SOLVE_PART_1 = reduceRecords(applyAndReturnLeft(BitSet::or));
	static final Function<String, Integer> SOLVE_PART_2 = reduceRecords(applyAndReturnLeft(BitSet::and));

	private static BitSet decodePerson(final String input) {
		final var answeredQuestions = new BitSet();
		for (final var character : input.toCharArray()) {
			answeredQuestions.set(character);
		}
		return answeredQuestions;
	}

	private static Function<String, Integer> reduceRecords(final BinaryOperator<BitSet> accumulator) {
		return input -> RECORD_SPLITTER.apply(input)
			.map(record -> LINE_SPLITTER.apply(record)
				.map(Day06::decodePerson)
				.reduce(accumulator)
			).map(Optional::get) // yolo
			.mapToInt(BitSet::cardinality)
			.sum();
	}

	private static <A> BinaryOperator<A> applyAndReturnLeft(final BiConsumer<? super A, ? super A> mutatingOperator) {
		return (l, r) -> {
			mutatingOperator.accept(l, r);
			return l;
		};
	}
}
