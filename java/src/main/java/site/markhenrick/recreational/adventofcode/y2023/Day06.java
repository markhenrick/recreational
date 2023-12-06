package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day06 {
	private static final String TIME_PREFIX = "Time:     ";
	private static final String DISTANCE_PREFIX = "Distance: ";

	public static long part1(String input) {
		val lines = input.split("\n");
		assert lines.length == 2;
		val durations = parseLineP1(TIME_PREFIX, lines[0]);
		val records = parseLineP1(DISTANCE_PREFIX, lines[1]);
		assert durations.size() == records.size();
		return IntStream.range(0, durations.size())
				// Not gonna bother boxing to Pair<> first
				.mapToLong(i -> countValids(durations.get(i), records.get(i)))
				.reduce(1, (x, y) -> x * y);
	}

	public static long part2(String input) {
		val lines = input.split("\n");
		assert lines.length == 2;
		val duration = parseLineP2(TIME_PREFIX, lines[0]);
		val record = parseLineP2(DISTANCE_PREFIX, lines[1]);
		return countValids(duration, record);
	}

	// TODO return a stream instead, and finally implement zipStream()
	private static List<Long> parseLineP1(String expectedPrefix, String line) {
		return getNumberStrings(expectedPrefix, line)
				.map(Long::parseLong)
				.toList();
	}

	private static Long parseLineP2(String expectedPrefix, String line) {
		return Long.parseLong(getNumberStrings(expectedPrefix, line)
				.collect(Collectors.joining()));
	}

	private static Stream<String> getNumberStrings(String expectedPrefix, String line) {
		assert line.startsWith(expectedPrefix);
		return WORD_SPLITTER.apply(line.substring(expectedPrefix.length()))
				.filter(string -> !string.isBlank());
	}

	static long countValids(long duration, long record) {
		// - buttonDuration^2 + duration * buttonDuration - record = 0;
		val roots = quadraticRoots(-1, duration, -record);
		return countIntegers(roots.l(), roots.r());
	}

	static Pair<Double, Double> quadraticRoots(double a, double b, double c) {
		val rootDiscriminant = Math.sqrt(b * b - 4 * a * c);
		val denom = 2 * a;
		val firstRoot = (-b + rootDiscriminant) / denom;
		val secondRoot = (-b - rootDiscriminant) / denom;
		return new Pair<>(firstRoot, secondRoot);
	}

	static long countIntegers(double start, double end) {
		return ((long) end) - ((long) start) - (end % 1 == 0 ? 1 : 0);
	}
}
