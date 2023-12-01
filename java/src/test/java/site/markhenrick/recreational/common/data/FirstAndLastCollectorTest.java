package site.markhenrick.recreational.common.data;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstAndLastCollectorTest {
	@Test
	void shouldYieldEmptyOptionalForEmptyInput() {
		val actual = Stream.empty()
				.collect(new FirstAndLastCollector<>());
		assertThat(actual).isEmpty();
	}

	static Stream<Arguments> shouldCollect() {
		// Using lists instead of streams just so the JUnit output is nicer
		return Stream.of(
				Arguments.of(List.of(1), 1, 1),
				Arguments.of(List.of(1, 2), 1, 2),
				Arguments.of(List.of(2, 1), 2, 1),
				Arguments.of(List.of(1, 2, 3), 1, 3)
		);
	}

	@ParameterizedTest
	@MethodSource
	void shouldCollect(List<Integer> input, int expectedFirst, int expectedLast) {
		val actual = input.stream().collect(new FirstAndLastCollector<>());
		assertThat(actual).isPresent();
		assertThat(actual.get().getValue0()).isEqualTo(expectedFirst);
		assertThat(actual.get().getValue1()).isEqualTo(expectedLast);
	}
}
