package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

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
			.map(Day02::minimalTriple)
			.mapToInt(Day02::triplePower)
			.sum();
	}

	private static boolean gameIsPossible(Game game) {
		return game.triples.allMatch(
			colorTriple -> colorTriple.red <= RED_LIMIT && colorTriple.green <= GREEN_LIMIT && colorTriple.blue <= BLUE_LIMIT
		);
	}

	static ColorTriple minimalTriple(Game game) {
		// TODO could it be custom collector time again 👀
		val savedStream = game.triples.toList();
		return new ColorTriple(
			maxOfColor(savedStream, ColorTriple::red),
			maxOfColor(savedStream, ColorTriple::green),
			maxOfColor(savedStream, ColorTriple::blue)
		);
	}

	private static int maxOfColor(List<ColorTriple> triples, ToIntFunction<ColorTriple> extractor) {
		return triples.stream()
			.mapToInt(extractor)
			.max()
			.getAsInt();
	}

	private static int triplePower(ColorTriple triple) {
		return triple.red * triple.green * triple.blue;
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
			SEMICOLON_SPLITTER.apply(remainder).map(Day02::parseTriple)
		);
	}

	private static ColorTriple parseTriple(String input) {
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
		return new ColorTriple(red, green, blue);
	}

	record Game(int id, Stream<ColorTriple> triples) {}
	record ColorTriple(int red, int green, int blue) {}
}
