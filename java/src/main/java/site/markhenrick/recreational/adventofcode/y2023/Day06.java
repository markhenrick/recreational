package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.List;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day06 {

	public static long part1(String input) {
		val lines = input.split("\n");
		assert lines.length == 2;
		val times = parseLine("Time:     ", lines[0]);
		val durations = parseLine("Distance: ", lines[1]);
		assert times.size() == durations.size();
		return IntStream.range(0, times.size())
				// Not gonna bother boxing to Pair<> first
				.mapToLong(i -> countValids(times.get(i), durations.get(i)))
				.reduce(1, (x, y) -> x * y);
	}

	// TODO return a stream instead, and finally implement zipStream()
	private static List<Long> parseLine(String expectedPrefix, String line) {
		assert line.startsWith(expectedPrefix);
		return WORD_SPLITTER.apply(line.substring(expectedPrefix.length()))
				.filter(string -> !string.isBlank())
				.map(Long::parseLong)
				.toList();
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
