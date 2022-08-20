package site.markhenrick.recreational.common.data;

import java.util.stream.IntStream;

public class ArrayBooleanMatrix implements BooleanMatrix {
	private final boolean[][] matrix;

	public ArrayBooleanMatrix(final int rows, final int columns) {
		this.matrix = new boolean[rows][columns];
	}


	@Override
	public boolean get(int i, int j) {
		return matrix[i][j];
	}

	@Override
	public void set(int i, int j) {
		matrix[i][j] = true;
	}

	@Override
	public int getRowCardinality(int i) {
		return (int) IntStream.range(0, matrix[i].length).filter(j -> matrix[i][j]).count();
	}
}
