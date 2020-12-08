package site.markhenrick.adventofcode.y2020;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
			for (final var key : production.branches.keySet()) {
				canContain.set(key, production.lhs);
			}
		}
		canContain.transitivelyClose();
		return canContain.targetSize(TARGET);
	}

	// Could make an enum for bag colours I guess
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
				for (var i = 4; i < words.length; i+= 4) {
					branches.put(words[i+1] + ' ' + words[i+2], Integer.parseInt(words[i]));
				}
				return new Production(lhs, branches);
			}
		}
	}

	static class BinaryRelation<A> {
		private final Map<A, Integer> domain; // probably not the best solution tbh
		// I should look into negotiating a bulk discount from the BitSet factory
		private final BitSet adjacencyMatrix;

		BinaryRelation(final Collection<A> domain) {
			this.domain = new LinkedHashMap<>(domain.size());
			final var iterator = domain.iterator();
			for (var i = 0; iterator.hasNext(); i++) {
				this.domain.put(iterator.next(), i);
			}
			this.adjacencyMatrix = new BitSet(domain.size() * domain.size());
		}

		private int getIndex(final A source, final A target) {
			assert domain.containsKey(source);
			assert domain.containsKey(target);
			return domain.get(source) * domain.size() + domain.get(target);
		}

		public boolean get(final A source, final A target) {
			return adjacencyMatrix.get(getIndex(source, target));
		}

		public void set(final A source, final A target) {
			adjacencyMatrix.set(getIndex(source, target));
		}

		// TODO better name
		int targetSize(final A source) {
			// could also use get(int, int).cardinality()
			final var start = domain.get(source) * domain.size();
			final var stop = start + domain.size();
			return (int) IntStream.range(start, stop).filter(adjacencyMatrix::get).count();
		}

		void transitivelyClose() {
			// TODO
		}

		public String toString() {
			final var sb = new StringBuilder("[");
			for (final var key : domain.keySet()) {
				sb.append(key).append(", ");
			}
			sb.append("]\n\n");
			for (var i = 0; i < domain.size(); i++) {
				for (var j = 0; j < domain.size(); j++) {
					sb.append(adjacencyMatrix.get(i * domain.size() + j) ? '1' : '0').append(' ');
				}
				sb.append('\n');
			}
			return sb.toString();
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			final var that = (BinaryRelation<?>) o;
			return Objects.equals(domain, that.domain) && Objects.equals(adjacencyMatrix, that.adjacencyMatrix);
		}

		@Override
		public int hashCode() {
			return Objects.hash(domain, adjacencyMatrix);
		}
	}
}
