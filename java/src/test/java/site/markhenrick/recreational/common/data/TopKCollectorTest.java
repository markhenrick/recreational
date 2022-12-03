package site.markhenrick.recreational.common.data;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TopKCollectorTest {
	static Stream<Arguments> fullTest() {
		return Stream.of(
			arguments(0, List.of(), List.of()),
			arguments(0, List.of(1, 2, 3), List.of()),
			arguments(1, List.of(), List.of()),
			arguments(1, List.of(1, 2, 3), List.of(3)),
			arguments(1, List.of(2, 3, 1), List.of(3)),
			arguments(2, List.of(2, 3, 1), List.of(2, 3)),
			arguments(3, List.of(2, 3, 1), List.of(1, 2, 3)),
			arguments(4, List.of(2, 3, 1), List.of(1, 2, 3)),
			arguments(3, List.of(18, 12, 4, 9, 21, 37, 3), List.of(18, 21, 37))
		);
	}

	@ParameterizedTest
	@MethodSource("fullTest")
	void fullTestSerial(int k, List<Integer> input, List<Integer> expected) {
		var result = input
			.stream()
			.collect(new TopKCollector<>(k, Integer::compareTo));
		assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("fullTest")
	void fullTestParallel(int k, List<Integer> input, List<Integer> expected) {
		var result = input
			.parallelStream() // Though this doesn't necessarily guarantee it will fork. That's impossible to predict
			.collect(new TopKCollector<>(k, Integer::compareTo));
		assertThat(result).isEqualTo(expected);
	}

	static Stream<Arguments> accumulatorTest() {
		return java.util.stream.Stream.of(
			arguments(0, List.of(), 1, List.of()),
			arguments(1, List.of(), 1, List.of(1)),
			arguments(1, List.of(2), 1, List.of(2)),
			arguments(1, List.of(2), 2, List.of(2)),
			arguments(1, List.of(2), 3, List.of(3)),
			arguments(2, List.of(1), 0, List.of(0, 1)),
			arguments(2, List.of(1), 1, List.of(1, 1)),
			arguments(2, List.of(1), 2, List.of(1, 2)),
			arguments(2, List.of(1, 2), 0, List.of(1, 2)),
			arguments(2, List.of(1, 2), 1, List.of(1, 2)),
			arguments(2, List.of(1, 2), 2, List.of(2, 2)),
			arguments(2, List.of(1, 2), 3, List.of(2, 3)),
			arguments(3, List.of(1), 0, List.of(0, 1)),
			arguments(3, List.of(1), 1, List.of(1, 1)),
			arguments(3, List.of(1), 2, List.of(1, 2)),
			arguments(3, List.of(1, 2), 0, List.of(0, 1, 2)),
			arguments(3, List.of(1, 2), 1, List.of(1, 1, 2)),
			arguments(3, List.of(1, 2), 2, List.of(1, 2, 2)),
			arguments(3, List.of(1, 2), 3, List.of(1, 2, 3)),
			arguments(3, List.of(1, 2, 3), 0, List.of(1, 2, 3)),
			arguments(3, List.of(1, 2, 3), 1, List.of(1, 2, 3)),
			arguments(3, List.of(1, 2, 3), 2, List.of(2, 2, 3)),
			arguments(3, List.of(1, 2, 3), 3, List.of(2, 3, 3)),
			arguments(3, List.of(1, 2, 3), 4, List.of(2, 3, 4)),
			arguments(5, List.of(1, 3, 5, 7, 9), 2, List.of(2, 3, 5, 7, 9)),
			arguments(5, List.of(1, 3, 5, 7, 9), 5, List.of(3, 5, 5, 7, 9)),
			arguments(5, List.of(1, 3, 5, 7, 9), 8, List.of(3, 5, 7, 8, 9)),
			arguments(5, List.of(1, 3, 5, 7, 9), 9, List.of(3, 5, 7, 9, 9)),
			arguments(5, List.of(1, 3, 5, 7, 9), 11, List.of(3, 5, 7, 9, 11))
		);
	}

	@ParameterizedTest
	@MethodSource
	void accumulatorTest(int k, List<Integer> list, int candidate, List<Integer> expected) {
		var collector = new TopKCollector<>(k, Integer::compareTo);
		var mutableList = new ArrayList<>(list);
		collector.accumulator().accept(mutableList, candidate);
		assertThat(mutableList).isEqualTo(expected);
	}

	static Stream<Arguments> combinerTest() {
		return Stream.of(
			arguments(0, List.of(), List.of(), List.of()),
			arguments(1, List.of(), List.of(), List.of()),
			arguments(1, List.of(1), List.of(), List.of(1)),
			arguments(1, List.of(1), List.of(2), List.of(2)),
			arguments(2, List.of(), List.of(1), List.of(1)),
			arguments(2, List.of(1), List.of(2), List.of(1, 2)),
			arguments(2, List.of(1, 3), List.of(2), List.of(2, 3)),
			arguments(2, List.of(1, 3), List.of(2, 4), List.of(3, 4))
		);
	}

	@ParameterizedTest
	@MethodSource("combinerTest")
	void combinerForwards(int k, List<Integer> list1, List<Integer> list2, List<Integer> expected) {
		var collector = new TopKCollector<>(k, Integer::compareTo);
		var result = collector.combiner().apply(list1, list2);
		assertThat(result).isEqualTo(expected);
	}

	@ParameterizedTest
	@MethodSource("combinerTest")
	void combinerBackwards(int k, List<Integer> list1, List<Integer> list2, List<Integer> expected) {
		var collector = new TopKCollector<>(k, Integer::compareTo);
		var result = collector.combiner().apply(list2, list1);
		assertThat(result).isEqualTo(expected);
	}

	// TODO test stability and behaviour on duplicates when implemented
}
