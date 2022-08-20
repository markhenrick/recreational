package site.markhenrick.recreational.adventofcode.y2020;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import site.markhenrick.recreational.common.TestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SuppressWarnings("SpellCheckingInspection")
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
	// first four only are invalid
	private static final String SAMPLE_INPUT_2 = """
eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007

pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f

eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022

iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
""";
	private static final String MY_INPUT = TestUtil.getResourceAsString("AoC/input/2020/day04.txt");
	private static final String SINGLE_RECORD = """
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm
""";
	private static final Map<String, String> VALID_RECORD = Day04.parseRecord(SINGLE_RECORD);

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

	@ParameterizedTest(name = "No {1} key is valid = {0}")
	@CsvSource({
		"true,  UNUSED_KEY",
		"true,  cid",
		"false, hgt",
	})
	void validateRecordsPart1(final boolean expected, final String keyToRemove) {
		final var input = derivedMap(VALID_RECORD, keyToRemove, null);
		assertThat(Day04.validateRecordPart1(input)).isEqualTo(expected);
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

	@ParameterizedTest(name = "<{1}: {2}> is valid = {0}")
	@CsvSource({
		"true,  UNUSED_KEY, [null]",
		"true,  cid, [null]",
		"false, hgt, [null]",
		"true,  byr, 1920",
		"true,  byr, 1921",
		"true,  byr, 2001",
		"true,  byr, 2002",
		"false, byr, 2003",
		"false, byr, 1919",
		"false, byr, a",
		"false, byr, 1",
		"true,  iyr, 2010",
		"true,  iyr, 2011",
		"true,  iyr, 2019",
		"true,  iyt, 2020",
		"false, iyr, 2021",
		"false, iyr, 2009",
		"false, iyr, a",
		"false, iyr, 1",
		"true,  eyr, 2020",
		"true,  eyr, 2021",
		"true,  eyr, 2029",
		"true,  eyr, 2030",
		"false, eyr, 2019",
		"false, eyr, 2031",
		"false, eyr, a",
		"false, eyr, 1",
		"true,  hgt, 59in",
		"true,  hgt, 60in",
		"true,  hgt, 75in",
		"true,  hgt, 76in",
		"true,  hgt, 150cm",
		"true,  hgt, 151cm",
		"true,  hgt, 192cm",
		"true,  hgt, 193cm",
		"false, hgt, 58in",
		"false, hgt, 77in",
		"false, hgt, 149cm",
		"false, hgt, 194cm",
		"false, hgt, 60",
		"false, hgt, 190",
		"false, hgt, a",
		"true,  hcl, #ffffff",
		"true,  hcl, #000000",
		"true,  hcl, #0ff00f",
		"false, hcl, #000",
		"false, hcl, ffffff",
		"false, hcl, 000000",
		"false, hcl, #gggggg",
		"true,  ecl, amb",
		"true,  ecl, blu",
		"true,  ecl, brn",
		"true,  ecl, gry",
		"true,  ecl, grn",
		"true,  ecl, hzl",
		"true,  ecl, oth",
		"false, ecl, abc",
		"false, ecl, blue",
		"false, ecl, 1",
		"true,  pid, 123456789",
		"true,  pid, 000000001",
		"true,  pid, 100000000",
		"false, pid, abcdef123",
		"false, pid, 123",
		"false, pid, a123456789a",
		"false, pid, a2345678a",
		"false, pid, 123456789a",
		"false, pid, 2345678a",
		"false, pid, a123456789",
		"false, pid, a2345678",
		"false, pid, a",
	})
	void validateRecordsPart2(final boolean expected, final String key, final String value) {
		final var input = derivedMap(VALID_RECORD, key, "[null]".equals(value) ? null : value);
		assertThat(Day04.validateRecordPart2(input)).isEqualTo(expected);
	}

	@SuppressWarnings("unused")
	static Stream<Arguments> solvePart2() {
		return Stream.of(
			arguments(SAMPLE_INPUT, 2),
			arguments(SAMPLE_INPUT_2, 4),
			arguments(MY_INPUT, 127)
		);
	}

	@ParameterizedTest
	@MethodSource
	void solvePart2(final String input, final int expected) {
		assertThat(Day04.solvePart2(input)).isEqualTo(expected);
	}

	private static <A, B> Map<A, B> derivedMap(final Map<A, B> original, final A key, final B newValue) {
		final var derived = new HashMap<>(original);
		if (newValue == null) {
			derived.remove(key);
		} else {
			derived.put(key, newValue);
		}
		return derived;
	}
}
