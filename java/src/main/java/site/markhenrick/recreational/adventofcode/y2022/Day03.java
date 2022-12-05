package site.markhenrick.recreational.adventofcode.y2022;

import static site.markhenrick.recreational.common.FunctionalUtil.charStream;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

import java.util.stream.Collectors;

public class Day03 {
	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.map(String::toCharArray)
				.map(Day03::findDuplicate)
				.mapToInt(Day03::getPriority)
				.sum();
	}

	static char findDuplicate(char[] charArray) {
		assert charArray.length % 2 == 0;
		var leftSet = charStream(charArray)
				.limit(charArray.length / 2)
				.collect(Collectors.toSet());
		var duplicate = charStream(charArray)
				.skip(charArray.length / 2)
				.filter(leftSet::contains)
				.findAny();
		assert duplicate.isPresent();
		return duplicate.get();
	}

	static int getPriority(char character) {
		// ASCII/UTF8 = ... [A-Z] some punctuation [a-z] ...
		// We want a=1, z=26, A=27, Z=52
		assert character >= 'A' && character <= 'Z' || character >= 'a' && character <= 'z';
		return (character <= 'Z' ? 26 + character - 'A' : character - 'a') + 1;
	}
}
