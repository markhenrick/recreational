package site.markhenrick.adventofcode2020.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MiscUtilTest {
	private static final String RECORDS = """
eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946

hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
""";

	@SuppressWarnings("unused")
	static Stream<Arguments> splitLines() {
		return Stream.of(
			arguments("ab", List.of("ab")),
			arguments("ab\na", List.of("ab", "a")),
			arguments("ab\na\n", List.of("ab", "a"))
		);
	}

	@ParameterizedTest
	@MethodSource
	void splitLines(final String input, final List<String> expected) {
		assertThat(MiscUtil.LINE_SPLITTER.apply(input).collect(Collectors.toList())).isEqualTo(expected);
	}

	@Test
	void splitRecords() {
		final var records = MiscUtil.RECORD_SPLITTER.apply(RECORDS).collect(Collectors.toList());
		assertThat(records).hasSize(3);
		assertThat(records.get(1)).contains("ecl:grn");
	}
}
