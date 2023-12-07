package site.markhenrick.recreational.common;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PemutatorTest {
	static Stream<Arguments> permutations() {
		return Stream.of(
			Arguments.of(List.of(), List.of(List.of())),
			Arguments.of(List.of(1), List.of(List.of(1))),
			Arguments.of(List.of(1, 2), List.of(List.of(1, 2), List.of(2, 1))),
			Arguments.of(List.of(1, 2, 3), List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1), List.of(3, 1, 2), List.of(3, 2, 1)))
		);
	}

	@ParameterizedTest
	@MethodSource
	void permutations(List<Integer> input, List<List<Integer>> expected) {
		val actual = Permutator.permutations(input).toList();
		assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
	}
}
