package site.markhenrick.recreational.common;

import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StatUtilTest {
	@ParameterizedTest
	@CsvSource({
		"0, 1",
		"1, 1",
		"2, 2",
		"3, 6",
		"4, 24",
		"10, 3628800",
	})
	void factorial(int n, int expected) {
		assertThat(StatUtil.factorial(n)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"0, 0, 1",
		"1, 0, 1",
		"1, 1, 1",
		"2, 0, 1",
		"2, 1, 2",
		"2, 2, 1",
		"4, 1, 4",
		"4, 2, 6",
		"7, 3, 35",
		"7, 4, 35",
	})
	void binomialCoefficients(int n, int k, int expected) {
		assertThat(StatUtil.binomialCoefficient(n, k)).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource({
		"0, 1",
		"1, 1 1",
		"2, 1 2 1",
		"3, 1 3 3 1",
		"4, 1 4 6 4 1",
		"5, 1 5 10 10 5 1",
	})
	void pascalRow(int n, String expectedRaw) {
		val expected = StringUtil.spaceSeparatedInts(expectedRaw).boxed().toList();
		assertThat(StatUtil.pascalRow(n)).isEqualTo(expected);
	}
}
