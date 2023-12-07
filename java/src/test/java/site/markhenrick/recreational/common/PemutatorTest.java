package site.markhenrick.recreational.common;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PemutatorTest {
	static Stream<Arguments> permutations() {
		return Stream.of(
			Arguments.of(List.of(), List.of(List.of())),
			Arguments.of(List.of('A'), List.of(List.of('A'))),
			Arguments.of(List.of('A', 'B'), List.of(List.of('A', 'B'), List.of('B', 'A'))),
			Arguments.of(List.of('A', 'B', 'C'), List.of(List.of('A', 'B', 'C'), List.of('A', 'C', 'B'), List.of('B', 'A', 'C'), List.of('B', 'C', 'A'), List.of('C', 'A', 'B'), List.of('C', 'B', 'A')))
		);
	}

	@ParameterizedTest
	@MethodSource
	void permutations(List<Character> input, List<List<Character>> expected) {
		val actual = Permutator.permutations(input).toList();
		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
	}
	
	static Stream<Arguments> uniquePermutations() {
		return Stream.of(
			Arguments.of(Map.of(), List.of(List.of())),
			Arguments.of(Map.of('A', 1), List.of(List.of('A'))),
			Arguments.of(Map.of('A', 3), List.of(List.of('A', 'A', 'A'))),
			Arguments.of(Map.of('A', 1, 'B', 1), List.of(List.of('A', 'B'), List.of('B', 'A'))),
			Arguments.of(Map.of('A', 2, 'B', 1), List.of(List.of('A', 'A', 'B'), List.of('A', 'B', 'A'), List.of('B', 'A', 'A'))),
			Arguments.of(Map.of('A', 2, 'B', 2), List.of(List.of('A', 'A', 'B', 'B'), List.of('A', 'B', 'B', 'A'), List.of('A', 'B', 'A', 'B'), List.of('B', 'B', 'A', 'A'), List.of('B', 'A', 'A', 'B'), List.of('B', 'A', 'B', 'A')))
		);
	}

	@ParameterizedTest
	@MethodSource
	void uniquePermutations(Map<Character, Integer> input, List<List<Character>> expected) {
		val actual = Permutator.uniquePermutations(input).toList();
		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
	}
}
