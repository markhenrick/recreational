package site.markhenrick.recreational.adventofcode.y2021;

import site.markhenrick.recreational.common.StringUtil;

import java.util.Arrays;
import java.util.List;

public class Day06 {
	private static final int FISH_INITIAL = 8;
	private static final int FISH_WRAPAROUND = 6;

	static List<Integer> parse(String input) {
		return StringUtil.COMMA_SPLITTER.apply(input.trim())
			.map(Integer::parseInt)
			.toList();
	}

	static long part1(List<Integer> input, int iterations) {
		var fish = new long[FISH_INITIAL + 1];
		for (var i : input) {
			fish[i]++;
		}

		for (int i = 1; i <= iterations; i++) {
			fish[(FISH_WRAPAROUND + i) % fish.length] += fish[(FISH_INITIAL + i) % fish.length];
		}

		return Arrays.stream(fish).sum();
	}
}
