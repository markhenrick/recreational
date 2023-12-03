package site.markhenrick.recreational.adventofcode.y2021;

import org.javatuples.Pair;
import site.markhenrick.recreational.common.data.IntVec2;

import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day05 {
	private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

	static Stream<Pair<IntVec2, IntVec2>> parse(String input) {
		return PATTERN.matcher(input).results()
			.map(Day05::parseLine);
	}

	static Pair<IntVec2, IntVec2> parseLine(MatchResult matchResult) {
		assert matchResult.groupCount() == 4;
		return Pair.with(
			new IntVec2(
				Integer.parseInt(matchResult.group(1)),
				Integer.parseInt(matchResult.group(2))
			),
			new IntVec2(
				Integer.parseInt(matchResult.group(3)),
				Integer.parseInt(matchResult.group(4))
			)
		);
	}

	static int part1And2(Stream<Pair<IntVec2, IntVec2>> lineStream, boolean considerDiagonals) {
		var lines = lineStream.toList();
		var maxX = lines.stream()
			.flatMapToInt(line -> IntStream.of(line.getValue0().x(), line.getValue1().x()))
			.max()
			.orElseThrow();
		var maxY = lines.stream()
			.flatMapToInt(line -> IntStream.of(line.getValue0().y(), line.getValue1().y()))
			.max()
			.orElseThrow();
		var matrix = new int[maxX + 1][];
		Arrays.setAll(matrix, i -> new int[maxY + 1]);
		var total = 0;
		for (var line : lines) {
			var start = line.getValue0();
			var end = line.getValue1();
			var vector = end.add(start.scale(-1));
			var unitVector = new IntVec2(Integer.compare(vector.x(), 0), Integer.compare(vector.y(), 0));
			var endExclusive = end.add(unitVector);

			if (!considerDiagonals && unitVector.x() != 0 && unitVector.y() != 0) {
				continue;
			}

			for (var position = start; !position.equals(endExclusive); position = position.add(unitVector)) {
				if (++matrix[position.x()][position.y()] == 2) {
					total += 1;
				}
			}
		}
		return total;
	}
}
