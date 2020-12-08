package site.markhenrick.adventofcode.common.data;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BinaryRelation<A> {
	private final Map<A, Integer> domain; // probably not the best solution tbh
	private final BooleanMatrix adjacencyMatrix;

	public BinaryRelation(final Collection<A> domain) {
		this.domain = new LinkedHashMap<>(domain.size());
		final var iterator = domain.iterator();
		for (var i = 0; iterator.hasNext(); i++) {
			this.domain.put(iterator.next(), i);
		}
		this.adjacencyMatrix = new BooleanMatrix(domain.size());
	}

	public boolean get(final A source, final A target) {
		assert domain.containsKey(source);
		assert domain.containsKey(target);
		return adjacencyMatrix.get(domain.get(source), domain.get(target));
	}

	public void set(final A source, final A target) {
		assert domain.containsKey(source);
		assert domain.containsKey(target);
		adjacencyMatrix.set(domain.get(source), domain.get(target));
	}

	// TODO better name
	public int targetSize(final A source) {
		return adjacencyMatrix.getRowCardinality(domain.get(source));
	}

	public void transitivelyClose() {
		// Floyd-Warshall Al Gore Rhythm
		for (var k = 0; k < domain.size(); k++) {
			for (var i = 0; i < domain.size(); i++) {
				for (var j = 0; j < domain.size(); j++) {
					if (adjacencyMatrix.get(i, k) && adjacencyMatrix.get(k, j)) {
						adjacencyMatrix.set(i, j);
					}
				}
			}
		}
	}

	public String toString() {
		final var sb = new StringBuilder("[");
		for (final var key : domain.keySet()) {
			sb.append(key).append(", ");
		}
		sb.append("]\n\n");
		sb.append(adjacencyMatrix);
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
