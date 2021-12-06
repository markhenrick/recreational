package site.markhenrick.adventofcode.y2015;

import site.markhenrick.adventofcode.common.data.IntVector;

import java.util.HashSet;

public class Day03 {
	static int part1(String input) {
		int x = 0;
		int y = 0;
		var visited = new HashSet<IntVector>();
		visited.add(new IntVector(x, y));
		for (var dir : input.trim().toCharArray()) {
			switch (dir) {
				case '^' -> x++;
				case 'v' -> x--;
				case '>' -> y++;
				case '<' -> y--;
				default -> throw new IllegalArgumentException("Unknown direction " + dir);
			}
			visited.add(new IntVector(x, y));
		}
		return visited.size();
	}
}
