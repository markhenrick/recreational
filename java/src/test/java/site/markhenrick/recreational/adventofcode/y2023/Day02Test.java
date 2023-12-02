package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Day02Test {
	@Test
	void parseGame() {
		val input = "Game 34: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		val expected = new Day02.Game(
			34,
			List.of(
				new Day02.Hand(List.of(new Day02.HandPart(1, Day02.Color.green), new Day02.HandPart(3, Day02.Color.red), new Day02.HandPart(6, Day02.Color.blue))),
				new Day02.Hand(List.of(new Day02.HandPart(3, Day02.Color.green), new Day02.HandPart(6, Day02.Color.red))),
				new Day02.Hand(List.of(new Day02.HandPart(3, Day02.Color.green), new Day02.HandPart(15, Day02.Color.blue), new Day02.HandPart(14, Day02.Color.red)))
			)
		);
		assertThat(Day02.parseGame(input)).isEqualTo(expected);
	}
}
