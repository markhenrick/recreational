package site.markhenrick.recreational.adventofcode.y2015;

import site.markhenrick.recreational.common.data.IntVec2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class Day03 {
	private static final Map<Character, IntVec2> DIRECTIONS = Map.of(
		'^', new IntVec2(1, 0),
		'v', new IntVec2(-1, 0),
		'>', new IntVec2(0, 1),
		'<', new IntVec2(0, -1)
	);

	static int part1And2(String input, int workerCount) {
		var workers = new IntVec2[workerCount];
		Arrays.fill(workers, new IntVec2(0, 0));
		var visited = new HashSet<IntVec2>();
		visited.add(new IntVec2(0, 0));
		char[] charArray = input.trim().toCharArray();
		for (var i = 0; i < charArray.length; i++) {
			var vector = DIRECTIONS.get(charArray[i]);
			var worker = i % workerCount;
			var newPos = workers[worker] = workers[worker].add(vector);
			visited.add(newPos);
		}
		return visited.size();
	}

}
