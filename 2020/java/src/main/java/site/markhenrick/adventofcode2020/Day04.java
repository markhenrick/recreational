package site.markhenrick.adventofcode2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Day04 {
	private static final Pattern RECORD_DELIMETER = Pattern.compile("\n\n");
	private static final Pattern PATTERN = Pattern.compile("(\\w+):([\\w#]+)");
	private static final List<String> REQUIRED_FIELDS = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

	static Stream<String> splitRecords(final String input) {
		return Arrays.stream(RECORD_DELIMETER.split(input));
	}

	static Map<String, String> parseRecord(final String record) {
		final Map<String, String> keyvals = new HashMap<>();
		final var matches = PATTERN.matcher(record);
		while (matches.find()) {
			assert matches.groupCount() == 2;
			keyvals.put(matches.group(1), matches.group(2));
		}
		return keyvals;
	}

	static boolean validateRecord(final Map<String, String> record) {
		return record.keySet().containsAll(REQUIRED_FIELDS);
	}

	static long solvePart1(final String input) {
		return splitRecords(input)
			.map(Day04::parseRecord)
			.filter(Day04::validateRecord)
			.count();
	}
}
