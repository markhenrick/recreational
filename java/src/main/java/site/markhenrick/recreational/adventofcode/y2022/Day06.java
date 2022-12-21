package site.markhenrick.recreational.adventofcode.y2022;

import java.util.HashMap;

public class Day06 {

    public static int part1(String input) {
        return findMarker(4, input);
    }

    public static int part2(String input) {
        return findMarker(14, input);
    }

    public static int findMarker(int markerSize, String input) {
        // Feel like there must be a smarter way of doing this... Probably involves zipping the stream with itself, offset by 4
        assert input.length() >= markerSize;
        var array = input.toCharArray();
        var window = new HashMap<Character, Integer>(markerSize); // char -> number of occurrences in window
        for (var i = 0; i < array.length; i++) {
            if (i >= markerSize) {
                var previous = array[i - markerSize];
                assert window.containsKey(previous);
                window.merge(previous, -1, Day06::addNullZero);
            }
            var character = array[i];
            assert !window.containsKey(character) || window.get(character) > 0;
            window.merge(character, 1, Integer::sum);
            assert window.size() <= markerSize;
            if (window.size() == markerSize) {
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
