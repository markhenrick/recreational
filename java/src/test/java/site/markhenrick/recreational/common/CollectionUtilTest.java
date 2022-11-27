package site.markhenrick.recreational.common;

import org.junit.jupiter.api.Test;

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
}
