package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StringUtil;
import site.markhenrick.recreational.common.data.IntVec3;

import java.util.Map;
import java.util.stream.Stream;

import static site.markhenrick.recreational.common.StringUtil.*;

public class Day02 {
	// Decided to go with an allocation-happy functional solution. The index arithmetic state machine version can go in C
	// PS. Yes I am using AmE for "color" because everything else in programming does
	private static final String GAME_START = "Game ";
	private static final Map<String, IntVec3> UNITS = Map.of(
		"red", IntVec3.UNIT_X,
		"green", IntVec3.UNIT_Y,
		"blue", IntVec3.UNIT_Z
	);
	private static final IntVec3 P1_LIMIT = new IntVec3(12, 13, 14);

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
			.map(Game::vecs)
			.map(Day02::minimalVec)
			.mapToInt(IntVec3::componentProduct)
			.sum();
	}

	private static boolean gameIsPossible(Game game) {
		return game.vecs.allMatch(vec -> vec.boundedBy(P1_LIMIT));
	}

	static IntVec3 minimalVec(Stream<IntVec3> vecs) {
		// Originally had a custom collector that used an intermediate mutable int[], still in git history
		return vecs
			.reduce((l, r) -> l.reduce(Math::max, r))
			.get();
	}

	static Game parseGame(String line) {
		// For some reason I have decided in this bit and this bit only to microoptimise to avoid allocations
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
		return COMMA_SPLITTER.apply(input)
			.map(String::trim)
			.map(colorSpec -> colorSpec.split(" "))
			.map(FunctionalUtil.Pair::fromArray)
			.map(pair -> pair.mapL(Integer::parseInt))
			.map(pair -> pair.mapR(UNITS::get))
			.map(pair -> pair.r().scale(pair.l()))
			.reduce(IntVec3.ORIGIN, IntVec3::add);
	}

	record Game(int id, Stream<IntVec3> vecs) {}
}
