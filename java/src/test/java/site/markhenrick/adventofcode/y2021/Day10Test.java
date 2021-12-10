package site.markhenrick.adventofcode.y2021;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.adventofcode.common.StringUtil;

import java.util.ArrayDeque;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode.common.TestUtil.getResourceAsString;

public class Day10Test {
	private static final String SAMPLE_INPUT_P2 = "[({(<(())[]>[[{[]{<()<>>\n" +
		"[(()[<>])]({[<{<<[]>>(\n" +
		"{([(<{}[<>[]}>{[]{[(<()>\n" +
		"(((({<>}<{<{<>}{[]{[]{}\n" +
		"[[<[([]))<([[{}[[()]]]\n" +
		"[{[{({}]{}}([{[{{{}}([]\n" +
		"{<[[]]>}<{[{[{[]{()[[[]\n" +
		"[<(<(<(<{}))><([]([]()\n" +
		"<{([([[(<>()){}]>(<<{{\n" +
		"<{([{{}}[<[[[<>{}]]]>[]]";

	@ParameterizedTest
	@CsvSource({
		"},{([(<{}[<>[]}>{[]{[(<()>",
		"),[[<[([]))<([[{}[[()]]]",
		"],[{[{({}]{}}([{[{{{}}([]",
		"),[<(<(<(<{}))><([]([]()",
		">,<{([([[(<>()){}]>(<<{{",
	})
	void findFirstError(char expectedChar, String line) {
		var result = Day10.findErrorAndAutocomplete(line);
		assertThat(result.getValue0()).isEqualTo(expectedChar);
		assertThat(result.getValue1()).isNull();
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part1() {
		return Stream.of(
			arguments(26397, SAMPLE_INPUT_P2),
			arguments(345441, getResourceAsString("input/2021/day10.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part1(int expectedScore, String input) {
		assertThat(Day10.part1(input)).isEqualTo(expectedScore);
	}

	@ParameterizedTest
	@CsvSource({
		"}}]])})],[({(<(())[]>[[{[]{<()<>>",
		")}>]}),[(()[<>])]({[<{<<[]>>(",
		"}}>}>)))),(((({<>}<{<{<>}{[]{[]{}",
		"]]}}]}]}>,{<[[]]>}<{[{[{[]{()[[[]",
		"])}>,<{([{{}}[<[[[<>{}]]]>[]]",
	})
	void autocomplete(String expectedCompletion, String line) {
		var result = Day10.findErrorAndAutocomplete(line);
		assertThat(charStackToString(result.getValue1())).isEqualTo(expectedCompletion);
		assertThat(result.getValue0()).isNull();
	}

	@ParameterizedTest
	@CsvSource({
		"288957,}}]])})]",
		"5566,)}>]})",
		"1480781,}}>}>))))",
		"995444,]]}}]}]}>",
		"294,])}>",
	})
	void scoreStack(int expectedScore, String stack) {
		var actualScore = Day10.scoreStack(StringUtil.charStream(stack));
		assertThat(actualScore).isEqualTo(expectedScore);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> part2() {
		return Stream.of(
			arguments(288957L, SAMPLE_INPUT_P2),
			arguments(3235371166L, getResourceAsString("input/2021/day10.txt"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void part2(long expectedScore, String input) {
		assertThat(Day10.part2(input)).isEqualTo(expectedScore);
	}

	private static String charStackToString(ArrayDeque<Character> stack) {
		var sb = new StringBuilder();
		stack.forEach(sb::append);
		return sb.toString();
	}
}
