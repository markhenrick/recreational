package site.markhenrick.adventofcode2020;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Day02Test {
	private static final String SAMPLE_INPUT = """
			1-3 a: abcde
			1-3 b: cdefg
			2-9 c: ccccccccc
		""";

	private static String getMyInput() throws Exception {
		final var resource = Day02Test.class.getClassLoader().getResource("input/day01part1.txt");
		assertThat(resource).isNotNull();
		return Files.readString(Paths.get(resource.toURI()));
	}

	@Test
	void parse() {
		final var output = Day02.parse(SAMPLE_INPUT);
		assertThat(output).hasSize(3);
		assertThat(output.get(0)).isEqualTo(new Day02.PasswordAndPolicy(1, 3, 'a', "abcde"));
	}

	@SuppressWarnings("SpellCheckingInspection")
	@ParameterizedTest
	@CsvSource({
		"1, 3, a, abcde,		true",
		"1, 3, b, cdefg,		false",
		"2, 9, c, ccccccccc,	true",
		"0, 9, a, bbb,			true",
		"0, 0, a, a,			false",
		"1, 1, a, a,			true",
	})
	void validatePasswordsPart1(int min, int max, char character, String password, boolean isValid) {
		final var input = new Day02.PasswordAndPolicy(min, max, character, password);
		assertThat(Day02.fastValidatePasswordPart1(input)).isEqualTo(isValid);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() throws Exception {
		return Stream.of(
			arguments(SAMPLE_INPUT, 2),
			arguments(getMyInput(), 538)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(CharSequence input, int expected) {
		assertThat(Day02.solvePart1(input)).isEqualTo(expected);
	}

	@SuppressWarnings("SpellCheckingInspection")
	@ParameterizedTest
	@CsvSource({
		"1, 3, a, abcde,		true",
		"1, 3, a, cbade,		true",
		"1, 3, b, cdefg,		false",
		"2, 9, c, ccccccccc,	false",
	})
	void validatePasswordsPart2(int min, int max, char character, String password, boolean isValid) {
		final var input = new Day02.PasswordAndPolicy(min, max, character, password);
		assertThat(Day02.validatePasswordPart2(input)).isEqualTo(isValid);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart2() throws Exception {
		return Stream.of(
			arguments(SAMPLE_INPUT, 1),
			arguments(getMyInput(), 489)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart2(CharSequence input, int expected) {
		assertThat(Day02.solvePart2(input)).isEqualTo(expected);
	}
}
