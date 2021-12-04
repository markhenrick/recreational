package site.markhenrick.adventofcode.y2021;

import site.markhenrick.adventofcode.common.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Day03 {
	private static final int INT_BIT_SIZE = 4 * 8;

	static int part1(String input) {
		var width = input.indexOf('\n');
		assert width <= INT_BIT_SIZE;
		var acc = new int[width];
		Arrays.fill(acc, 0);
		var lines = StringUtil.LINE_SPLITTER.apply(input).toList();
		var finalAcc = lines.stream() // will probably actually be the same pointer as acc
			.reduce(acc, Day03::reducer, Day03::combiner);
		var height = lines.size();
		var gamma = toInt(finalAcc, height / 2);
		var epsilonWithExtra1s = ~gamma;
		var mask = Integer.MAX_VALUE >> (INT_BIT_SIZE - width);
		var epsilon = epsilonWithExtra1s & mask;
		return gamma * epsilon;
	}

	static int part2(String input) {
		var lines = StringUtil.LINE_SPLITTER.apply(input).toList();
		return lifeSupportRating(lines, false) * lifeSupportRating(lines, true);
	}

	static int lifeSupportRating(List<String> lines, boolean isCo2) {
		assert !lines.isEmpty();
		var width = lines.get(0).length();
		assert width <= INT_BIT_SIZE;
		for (int i = 0; lines.size() > 1; i++) {
			assert i < width;
			var ones = new ArrayList<String>(lines.size());
			var zeroes = new ArrayList<String>(lines.size());
			for (var line : lines) {
				if (line.charAt(i) == '1') {
					ones.add(line);
				} else {
					zeroes.add(line);
				}
			}
			List<String> mostCommon;
			List<String> leastCommon;
			if (ones.size() >= zeroes.size()) {
				mostCommon = ones;
				leastCommon = zeroes;
			} else {
				mostCommon = zeroes;
				leastCommon = ones;
			}
			lines = isCo2 ? leastCommon : mostCommon;
		}
		return Integer.parseInt(lines.get(0), 2);
	}

	// IMPURE - Mutates acc
	static int[] reducer(int[] acc, String addendum) {
		var asCharArray = addendum.toCharArray();
		assert asCharArray.length == acc.length;
		for (int i = 0; i < asCharArray.length; i++) {
			if (asCharArray[i] == '1') {
				acc[i] += 1;
			}
		}
		return acc;
	}

	// Pure
	// Should only be called if using parallelStream
	// It would also be possible to use *only* this, and map each line to a binary int[] first
	// but even for the Java implementation, that seems kinda wasteful
	static int[] combiner(int[] left, int[] right) {
		assert left.length == right.length;
		int[] sum = new int[left.length];
		for (int i = 0; i < left.length; i++) {
			sum[i] = left[i] + right[i];
		}
		return sum;
	}

	static int toInt(int[] acc, int threshold) {
		var total = 0;
		for (int i = 0, a = 1; i < acc.length; i++, a <<= 1) {
			if (acc[acc.length - i - 1] > threshold) {
				total += a;
			}
		}
		return total;
	}
}
