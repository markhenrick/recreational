package site.markhenrick.adventofcode.y2021;

import site.markhenrick.adventofcode.common.data.IntVector;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Day02 {
	// This implementation is more about being expressive than fast
	// For that, see the C version

	private static final Map<String, IntVector> BASE_VECTORS = Map.of(
		"forward", new IntVector(1, 0),
		"up", new IntVector(0, -1),
		"down", new IntVector(0, 1)
	);

	private static final Pattern PATTERN = Pattern.compile("(\\S+) (\\d+)");

	static int part1(String input) {
		var finalVector = PATTERN.matcher(input).results()
			.map(Day02::parseLine)
			.reduce(new IntVector(0, 0), (IntVector::add));
		return finalVector.x() * finalVector.y();
	}

	static int part2(String input) {
		BinaryOperator<PositionWithAim> combinerWhichShouldNotActuallyBeCalled = (positionWithAim, positionWithAim2) -> { throw new AssertionError(); };
		var finalData = PATTERN.matcher(input).results()
			.map(Day02::parseLine)
			.reduce(new PositionWithAim(0, 0, 0), PositionWithAim::applyVector, combinerWhichShouldNotActuallyBeCalled);
		return finalData.horizontal * finalData.depth;
	}

	static IntVector parseLine(MatchResult matchResult) {
		assert matchResult.groupCount() == 2;
		var baseVector = BASE_VECTORS.get(matchResult.group(1));
		assert baseVector != null;
		var param = Integer.parseInt(matchResult.group(2));
		return baseVector.scale(param);
	}

	// Not really correct to call it a "vector" any more since the axioms don't hold
	private record PositionWithAim(int horizontal, int depth, int aim) {
		public PositionWithAim applyVector(IntVector input) {
			if (input.y() != 0) {
				assert input.x() == 0;
				return new PositionWithAim(this.horizontal, this.depth, this.aim + input.y());
			}
			if (input.x() != 0) {
				return new PositionWithAim(this.horizontal + input.x(), this.depth + this.aim * input.x(), this.aim);
			}
			return this;
		}
	}
}
