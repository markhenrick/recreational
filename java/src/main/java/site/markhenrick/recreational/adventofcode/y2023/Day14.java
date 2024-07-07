package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.CollectionUtil;

import java.util.Arrays;
import java.util.List;

import static site.markhenrick.recreational.common.CollectionUtil.isRectangular;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.charStream;

public class Day14 {

	static int part1(String input) {
		return weigh(parse(input));
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

	static int weigh(List<List<Cell>> array) {
		return array.stream()
			.mapToInt(Day14::weighColumn)
			.sum();
	}

	static int weighColumn(List<Cell> column) {
		var total = 0;
		var whereItShouldBe = 0;
		for (int actualPosition = 0; actualPosition < column.size(); actualPosition++) {
			val cell = column.get(actualPosition);
			if (cell == Cell.ROUND) {
				total += column.size() - whereItShouldBe;
				whereItShouldBe++;
			} else if (cell == Cell.CUBE) {
				whereItShouldBe = actualPosition + 1;
			}
		}
		return total;
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
