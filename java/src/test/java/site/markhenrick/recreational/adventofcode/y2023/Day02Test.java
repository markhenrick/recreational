package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static site.markhenrick.recreational.adventofcode.y2023.Day02.Color.*;

public class Day02Test {
	@Test
	void parseGame() {
		val input = "Game 34: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
		val expectedId = 34;
		val expectedHands = List.of(
			List.of(new Day02.HandPart(1, green), new Day02.HandPart(3, red), new Day02.HandPart(6, blue)),
			List.of(new Day02.HandPart(3, green), new Day02.HandPart(6, red)),
			List.of(new Day02.HandPart(3, green), new Day02.HandPart(15, blue), new Day02.HandPart(14, red))
		);

		val result = Day02.parseGame(input);
		val hands = result.hands()
			.map(Stream::toList)
			.toList();

		assertThat(result.id()).isEqualTo(expectedId);
		assertThat(hands).isEqualTo(expectedHands);
	}
}
