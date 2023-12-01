package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.javatuples.Pair;
import site.markhenrick.recreational.common.StringUtil;
import site.markhenrick.recreational.common.data.FirstAndLastCollector;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

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

	private static int processFile(String input, Function<String, Pair<Integer, Integer>> splitter) {
		return LINE_SPLITTER.apply(input)
				.mapToInt(line -> {
					val digits = splitter.apply(line);
					return 10 * digits.getValue0() + digits.getValue1();
				})
				.sum();
	}

	static Pair<Integer, Integer> p1Splitter(String line) {
		return StringUtil.charStream(line)
				.map(character -> character - '0')
				.filter(digit -> digit >= 0 && digit <= 9)
				.collect(new FirstAndLastCollector<>())
				.get();
	}

	static Pair<Integer, Integer> p2Splitter(String line) {
		// TODO not very satisfied with this. Probably a better way. But since the matches can overlap this might be about optimal
		val first = DIGIT_MAP.entrySet().stream()
				.min(Comparator.comparing(entry -> minusOneToMaxInstead(line.indexOf(entry.getKey()))))
				.get();
		val last = DIGIT_MAP.entrySet().stream()
				.max(Comparator.comparing(entry -> line.lastIndexOf(entry.getKey())))
				.get();
		assert line.contains(first.getKey()) && line.contains(last.getKey());
		return Pair.with(first.getValue(), last.getValue());
	}

	private static int minusOneToMaxInstead(int x) {
		return x == -1 ? Integer.MAX_VALUE : x;
	}
}
