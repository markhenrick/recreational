package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.CollectionUtil.isRectangular;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.charStream;

public class Day14 {

	static int part1(String input) {
		return weigh(roll(parse(input)));
	}

	static List<List<Cell>> parse(String input) {
		val result = LINE_SPLITTER.apply(input)
			.map(Day14::parseLine)
			.toList();
		assert isRectangular(result);
		return CollectionUtil.transpose(result);
	}

	static List<Cell> parseLine(String line) {
		return charStream(line).map(Cell::of).toList();
	}

	static List<List<Cell>> roll(List<List<Cell>> array) {
		return array.stream()
			.map(Day14::rollColumn)
			.toList();
	}

	static List<Cell> rollColumn(List<Cell> column) {
		val newColumn = new ArrayList<Cell>(column.size());
		for (int i = 0; i < column.size(); i++) {
			val cell = column.get(i);
			if (cell == Cell.ROUND) {
				newColumn.add(Cell.ROUND);
			} else if (cell == Cell.CUBE) {
				topUpColumn(newColumn, i);
				newColumn.add(Cell.CUBE);
			}
		}
		topUpColumn(newColumn, column.size());
		assert newColumn.size() == column.size();
		return newColumn;
	}

	private static void topUpColumn(List<Cell> newColumn, int expectedHeight) {
		val extraSpacesNeeded = expectedHeight - newColumn.size();
		assert extraSpacesNeeded >= 0;
		for (int j = 0; j < extraSpacesNeeded; j++) {
			newColumn.add(Cell.EMPTY);
		}
	}

	static int weigh(List<List<Cell>> array) {
		return array.stream()
			.mapToInt(Day14::weighColumn)
			.sum();
	}

	static int weighColumn(List<Cell> column) {
		return IntStream.range(0, column.size())
			.map(i -> {
				val cell = column.get(i);
				if (cell != Cell.ROUND) return 0;
				return column.size() - i;
			})
			.sum();
	}

	private enum Cell {
		ROUND('O'),
		CUBE('#'),
		EMPTY('.');

		private final char character;

		Cell(char character) {
			this.character = character;
		}

		@Override
		public String toString() {
			return "" + character;
		}

		static Cell of(char character) {
			// Very inefficient lol
			return Arrays.stream(Cell.values())
				.filter(cell -> cell.character == character)
				.findFirst()
				.get();
		}
	}
}
