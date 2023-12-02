package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;

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

	private static boolean gameIsPossible(Game game) {
		return game.hands.allMatch(
			hand -> hand.red <= RED_LIMIT && hand.green <= GREEN_LIMIT && hand.blue <= BLUE_LIMIT
		);
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
			SEMICOLON_SPLITTER.apply(remainder).map(Day02::parseHand)
		);
	}

	private static Hand parseHand(String input) {
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
		return new Hand(red, green, blue);
	}

	record Game(int id, Stream<Hand> hands) {}
	record Hand(int red, int green, int blue) {}
}
