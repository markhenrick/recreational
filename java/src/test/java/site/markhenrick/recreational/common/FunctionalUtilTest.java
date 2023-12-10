package site.markhenrick.recreational.common;

import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionalUtilTest {
	@Nested
	class ZipWithTest {
		private static final List<Integer> MASTER_LEFT = List.of(1, 2, 3);
		private static final List<Integer> MASTER_RIGHT = List.of(4, 5, 6);
		private static final List<Pair<Integer, Integer>> MASTER_EXPECTED = List.of(
			new Pair<>(1, 4),
			new Pair<>(2, 5),
			new Pair<>(3, 6)
		);

		@ParameterizedTest
		@CsvSource({
			"3, 3",
			"2, 3",
			"3, 2",
			"0, 0",
		})
		void zipFiniteWithFinite(int leftCount, int rightCount) {
			val left = firstN(MASTER_LEFT, leftCount);
			val right = firstN(MASTER_RIGHT, rightCount);
			val expected = firstN(MASTER_EXPECTED, Math.min(leftCount, rightCount)).toList();
			val actual = FunctionalUtil.zipWith(FunctionalUtil.Pair::new, left, right).toList();
			assertThat(actual).isEqualTo(expected);
		}

		@Test
		void zipFiniteWithInfinite() {
			val expected = List.of(2, 4, 7);
			val actual = FunctionalUtil.zipWith(Integer::sum, MASTER_LEFT.stream(), powersOfTwo()).toList();
			assertThat(actual).isEqualTo(expected);
		}

		@Test
		void zipInfiniteWithFinite() {
			val expected = List.of(2, 4, 7);
			val actual = FunctionalUtil.zipWith(Integer::sum, powersOfTwo(), MASTER_LEFT.stream()).toList();
			assertThat(actual).isEqualTo(expected);
		}

		@Test
		void zipInfiniteWithInfinite() {
			val expected = List.of(2, 4, 8);
			val actual = FunctionalUtil.zipWith(Integer::sum, powersOfTwo(), powersOfTwo()).limit(3).toList();
			assertThat(actual).isEqualTo(expected);
		}

		private static <T> Stream<T> firstN(List<T> list, int n) {
			return list.stream().limit(n);
		}

		private static Stream<Integer> powersOfTwo() {
			return Stream.iterate(1, x -> 2 * x);
		}
	}
}
