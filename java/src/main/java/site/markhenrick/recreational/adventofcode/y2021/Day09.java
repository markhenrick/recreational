package site.markhenrick.recreational.adventofcode.y2021;

import site.markhenrick.recreational.common.StringUtil;

public class Day09 {
	private static final char ZERO = '0';

	public static long part1(String input) {
		return findLowPoints(parseInput(input));
	}

	private static char[][] parseInput(String input) {
		return StringUtil.LINE_SPLITTER.apply(input)
			.map(String::trim)
			.map(String::toCharArray)
			.toArray(char[][]::new);
	}

	private static long findLowPoints(char[][] grid) {
		long total = 0;
		for (var i = 0; i < grid.length; i++) {
			for (var j = 0; j < grid[0].length; j++) {
				var value = grid[i][j];
				if (
					safeIndex(grid, i + 1, j) > value &&
						safeIndex(grid, i - 1, j) > value &&
						safeIndex(grid, i, j + 1) > value &&
						safeIndex(grid, i, j - 1) > value
				) {
					total += value - ZERO + 1;
				}
			}
		}
		return total;
	}

	private static char safeIndex(char[][] grid, int i, int j) {
		return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ? Character.MAX_VALUE : grid[i][j];
	}
}
