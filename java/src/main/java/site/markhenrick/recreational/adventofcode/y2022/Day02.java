package site.markhenrick.recreational.adventofcode.y2022;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day02 {
	private static final char FIRST_COL_BASE = 'A';
	private static final char SND_COL_BASE = 'X';

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
			.mapToInt(line -> {
				assert line.length() == 3;
				var theirs = Shape.fromCharacter(FIRST_COL_BASE, line.charAt(0));
				assert line.charAt(1) == ' ';
				var ours = Shape.fromCharacter(SND_COL_BASE, line.charAt(2));
				return ours.score() + ours.fight(theirs);
			})
			.sum();
	}

	// Still debating whether using an enum here was the right choice over just using plain ints or chars and static methods

	public enum Shape {
		ROCK, PAPER, SCISSORS;

		private static final int SHAPE_COUNT = Shape.class.getEnumConstants().length;

		public static Shape fromCharacter(char base, char input) {
			assert input >= base && input < base + SHAPE_COUNT;
			var index = input - base;
			return Shape.class.getEnumConstants()[index];
		}

		public int score() {
			return this.ordinal() + 1;
		}

		public int fight(Shape that) {
			// SHAPE_COUNT added to prevent modulo going negative - wouldn't behave how we want
			return 3 * ((SHAPE_COUNT + this.ordinal() - that.ordinal() + 1) % SHAPE_COUNT);
		}
	}
}
