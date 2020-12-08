package site.markhenrick.adventofcode.y2020;

import site.markhenrick.adventofcode.common.data.BinaryRelation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static site.markhenrick.adventofcode.common.StringUtil.LINE_SPLITTER;

class Day07 {
	private static final String TARGET = "shiny gold";

	static int solveDay1(final String input) {
		final var productions = LINE_SPLITTER.apply(input)
			.map(Production::parse)
			.collect(Collectors.toList());
		final var bagTypes = productions.stream().map(Production::lhs).collect(Collectors.toSet());
		final var canContain = new BinaryRelation<>(bagTypes);
		for (final var production : productions) {
			for (final var key : production.branches().keySet()) {
				canContain.set(key, production.lhs());
			}
		}
		canContain.transitivelyClose();
		return canContain.targetSize(TARGET);
	}

	static record Production(String lhs, Map<String, Integer> branches) {
		static Production parse(final String input) {
			// This is the worst code I've written so far for this year
			@SuppressWarnings("ProblematicWhitespace") final var words = input.split(" ");
			final var lhs = words[0] + ' ' + words[1];
			if (input.endsWith("contain no other bags.")) {
				return new Production(lhs, Collections.emptyMap());
			} else {
				final Map<String, Integer> branches = new HashMap<>(input.length() / 4 - 1);
				// If you think this is ugly, you should see the stream version that IntelliJ is suggesting
				for (var i = 4; i < words.length; i += 4) {
					branches.put(words[i + 1] + ' ' + words[i + 2], Integer.parseInt(words[i]));
				}
				return new Production(lhs, branches);
			}
		}
	}
}
