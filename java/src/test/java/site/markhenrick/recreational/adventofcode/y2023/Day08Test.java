package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;
import site.markhenrick.recreational.common.FunctionalUtil;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day08Test {
	private static final String SAMPLE_INPUT_1 = """
RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)
""";

	@Test
	void parseInput() {
		val expectedWalk = List.of(true, false);
		val expectedGraph = Map.of(
			"AAA", new FunctionalUtil.Pair<>("BBB", "CCC"),
			"BBB", new FunctionalUtil.Pair<>("DDD", "EEE"),
			"CCC", new FunctionalUtil.Pair<>("ZZZ", "GGG"),
			"DDD", new FunctionalUtil.Pair<>("DDD", "DDD"),
			"EEE", new FunctionalUtil.Pair<>("EEE", "EEE"),
			"GGG", new FunctionalUtil.Pair<>("GGG", "GGG"),
			"ZZZ", new FunctionalUtil.Pair<>("ZZZ", "ZZZ")
		);
		val actual = Day08.parseInput(SAMPLE_INPUT_1);
		assertThat(actual.l()).isEqualTo(expectedWalk);
		assertThat(actual.r()).isEqualTo(expectedGraph);
	}
}
