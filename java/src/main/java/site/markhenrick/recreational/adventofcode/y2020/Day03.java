package site.markhenrick.recreational.adventofcode.y2020;

import site.markhenrick.recreational.common.data.IntVec2;

import java.util.List;
import java.util.stream.Stream;

class Day03 {
	// Co-ordinates are 0-based, with x going down and y going across

	private static final IntVec2 DESCENT_VECTOR_P1 = new IntVec2(1, 3);
	private static final List<IntVec2> DESCENT_VECTORS_P2 = List.of(
		new IntVec2(1, 1),
		new IntVec2(1, 3),
		new IntVec2(1, 5),
		new IntVec2(1, 7),
		new IntVec2(2, 1)
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

	char getChar(final IntVec2 coordinate) {
		assert coordinate.x() <= maxX;
		// +1 due to the \n
		final var character = input[(coordinate.x() * (maxY + 1)) + (coordinate.y() % maxY)];
		assert character == BLANK || character == TREE;
		return character;
	}

	private long solveForDescentVector(final IntVec2 descentVector) {
		return Stream.iterate(
			IntVec2.ORIGIN,
			location -> location.x() <= maxX,
			location -> location.add(descentVector)
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
}
