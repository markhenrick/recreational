package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;
import site.markhenrick.recreational.common.data.FirstAndLastCollector;

import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day01 {
	// Decided to go with a nice streams approach rather than a faster but ugly state machine
	// time and space should only differ by a constant factor :)

	private static final Pattern PART2_PATTERN = Pattern.compile("(\\d|one|two|three|four|five|six|seven|eight|nine)");
	//<editor-fold desc="zero -> 0 etc." defaultstate="collapsed">
	private static final Map<String, Integer> DIGIT_MAP = Map.ofEntries(
			// The only part of this codebase where I used ChatGPT. Not typing all this myself lol
			Map.entry("0", 0),
			Map.entry("zero", 0),
			Map.entry("1", 1),
			Map.entry("one", 1),
			Map.entry("2", 2),
			Map.entry("two", 2),
			Map.entry("3", 3),
			Map.entry("three", 3),
			Map.entry("4", 4),
			Map.entry("four", 4),
			Map.entry("5", 5),
			Map.entry("five", 5),
			Map.entry("6", 6),
			Map.entry("six", 6),
			Map.entry("7", 7),
			Map.entry("seven", 7),
			Map.entry("8", 8),
			Map.entry("eight", 8),
			Map.entry("9", 9),
			Map.entry("nine", 9)
	);
	//</editor-fold>

	public static int part1(String input) {
		return processFile(input, Day01::p1Splitter);
	}

	public static int part2(String input) {
		return processFile(input, Day01::p2Splitter);
	}

	private static int processFile(String input, Function<String, Stream<Integer>> splitter) {
		return LINE_SPLITTER.apply(input)
				.mapToInt(processLineWith(splitter))
				.sum();
	}

	static ToIntFunction<String> processLineWith(Function<String, Stream<Integer>> splitter) {
		return line -> {
			//noinspection OptionalGetWithoutIsPresent
			val digits = splitter.apply(line)
					.filter(digit -> digit >= 0 && digit <= 9)
					.collect(new FirstAndLastCollector<>())
					.get();
			return 10 * digits.getValue0() + digits.getValue1();
		};
	}

	static Stream<Integer> p1Splitter(String line) {
		return StringUtil.charStream(line).map(character -> character - '0');
	}

	static Stream<Integer> p2Splitter(String line) {
		return PART2_PATTERN.matcher(line).results().map(result -> DIGIT_MAP.get(result.group(1)));
	}
}
