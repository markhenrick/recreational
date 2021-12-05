package site.markhenrick.adventofcode.y2020;

import java.util.List;
import java.util.stream.Stream;

class Day03 {
	// Co-ordinates are 0-based, with x going down and y going across

	private static final IntVector DESCENT_VECTOR_P1 = new IntVector(1, 3);
	private static final List<IntVector> DESCENT_VECTORS_P2 = List.of(
		new IntVector(1, 1),
		new IntVector(1, 3),
		new IntVector(1, 5),
		new IntVector(1, 7),
		new IntVector(2, 1)
	);
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

	private long solveForDescentVector(final IntVector descentVector) {
		return Stream.iterate(
			IntVector.ORIGIN,
			location -> location.x <= maxX,
			location -> IntVector.add(location, descentVector)
		)
			.map(this::getChar)
			.filter(symbol -> symbol == TREE)
			.count();
	}

	long solvePart1() {
		return solveForDescentVector(DESCENT_VECTOR_P1);
	}

	long solvePart2() {
		return DESCENT_VECTORS_P2.stream()
			.mapToLong(this::solveForDescentVector)
			.reduce(1, (l, r) -> l*r);
	}

	record IntVector(int x, int y) {
		static final IntVector ORIGIN = new IntVector(0, 0);

		static IntVector add(final IntVector l, final IntVector r) {
			return new IntVector(l.x + r.x, l.y + r.y);
		}
	}
}
