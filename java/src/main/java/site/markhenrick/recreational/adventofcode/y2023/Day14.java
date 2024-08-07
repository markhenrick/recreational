package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.CollectionUtil;

import java.util.List;

import static site.markhenrick.recreational.common.CollectionUtil.isRectangular;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.charStream;

public class Day14 {

	private static final char ROUND = 'O';
	private static final char CUBE = '#';

	static int part1(String input) {
		return weigh(parse(input));
	}

	static List<List<Character>> parse(String input) {
		val result = LINE_SPLITTER.apply(input)
			.map(Day14::parseLine)
			.toList();
		assert isRectangular(result);
		return CollectionUtil.transpose(result);
	}

	static List<Character> parseLine(String line) {
		return charStream(line).toList();
	}

	static int weigh(List<List<Character>> array) {
		return array.stream()
			.mapToInt(Day14::weighColumn)
			.sum();
	}

	static int weighColumn(List<Character> column) {
		var total = 0;
		var whereItShouldBe = 0;
		for (int actualPosition = 0; actualPosition < column.size(); actualPosition++) {
			val cell = column.get(actualPosition);
			if (cell == ROUND) {
				total += column.size() - whereItShouldBe;
				whereItShouldBe++;
			} else if (cell == CUBE) {
				whereItShouldBe = actualPosition + 1;
			}
		}
		return total;
	}
}
