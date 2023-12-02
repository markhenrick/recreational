package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;

import java.util.List;

import static site.markhenrick.recreational.common.StringUtil.*;

public class Day02 {
	// Yes I am using AmE for "color" because everything else in programming does
	private static final String GAME_START = "Game ";

	static Game parseGame(String line) {
		// Originally had a zero-allocation index-arithmetic version, but it was getting too complex to understand
		// I'll save that for the C version if I get round to it. Streams and regex are just too convenient

		// But I'll at least do the game ID part vaguely efficiently
		assert StringUtil.substringMatches(line, 0, GAME_START);
		val semicolonIndex = line.indexOf(':');
		assert semicolonIndex != -1;
		assert line.lastIndexOf(':') == semicolonIndex;
		val gameId = Integer.parseInt(line, GAME_START.length(), semicolonIndex, 10);

		val remainder = line.substring(semicolonIndex + 1);
		return new Game(
			gameId,
			SEMICOLON_SPLITTER.apply(remainder)
				.map(Day02::parseHand)
				.toList() // TODO no list
		);
	}

	static Hand parseHand(String input) {
		return new Hand(
			COMMA_SPLITTER.apply(input)
				.map(Day02::parseHandPart)
				.toList() // TODO no list
		);
	}

	static HandPart parseHandPart(String input) {
		val leadingSpace = input.charAt(0) == ' ';
		val spaceIndex = input.indexOf(' ', leadingSpace ? 1 : 0);
		assert spaceIndex != -1;
		assert input.lastIndexOf(' ') == spaceIndex;
		return new HandPart(
			Integer.parseInt(input, leadingSpace ? 1 : 0, spaceIndex, 10),
			// So technically this still results in an allocation, but it will be eligible for collection almost instantly
			// a more efficient method using StringUtil.substringMatches would be possible
			Color.valueOf(input.substring(spaceIndex + 1))
		);
	}


	// Decided we're not just going to use generic Pair<>s today
	record Game(int id, List<Hand> hands) {}
	record Hand(List<HandPart> parts) {}
	// Can't think of a better name for this
	record HandPart(int count, Color color) { }
	// lowercase because the input is
	enum Color {red, green, blue}
}
