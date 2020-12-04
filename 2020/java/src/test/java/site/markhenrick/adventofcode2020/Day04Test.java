package site.markhenrick.adventofcode2020;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static site.markhenrick.adventofcode2020.common.TestUtil.getResourceAsString;

class Day04Test {
	private static final String SAMPLE_INPUT = """
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
""";
	private static final String MY_INPUT = getResourceAsString("input/day04.txt");
	private static final String SINGLE_RECORD = """
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm
""";

	@Test
	void splitRecords() {
		final var records = Day04.splitRecords(SAMPLE_INPUT).collect(Collectors.toList());
		assertThat(records).hasSize(4);
		assertThat(records.get(1)).contains("ecl:amb");
	}

	@Test
	void parseRecord() {
		final var actual = Day04.parseRecord(SINGLE_RECORD);
		assertThat(actual)
			.hasSize(8)
			.containsEntry("ecl", "gry")
			.containsEntry("eyr", "2020")
			.containsEntry("hcl", "#fffffd")
			.containsEntry("hgt", "183cm");
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> validateRecords() {
		final var valid = Day04.parseRecord(SINGLE_RECORD);
		final var noCid = new HashMap<>(valid);
		noCid.remove("cid");
		final var noHgt = new HashMap<>(valid);
		noHgt.remove("hgt");
		return Stream.of(
			arguments(true, valid),
			arguments(true, noCid),
			arguments(false, noHgt)
		);
	}

	@ParameterizedTest
	@MethodSource
	void validateRecords(final boolean expected, final Map<String, String> input) {
		assertThat(Day04.validateRecord(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart1() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 2),
			arguments(MY_INPUT, 219)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart1(final String input, final int expected) {
		assertThat(Day04.solvePart1(input)).isEqualTo(expected);
	}
}
