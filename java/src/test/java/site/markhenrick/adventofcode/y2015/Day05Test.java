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
	static Stream<String> fullyNice() {
		return Stream.of(
			"ugknbfddgicrmopn",
			"aaa"
		);
	}

	@ParameterizedTest
	@MethodSource
	void fullyNice(String input) {
		assertThat(Day05.part1(input)).isTrue();
	}

	@SuppressWarnings("unused")
	static Stream<String> contains3Vowels() {
		return Stream.concat(fullyNice(), Stream.of(
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
		return Stream.concat(fullyNice(), Stream.of(
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
		return fullyNice();
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
	static Stream<String> naughtyForAnyReason() {
		return Stream.concat(doesNotContain3Vowels(), Stream.concat(doesNotContainDoubleLetter(), containsForbiddenDouble()));
	}

	@ParameterizedTest
	@MethodSource
	void naughtyForAnyReason(String input) {
		assertThat(Day05.part1(input)).isFalse();
	}

	@Test
	void actualInput() {
		var input = getResourceAsString("input/2015/day05.txt").trim();
		var count = StringUtil.LINE_SPLITTER.apply(input)
			.filter(Day05::part1)
			.count();
		assertThat(count).isEqualTo(258);
	}
}
