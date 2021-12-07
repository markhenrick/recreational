package site.markhenrick.adventofcode.y2015;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.adventofcode.common.StringUtil;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

@SuppressWarnings("SpellCheckingInspection")
public class Day05Test {
	static Stream<String> fullyNiceP1() {
		return Stream.of(
			"ugknbfddgicrmopn",
			"aaa"
		);
	}

	@ParameterizedTest
	@MethodSource
	void fullyNiceP1(String input) {
		assertThat(Day05.part1(input)).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> contains3Vowels() {
		return Stream.concat(fullyNiceP1(), Stream.of(
			"aei",
			"xazegov",
			"aeiouaeiouaeiou"
		));
	}

	@ParameterizedTest
	@MethodSource
	void contains3Vowels(String input) {
		assertThat(Day05.contains3Vowels(input.toCharArray())).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> doesNotContain3Vowels() {
		return Stream.of(
			"abcdde",
			"aabbccdd"
		);
	}

	@ParameterizedTest
	@MethodSource
	void doesNotContain3Vowels(String input) {
		assertThat(Day05.contains3Vowels(input.toCharArray())).isFalse();
	}

	@SuppressWarnings("unused")
	static Stream<String> containsDoubleLetter() {
		return Stream.concat(fullyNiceP1(), Stream.of(
			"abcdde",
			"aabbccdd"
		));
	}

	@ParameterizedTest
	@MethodSource
	void containsDoubleLetter(String input) {
		assertThat(Day05.containsDoubleLetter(input.toCharArray())).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> doesNotContainDoubleLetter() {
		return Stream.of(
			"aei",
			"xazegov",
			"aeiouaeiouaeiou"
		);
	}

	@ParameterizedTest
	@MethodSource
	void doesNotContainDoubleLetter(String input) {
		assertThat(Day05.containsDoubleLetter(input.toCharArray())).isFalse();
	}

	@SuppressWarnings("unused")
	static Stream<String> doesNotContainForbiddenDouble() {
		return fullyNiceP1();
	}

	@ParameterizedTest
	@MethodSource
	void doesNotContainForbiddenDouble(String input) {
		assertThat(Day05.doesNotContainForbiddenDouble(input.toCharArray())).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> containsForbiddenDouble() {
		return Stream.of(
			"ab",
			"aab",
			"abb",
			"aabb"
		);
	}

	@ParameterizedTest
	@MethodSource
	void containsForbiddenDouble(String input) {
		assertThat(Day05.doesNotContainForbiddenDouble(input.toCharArray())).isFalse();
	}

	@SuppressWarnings("unused")
	static Stream<String> naughtyForAnyReasonP1() {
		return Stream.concat(doesNotContain3Vowels(), Stream.concat(doesNotContainDoubleLetter(), containsForbiddenDouble()));
	}

	@ParameterizedTest
	@MethodSource
	void naughtyForAnyReasonP1(String input) {
		assertThat(Day05.part1(input)).isFalse();
	}

	@Test
	void actualInputP1() {
		var input = getResourceAsString("input/2015/day05.txt").trim();
		var count = StringUtil.LINE_SPLITTER.apply(input)
			.filter(Day05::part1)
			.count();
		assertThat(count).isEqualTo(258);
	}

	static Stream<String> fullyNiceP2() {
		return Stream.of(
			"qjhvhtzxzqqjkmpb",
			"xxyxx"
		);
	}

	@ParameterizedTest
	@MethodSource
	void fullyNiceP2(String input) {
		assertThat(Day05.part2(input)).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> containsDoubleDouble() {
		return Stream.concat(fullyNiceP2(), Stream.of(
			"xyxy",
			"aabcdefgaa"
		));
	}

	@ParameterizedTest
	@MethodSource
	void containsDoubleDouble(String input) {
		assertThat(Day05.containsDoubleDouble(input.toCharArray())).isTrue();
	}

	static Stream<String> doesNotContainDoubleDouble() {
		return Stream.of(
			"aaa",
			"abcd"
		);
	}

	@ParameterizedTest
	@MethodSource
	void doesNotContainDoubleDouble(String input) {
		assertThat(Day05.containsDoubleDouble(input.toCharArray())).isFalse();
	}

	@SuppressWarnings("unused")
	static Stream<String> containsDoubleLetterWithBreak() {
		return Stream.concat(fullyNiceP2(), Stream.of(
			"xyx",
			"abcdefeghi",
			"aaa"
		));
	}

	@ParameterizedTest
	@MethodSource
	void containsDoubleLetterWithBreak(String input) {
		assertThat(Day05.containsDoubleLetterWithBreak(input.toCharArray())).isTrue();
	}

	static Stream<String> doesNotContainDoubleLetterWithBreak() {
		return Stream.of(
			"abc"
		);
	}

	@ParameterizedTest
	@MethodSource
	void doesNotContainDoubleLetterWithBreak(String input) {
		assertThat(Day05.containsDoubleLetterWithBreak(input.toCharArray())).isFalse();
	}

	@SuppressWarnings("unused")
	static Stream<String> naughtyForAnyReasonP2() {
		return Stream.concat(doesNotContainDoubleDouble(), doesNotContainDoubleLetterWithBreak());
	}

	@ParameterizedTest
	@MethodSource
	void naughtyForAnyReasonP2(String input) {
		assertThat(Day05.part2(input)).isFalse();
	}

	@Test
	void actualInputP2() {
		var input = getResourceAsString("input/2015/day05.txt").trim();
		var count = StringUtil.LINE_SPLITTER.apply(input)
			.filter(Day05::part2)
			.count();
		assertThat(count).isEqualTo(53);
	}
}
