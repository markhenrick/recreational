package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.data.IntVec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static site.markhenrick.recreational.common.CollectionUtil.mutableListOf;
import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;

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

	public static int part2(String input) {
		val grid = input.split("\n");
		val numbersByGears = new HashMap<IntVec2, List<Integer>>();
		for (var i = 0; i < grid.length; i++) {
			val line = grid[i];
			val matcher = NUMBER_PATTERN.matcher(line);
			while (matcher.find()) {
				val surroundingStars = findSurroundingStars(grid, i, matcher.start(), matcher.end() - matcher.start());
				if (!surroundingStars.isEmpty()) {
					val number = Integer.parseInt(matcher.group(1));;
					for (var star : surroundingStars) {
						numbersByGears.merge(star, mutableListOf(number), applyAndReturnLeft(List::addAll));
					}
				}
			}
		}
		return numbersByGears.values().stream()
			.filter(list -> list.size() == 2)
			.mapToInt(list -> list.get(0) * list.get(1))
			.sum();
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

	// So it turns out returning a list here isn't actually necessary. It seemed like an obvious gotcha, but it appears
	// that there really are no numbers with two adjacent stars
	static List<IntVec2> findSurroundingStars(String[] grid, int row, int col, int length) {
		val list = new ArrayList<IntVec2>();
		for (var i = row - 1; i <= row + 1; i++) {
			for (var j = col - 1; j <= col + length; j++) {
				val character = safeIndex(grid, i, j);
				if (character != null && character == '*') {
					list.add(new IntVec2(i, j));
				}
			}
		}
		return list;
	}

	static Character safeIndex(String[] grid, int i, int j) {
		return i >= 0 && j >= 0 && i < grid.length && j < grid[i].length() ? grid[i].charAt(j) : null;
	}

	static boolean isSymbol(Character character) {
		return character != null && character != '.' && (character < '0' || character > '9');
	}
}
