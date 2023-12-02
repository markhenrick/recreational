package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.StringUtil;

import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.COMMA_SPLITTER;
import static site.markhenrick.recreational.common.StringUtil.SEMICOLON_SPLITTER;

public class Day02 {
	// So this solution is an inconsistent mix of microoptimising to avoid allocation in the low methods combined with
	// flippant use of streams and string splitting in the high methods. Might refactor in one direction or the other later
	// PS. Yes I am using AmE for "color" because everything else in programming does
	private static final String GAME_START = "Game ";

	static Game parseGame(String line) {
		assert StringUtil.substringMatches(line, 0, GAME_START);
		val semicolonIndex = line.indexOf(':');
		assert semicolonIndex != -1;
		assert line.lastIndexOf(':') == semicolonIndex;
		val gameId = Integer.parseInt(line, GAME_START.length(), semicolonIndex, 10);

		val remainder = line.substring(semicolonIndex + 1);
		return new Game(
			gameId,
			SEMICOLON_SPLITTER.apply(remainder)
				.map(input -> new Hand(COMMA_SPLITTER.apply(input).map(Day02::parseHandPart)))
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
	// TODO consider if this is actually better or not
	record Game(int id, Stream<Hand> hands) {}
	record Hand(Stream<HandPart> parts) {}
	// Can't think of a better name for this
	record HandPart(int count, Color color) { }
	// lowercase because the input is
	enum Color {red, green, blue}
}
