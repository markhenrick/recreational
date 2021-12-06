package site.markhenrick.adventofcode.y2021;

import java.util.List;

import static com.codepoetics.protonpack.StreamUtils.zip;
import static site.markhenrick.adventofcode.common.StringUtil.LINE_SPLITTER;

public class Day01 {
	static List<Integer> parse(String input) {
		return LINE_SPLITTER.apply(input)
			.map(Integer::parseInt)
			.toList();
	}

	static long part1(List<Integer> input) {
		return zip(input.stream(), input.stream().skip(1), (a, b) -> a < b)
			.filter(a -> a)
			.count();
	}

	static long part2(List<Integer> input) {
		// Not the most efficient implementation. See the C code for that
		var windows = zip(
			List.of(input.stream(), input.stream().skip(1), input.stream().skip(2)),
			list -> list.stream().mapToInt(a -> a).sum()
		)
			.toList();
		return part1(windows);
	}
}
