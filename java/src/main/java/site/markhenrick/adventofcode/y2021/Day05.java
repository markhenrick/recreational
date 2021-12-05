package site.markhenrick.adventofcode.y2021;

import org.javatuples.Pair;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day05 {
	private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

	static Stream<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> parse(String input) {
		return PATTERN.matcher(input).results()
			.map(Day05::parseLine);
	}

	static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> parseLine(MatchResult matchResult) {
		assert matchResult.groupCount() == 4;
		return Pair.with(
			Pair.with(
				Integer.parseInt(matchResult.group(1)),
				Integer.parseInt(matchResult.group(2))
			),
			Pair.with(
				Integer.parseInt(matchResult.group(3)),
				Integer.parseInt(matchResult.group(4))
			)
		);
	}

	static int part1(Stream<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> lineStream) {
		var lines = lineStream.toList();
		var maxX = lines.stream()
			.flatMapToInt(line -> IntStream.of(line.getValue0().getValue0(), line.getValue1().getValue0()))
			.max()
			.orElseThrow();
		var maxY = lines.stream()
			.flatMapToInt(line -> IntStream.of(line.getValue0().getValue1(), line.getValue1().getValue1()))
			.max()
			.orElseThrow();
		var matrix = new int[maxX + 1][];
		Arrays.setAll(matrix, i -> new int[maxY + 1]);
		var total = 0;
		for (var line : lines) {
			var xa = line.getValue0().getValue0();
			var ya = line.getValue0().getValue1();
			var xb = line.getValue1().getValue0();
			var yb = line.getValue1().getValue1();
			var x1 = min(xa, xb);
			var x2 = max(xa, xb);
			var y1 = min(ya, yb);
			var y2 = max(ya, yb);
			if (Objects.equals(x1, x2))
			{
				// Horizontal
				for (int i = y1; i <= y2; i++) {
					if (++matrix[x1][i] == 2) {
						total++;
					}
				}
			}
			else if (Objects.equals(y1, y2))
			{
				// Vertical
				for (int i = x1; i <= x2; i++) {
					if (++matrix[i][y1] == 2) {
						total++;
					}
				}
			}
		}
		return total;
	}
}
