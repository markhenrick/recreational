package site.markhenrick.adventofcode.common.data;

import java.util.BitSet;
import java.util.Objects;
import java.util.stream.IntStream;

class BooleanMatrix {
	private final int rowLength;
	// I should look into negotiating a bulk discount from the BitSet factory
	private final BitSet bitSet;

	BooleanMatrix(final int rowLength) {
		this.rowLength = rowLength;
		this.bitSet = new BitSet();
	}

	private int getIndex(final int i, final int j) {
		assert j < rowLength;
		return i * rowLength + j;
	}

	boolean get(final int i, final int j) {
		return bitSet.get(getIndex(i, j));
	}

	void set(final int i, final int j) {
		bitSet.set(getIndex(i, j));
	}

	int getRowCardinality(final int i) {
		final var start = i * rowLength;
		final var stop = start + rowLength;
		return (int) IntStream.range(start, stop).filter(bitSet::get).count();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final var that = (BooleanMatrix) o;
		return rowLength == that.rowLength && Objects.equals(bitSet, that.bitSet);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rowLength, bitSet);
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder();
		for (var i = 0; i < rowLength; i++) {
			for (var j = 0; j < rowLength; j++) {
				sb.append(bitSet.get(getIndex(i, j)) ? '1' : '0').append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
}
