package site.markhenrick.recreational.common.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BitSetBooleanMatrixTest {
	private BooleanMatrix exampleMatrix;

	@BeforeEach
	void setUp() {
		exampleMatrix = new BitSetBooleanMatrix(3);
		exampleMatrix.set(0, 1);
		exampleMatrix.set(1, 0);
		exampleMatrix.set(1, 2);
	}

	@ParameterizedTest
	@CsvSource({
		"0, 1, true",
		"1, 0, true",
		"1, 2, true",
		"0, 0, false",
		"0, 2, false",
		"1, 1, false",
		"99, 1, false",
	})
	void retention(final int i, final int j, final boolean expected) {
		assertThat(exampleMatrix.get(i, j)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"0,  1",
		"1,  2",
		"99, 0",
	})
	void rowCardinality(final int i, final int expected) {
		assertThat(exampleMatrix.getRowCardinality(i)).isEqualTo(expected);
	}
}
