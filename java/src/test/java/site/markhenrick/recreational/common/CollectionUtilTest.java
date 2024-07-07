package site.markhenrick.recreational.common;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionUtilTest {
	@Test
	void invertMapTest() {
		var input = Map.of(
			1, "a",
			2, "b",
			3, "c",
			4, "b"
		);
		var expected = Map.of(
			"a", Set.of(1),
			"b", Set.of(2, 4),
			"c", Set.of(3)
		);
		var actual = CollectionUtil.invertMap(input);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void transposeTest() {
		val input = List.of(
			List.of(1, 2, 3),
			List.of(4, 5, 6),
			List.of(7, 8, 9)
		);
		val expect = List.of(
			List.of(1, 4, 7),
			List.of(2, 5, 8),
			List.of(3, 6, 9)
		);
		val actual = CollectionUtil.transpose(input);
		assertThat(actual).isEqualTo(expect);
	}
}
