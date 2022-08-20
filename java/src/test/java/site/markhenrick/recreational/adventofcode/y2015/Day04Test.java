package site.markhenrick.recreational.adventofcode.y2015;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Day04Test {
	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(609043, "abcdef"),
			arguments(1048970, "pqrstuv"),
			arguments(282749, "yzbqklnj")
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expected, String key) throws NoSuchAlgorithmException {
		assertThat(Day04.part1(key)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(9962624, "yzbqklnj")
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(int expected, String key) throws NoSuchAlgorithmException {
		assertThat(Day04.part2(key)).isEqualTo(expected);
	}
}
