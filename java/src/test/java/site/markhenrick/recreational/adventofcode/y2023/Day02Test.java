package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {
	@Test
	void parseGame() {
		val input = "Game 34: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		val expectedId = 34;
		val expectedHands = List.of(
			new Day02.Hand(3, 1, 6),
			new Day02.Hand(6, 3, 0),
			new Day02.Hand(14, 3, 15)
		);

		val result = Day02.parseGame(input);

		assertThat(result.id()).isEqualTo(expectedId);
		assertThat(result.hands().toList()).isEqualTo(expectedHands);
	}
}
