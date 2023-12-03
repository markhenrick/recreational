package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.IntVec3;
import site.markhenrick.recreational.common.StringUtil;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.SEMICOLON_SPLITTER;

public class Day02 {
	// So this solution is an inconsistent mix of microoptimising to avoid allocation in the low methods combined with
	// flippant use of streams and string splitting in the high methods. Might refactor in one direction or the other later
	// PS. Yes I am using AmE for "color" because everything else in programming does
	private static final String GAME_START = "Game ";
	private static final String RED = "red";
	private static final String GREEN = "green";
	private static final String BLUE = "blue";
	private static final int RED_LIMIT = 12;
	private static final int GREEN_LIMIT = 13;
	private static final int BLUE_LIMIT = 14;

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
			.map(Day02::parseGame)
			.filter(Day02::gameIsPossible)
			.mapToInt(Game::id)
			.sum();
	}

	public static int part2(String input) {
		return LINE_SPLITTER.apply(input)
			.map(Day02::parseGame)
			.map(Day02::minimalVec)
			.mapToInt(Day02::vecPower)
			.sum();
	}

	private static boolean gameIsPossible(Game game) {
		return game.vecs.allMatch(
			IntVec3 -> IntVec3.x() <= RED_LIMIT && IntVec3.y() <= GREEN_LIMIT && IntVec3.z() <= BLUE_LIMIT
		);
	}

	static IntVec3 minimalVec(Game game) {
		return game.vecs.collect(new MinimalVecCollector());
	}

	private static int vecPower(IntVec3 vec) {
		return vec.x() * vec.y() * vec.z();
	}

	static Game parseGame(String line) {
		assert StringUtil.substringMatches(line, 0, GAME_START);
		val semicolonIndex = line.indexOf(':');
		assert semicolonIndex != -1;
		assert line.lastIndexOf(':') == semicolonIndex;
		val gameId = Integer.parseInt(line, GAME_START.length(), semicolonIndex, 10);

		val remainder = line.substring(semicolonIndex + 1);
		return new Game(
			gameId,
			SEMICOLON_SPLITTER.apply(remainder).map(Day02::parseVec)
		);
	}

	private static IntVec3 parseVec(String input) {
		var red = 0;
		var green = 0;
		var blue = 0;
		val parts = input.split(",");
		for (val part : parts) {
			val leadingSpace = part.charAt(0) == ' ';
			val spaceIndex = part.indexOf(' ', leadingSpace ? 1 : 0);
			assert spaceIndex != -1;
			assert part.lastIndexOf(' ') == spaceIndex;
			val count = Integer.parseInt(part, leadingSpace ? 1 : 0, spaceIndex, 10);
			val color = part.substring(spaceIndex + 1);
			switch (color) {
				case RED:
					assert red == 0;
					red += count;
					break;
				case GREEN:
					assert green == 0;
					green += count;
					break;
				case BLUE:
					assert blue == 0;
					blue += count;
					break;
				default:
					assert false;
			}
		}
		return new IntVec3(red, green, blue);
	}

	record Game(int id, Stream<IntVec3> vecs) {}

	private static class MinimalVecCollector implements Collector<IntVec3, int[], IntVec3> {
		private static final Set<Characteristics> CHARACTERISTICS = Set.of(Characteristics.UNORDERED);

		@Override
		public Supplier<int[]> supplier() {
			return () -> new int[3];
		}

		@Override
		public BiConsumer<int[], IntVec3> accumulator() {
			return (counts, vec) -> {
				counts[0] = max(counts[0], vec.x());
				counts[1] = max(counts[1], vec.y());
				counts[2] = max(counts[2], vec.z());
			};
		}

		@Override
		public BinaryOperator<int[]> combiner() {
			return (counts0, counts1) -> {
				counts0[0] = max(counts0[0], counts1[0]);
				counts0[1] = max(counts0[1], counts1[1]);
				counts0[2] = max(counts0[2], counts1[2]);
				return counts0;
			};
		}

		@Override
		public Function<int[], IntVec3> finisher() {
			return counts -> new IntVec3(counts[0], counts[1], counts[2]);
		}

		@Override
		public Set<Characteristics> characteristics() {
			return CHARACTERISTICS;
		}
	}
}
