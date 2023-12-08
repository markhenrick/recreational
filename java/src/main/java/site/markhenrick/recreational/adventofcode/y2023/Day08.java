package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;
import site.markhenrick.recreational.common.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

public class Day08 {
	// true = right, false = left

	static Pair<List<Boolean>, Map<String, Pair<String, String>>> parseInput(String input) {
		val records = input.split("\n\n");
		assert records.length == 2;
		val walk = StringUtil.charStream(records[0])
			.map(Day08::parseWalk)
			.toList();
		val graph = parseGraph(records[1]); // type inference fails when inlined; methods are nicer than unchecked casts
		assert graphIsValid(graph);
		return new Pair<>(walk, graph);
	}

	private static boolean parseWalk(char character) {
		assert character == 'L' || character == 'R'; // Obligatory note on this not being prod code
		return character == 'R';
	}

	private static Map<String, Pair<String, String>> parseGraph(String input) {
		return LINE_SPLITTER.apply(input)
			.map(Day08::parseEdge)
			.collect(HashMap::new, (map, pair) -> map.put(pair.l(), pair.r()), HashMap::putAll);
	}

	private static Pair<String, Pair<String, String>> parseEdge(String line) {
		// truly excellent parsing code here
		// tbf it is pretty good in terms of efficiency

		// Not gonna bother defining constants for magic numbers when they're only used here
		assert line.length() == 16;
		assert line.charAt(4) == '=';
		assert line.charAt(10) == ',';
		val source = line.substring(0, 3);
		val leftDest = line.substring(7, 10);
		val rightDest = line.substring(12, 15);
		return new Pair<>(source, new Pair<>(leftDest, rightDest));
	}

	private static boolean graphIsValid(Map<String, Pair<String, String>> graph) {
		// No dangling node references. Unreachable nodes are fine
		return graph.values().stream().allMatch(edge -> graph.containsKey(edge.l()) && graph.containsKey(edge.r()));
	}
}
