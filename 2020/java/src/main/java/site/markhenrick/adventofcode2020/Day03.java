package site.markhenrick.adventofcode2020;

import java.util.stream.Stream;

class Day03 {
	// Co-ordinates are 0-based, with x going down and y going across

	private static final IntVector DESCENT_VECTOR = new IntVector(1, 3);
	private static final char BLANK = '.';
	private static final char TREE = '#';

	private final char[] input;
	private final int maxY;
	private final int maxX;

	Day03(final String input) {
		this.input = input.toCharArray();
		this.maxY = input.indexOf('\n') ;
		this.maxX = (input.length() / (maxY + 1)) - 1;
	}

	char getChar(final IntVector coordinate) {
		assert coordinate.x <= maxX;
		// +1 due to the \n
		final var character = input[(coordinate.x * (maxY + 1)) + (coordinate.y % maxY)];
		assert character == BLANK || character == TREE;
		return character;
	}

	int solvePart1() {
		return (int) Stream.iterate(
			IntVector.ORIGIN,
			location -> location.x <= maxX,
			location -> IntVector.add(location, DESCENT_VECTOR)
		)
			.map(this::getChar)
			.filter(symbol -> symbol == TREE)
			.count();
	}

	static record IntVector(int x, int y) {
		static IntVector ORIGIN = new IntVector(0, 0);

		static IntVector add(final IntVector l, final IntVector r) {
			return new IntVector(l.x + r.x, l.y + r.y);
		}
	}
}
