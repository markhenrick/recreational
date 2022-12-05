package site.markhenrick.recreational.adventofcode.y2022;

import org.javatuples.Pair;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Day04 {
	private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

	public static long part1(String input) {
		return PATTERN.matcher(input).results()
			.map(Day04::parseLine)
			.filter(pair -> oneFullyContains(pair.getValue0(), pair.getValue1())) // uncurry
			.count();
	}

	// Code here is quite similar to y2021.Day05, but not really worth the effort of generalising

	static Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> parseLine(MatchResult matchResult) {
		assert matchResult.groupCount() == 4;
		return Pair.with(
			Pair.with(
				Integer.parseInt(matchResult.group(1)),
				Integer.parseInt(matchResult.group(2))
			),
			Pair.with(
				Integer.parseInt(matchResult.group(3)),
				Integer.parseInt(matchResult.group(4))
			)
		);
	}

	static boolean oneFullyContains(Pair<Integer, Integer> left, Pair<Integer, Integer> right) {
		assert left.getValue0() <= left.getValue1() && right.getValue0() <= right.getValue1();
		if (left.getValue0().equals(right.getValue0())) {
			return true;
		}
		// This fails on equality since you could say have first=[1, 2], second=[1, 3] and the whole method will then
		// only return true in one direction. One could do a secondary sort, but the simpler solution is to realise that
		// one will always contain the other in that case, hence the above short-circuit
		var first = left.getValue0() < right.getValue0() ? left : right;
		var second = first == left ? right : left;
		return first.getValue1() >= second.getValue1();
	}
}
