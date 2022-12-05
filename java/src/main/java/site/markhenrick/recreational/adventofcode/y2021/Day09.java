package site.markhenrick.recreational.adventofcode.y2021;

import site.markhenrick.recreational.common.StringUtil;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

public class Day09 {
	private static final char ZERO = '0';

	public static int part1(String input) {
		var grid = parseInput(input);
		var total = new AtomicInteger(0);
		findLowPoints(grid, (i, j) -> total.addAndGet(grid[i][j] - ZERO + 1));
		return total.get();
	}

	public static int part2(String input) {
		var grid = parseInput(input);
		var basins = new ArrayList<Integer>();
		findLowPoints(grid, (i, j) -> basins.add(measureAndDestroyBasin(grid, i, j)));
		basins.sort(Integer::compareTo);
		assert basins.size() >= 3;
		return basins.get(0) * basins.get(1) * basins.get(2);
	}

	private static char[][] parseInput(String input) {
		return StringUtil.LINE_SPLITTER.apply(input)
			.map(String::trim)
			.map(String::toCharArray)
			.toArray(char[][]::new);
	}

	private static void findLowPoints(char[][] grid, BiConsumer<Integer, Integer> action) {
		for (var i = 0; i < grid.length; i++) {
			for (var j = 0; j < grid[0].length; j++) {
				var value = grid[i][j];
				if (
					safeIndex(grid, i + 1, j) > value &&
						safeIndex(grid, i - 1, j) > value &&
						safeIndex(grid, i, j + 1) > value &&
						safeIndex(grid, i, j - 1) > value
				) {
					action.accept(i, j);
				}
			}
		}
	}

	private static char safeIndex(char[][] grid, int i, int j) {
		return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length ? Character.MAX_VALUE : grid[i][j];
	}

	private static int measureAndDestroyBasin(char[][] grid, int i, int j) {
		return 0; // TODO
	}
}
