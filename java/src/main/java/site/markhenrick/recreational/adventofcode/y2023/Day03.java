package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;

import java.util.regex.Pattern;

public class Day03 {
	// TODO consider a manual state machine since it's so simple
	private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");

	public static int part1(String input) {
		val grid = input.split("\n");
		var total = 0;
		for (var i = 0; i < grid.length; i++) {
			val line = grid[i];
			val matcher = NUMBER_PATTERN.matcher(line);
			while (matcher.find()) {
				if (symbolAround(grid, i, matcher.start(), matcher.end() - matcher.start())) {
					total += Integer.parseInt(matcher.group(1));
				}
			}
		}
		return total;
	}

	static boolean symbolAround(String[] grid, int row, int col, int length) {
		for (var i = row - 1; i <= row + 1; i++) {
			for (var j = col - 1; j <= col + length; j++) {
				// yes this does scan over the numbers itself. Not a big enough deal for me to fix atm, and doing so may
				// even screw with branch prediction and caches
				if (isSymbol(safeIndex(grid, i, j))) {
					return true;
				}
			}
		}
		return false;
	}

	static Character safeIndex(String[] grid, int i, int j) {
		return i >= 0 && j >= 0 && i < grid.length && j < grid[i].length() ? grid[i].charAt(j) : null;
	}

	static boolean isSymbol(Character character) {
		return character != null && character != '.' && (character < '0' || character > '9');
	}
}
