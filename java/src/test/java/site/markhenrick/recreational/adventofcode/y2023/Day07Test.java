package site.markhenrick.recreational.adventofcode.y2023;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Day07Test {
	// TODO With a small enough alphabet it is probably feasible to generate an exhaustive test parameter stream
	// will probably need to write and test a permutations function first though (no libraries allowed!)
	@ParameterizedTest
	@CsvSource({
			"11111,FIVE",
			"11112,FOUR",
			"11211,FOUR",
			"11112,FOUR",
			"11222,FULL",
			"11122,FULL",
			"11212,FULL",
			"11123,THREE",
			"12232,THREE",
			"11223,TWO_PAIR",
			"12332,TWO_PAIR",
			"11234,ONE_PAIR",
			"12334,ONE_PAIR",
			"12345,HIGH",
			"54321,HIGH",
	})
	void getHandType(String input, Day07.HandType expected) {
		assertThat(Day07.getHandType(input.toCharArray())).isEqualTo(expected);
	}
}
