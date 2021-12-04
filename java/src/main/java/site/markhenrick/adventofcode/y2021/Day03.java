package site.markhenrick.adventofcode.y2021;

import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Day03 {
	// This implementation is more about being expressive than fast
	// For that, see the C version

	private static final Map<String, Vector> BASE_VECTORS = Map.of(
		"forward", new Vector(1, 0),
		"up", new Vector(0, -1),
		"down", new Vector(0, 1)
	);

	private static final Pattern PATTERN = Pattern.compile("(\\S+) (\\d+)");

	static int part1(String input) {
		var finalVector = PATTERN.matcher(input).results()
			.map(Day03::parseLine)
			.reduce(new Vector(0, 0), (Vector::add));
		return finalVector.x * finalVector.y;
	}

	static Vector parseLine(MatchResult matchResult) {
		assert matchResult.groupCount() == 2;
		var baseVector = BASE_VECTORS.get(matchResult.group(1));
		assert baseVector != null;
		var param = Integer.parseInt(matchResult.group(2));
		return baseVector.scale(param);
	}

	private static record Vector(int x, int y) {
		public Vector add(Vector addendum) {
			return new Vector(this.x + addendum.x, this.y + addendum.y);
		}

		public Vector scale(int scalar) {
			return new Vector(this.x * scalar, this.y * scalar);
		}
	}
}
