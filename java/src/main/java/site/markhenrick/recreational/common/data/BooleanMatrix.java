package site.markhenrick.recreational.common.data;

public interface BooleanMatrix {
	boolean get(int i, int j);

	void set(int i, int j);

	int getRowCardinality(int i);
}
