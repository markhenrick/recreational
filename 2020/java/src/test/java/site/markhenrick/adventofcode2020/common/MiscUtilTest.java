package site.markhenrick.adventofcode2020.common;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MiscUtilTest {
	@SuppressWarnings("unused")
	static Stream<Arguments> lines() {
		return Stream.of(
			arguments("ab", List.of("ab")),
			arguments("ab\na", List.of("ab", "a")),
			arguments("ab\na\n", List.of("ab", "a"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void lines(final String input, final List<String> expected) {
		assertThat(MiscUtil.lines(input)).isEqualTo(expected);
	}
}
