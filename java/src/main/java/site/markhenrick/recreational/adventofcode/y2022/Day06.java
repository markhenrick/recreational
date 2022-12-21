package site.markhenrick.recreational.adventofcode.y2022;

import java.util.HashMap;

public class Day06 {
    private static final int MARKER_SIZE = 4;

    public static int part1(String input) {
        // Feel like there must be a smarter way of doing this... Probably involves zipping the stream with itself, offset by 4
        assert input.length() >= MARKER_SIZE;
        var array = input.toCharArray();
        var window = new HashMap<Character, Integer>(MARKER_SIZE); // char -> number of occurrences in window
        for (var i = 0; i < array.length; i++) {
            if (i >= MARKER_SIZE) {
                var previous = array[i - MARKER_SIZE];
                assert window.containsKey(previous);
                window.merge(previous, -1, Day06::addNullZero);
            }
            var character = array[i];
            assert !window.containsKey(character) || window.get(character) > 0;
            window.merge(character, 1, Integer::sum);
            assert window.size() <= MARKER_SIZE;
            if (window.size() == MARKER_SIZE) {
                return i + 1;
            }
        }
        return -1;
    }

    private static Integer addNullZero(int a, int b) {
        var sum = a + b;
        assert sum >= 0;
        return sum == 0 ? null : sum;
    }
}
