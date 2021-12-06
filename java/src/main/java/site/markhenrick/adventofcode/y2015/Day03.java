package site.markhenrick.adventofcode.y2015;

import site.markhenrick.adventofcode.common.data.IntVector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class Day03 {
	private static final Map<Character, IntVector> DIRECTIONS = Map.of(
		'^', new IntVector(1, 0),
		'v', new IntVector(-1, 0),
		'>', new IntVector(0, 1),
		'<', new IntVector(0, -1)
	);

	static int part1And2(String input, int workerCount) {
		var workers = new IntVector[workerCount];
		Arrays.fill(workers, new IntVector(0, 0));
		var visited = new HashSet<IntVector>();
		visited.add(new IntVector(0, 0));
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
