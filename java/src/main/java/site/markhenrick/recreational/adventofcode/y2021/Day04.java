package site.markhenrick.recreational.adventofcode.y2021;

import org.javatuples.Pair;
import site.markhenrick.recreational.common.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day04 {
	static Pair<List<Integer>, Integer[][][]> parse(String input) {
		var firstLine = input.substring(0, input.indexOf('\n'));
		var draw = StringUtil.splitToStream(",").apply(firstLine)
			.map(Integer::parseInt)
			.toList();
		var boards = StringUtil.RECORD_SPLITTER.apply(input)
			.skip(1)
			.map(Day04::parseBoard)
			.toList()
			.toArray(new Integer[0][][]);
		return Pair.with(draw, boards);
	}

	static Integer[][] parseBoard(String boardRecord) {
		return StringUtil.LINE_SPLITTER.apply(boardRecord)
			.map(line -> StringUtil.WORD_SPLITTER.apply(line)
					.filter(string -> !string.isBlank())
					.map(Integer::parseInt)
				.toList()
				.toArray(new Integer[0])
			)
			.toList()
			.toArray(new Integer[0][]);
	}

	static List<Integer> getAllResults(Integer[][][] boards, Iterator<Integer> draw) {
		validateAssumptions(boards);
		var rowCol = initialiseMarginalCounts(boards);
		var rowCounts = rowCol.getValue0();
		var colCounts = rowCol.getValue1();
		var boardHeight = boards[0].length;
		var boardWidth = boards[0][0].length;

		List<Integer> results = new ArrayList<>(boards.length);
		while (results.size() < boards.length) {
			assert draw.hasNext();
			var number = draw.next();
			for (int i = 0; i < boards.length; i++) {
				var board = boards[i];
				if (board == null) {
					continue;
				}
				var coord = crossOutNumber(board, number);
				if (coord != null) {
					var rowCount = ++rowCounts[i][coord.getValue0()];
					var colCount = ++colCounts[i][coord.getValue1()];
					if (rowCount == boardWidth || colCount == boardHeight) {
						results.add(score(board) * number);
						boards[i] = null;
					}
				}
			}
		}

		return results;
	}

	static void validateAssumptions(Integer[][][] boards) {
		assert boards.length > 0;
		var boardHeight = boards[0].length;
		assert boardHeight > 0;
		assert Arrays.stream(boards).allMatch(board -> board.length == boardHeight);
		var boardWidth = boards[0][0].length;
		assert Arrays.stream(boards).allMatch(
			board -> Arrays.stream(board)
				.allMatch(row -> row.length == boardWidth)
		);
		assert Arrays.stream(boards).allMatch( // Check that the boards don't have duplicates
			board -> Arrays.stream(board)
				.flatMap(Arrays::stream)
				.collect(Collectors.toSet())
				.size() == boardHeight * boardWidth
		);
	}

	static Pair<int[][], int[][]> initialiseMarginalCounts(Integer[][][] boards) {
		var boardHeight = boards[0].length;
		var boardWidth = boards[0][0].length;
		int[][] rowCounts = new int[boards.length][];
		Arrays.setAll(rowCounts, i -> new int[boardHeight]); // Simple int[]s always initialise zeroed
		int[][] columnCounts = new int[boards.length][];
		Arrays.setAll(columnCounts, i -> new int[boardWidth]);
		return Pair.with(rowCounts, columnCounts);
	}

	static Pair<Integer, Integer> crossOutNumber(Integer[][] board, Integer number) {
		for (var i = 0; i < board.length; i++) {
			var row = board[i];
			for (var j = 0; j < row.length; j++) {
				if (Objects.equals(board[i][j], number)) {
					board[i][j] = null;
					return Pair.with(i, j);
				}
			}
		}
		return null;
	}

	static int score(Integer[][] board) {
		return Arrays.stream(board)
			.flatMap(Arrays::stream)
			.filter(Objects::nonNull)
			.mapToInt(i -> i)
			.sum();
	}
}
