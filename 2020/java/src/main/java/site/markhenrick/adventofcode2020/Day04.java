package site.markhenrick.adventofcode2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class Day04 {
	private static final Pattern RECORD_DELIMITER = Pattern.compile("\n\n");
	private static final Pattern PATTERN = Pattern.compile("(\\w+):([\\w#]+)");
	private static final Pattern NUMERIC = Pattern.compile("^\\d+$");
	private static final Pattern HAIR_COLOUR = Pattern.compile("^#[0-9a-f]{6}$");
	private static final Pattern EYE_COLOUR = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
	private static final Pattern PASSPORT_ID = Pattern.compile("^\\d{9}$");
	private static final List<String> REQUIRED_FIELDS = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
	private static final List<Predicate<Map<String, String>>> FULL_REQUIREMENTS = List.of(
		Day04::validateRecordPart1,
		record -> isIntegerBetween(record.get("byr"), 1920, 2002),
		record -> isIntegerBetween(record.get("iyr"), 2010, 2020),
		record -> isIntegerBetween(record.get("eyr"), 2020, 2030),
		record -> validateHeight(record.get("hgt")),
		record -> HAIR_COLOUR.matcher(record.get("hcl")).matches(),
		record -> EYE_COLOUR.matcher(record.get("ecl")).matches(),
		record -> PASSPORT_ID.matcher(record.get("pid")).matches()
	);

	static Stream<String> splitRecords(final CharSequence input) {
		return Arrays.stream(RECORD_DELIMITER.split(input));
	}

	static Map<String, String> parseRecord(final CharSequence record) {
		final Map<String, String> keyvals = new HashMap<>();
		final var matches = PATTERN.matcher(record);
		while (matches.find()) {
			assert matches.groupCount() == 2;
			keyvals.put(matches.group(1), matches.group(2));
		}
		return keyvals;
	}

	static boolean validateRecordPart1(final Map<String, String> record) {
		return record.keySet().containsAll(REQUIRED_FIELDS);
	}

	static boolean validateRecordPart2(final Map<String, String> record) {
		return FULL_REQUIREMENTS.stream().allMatch(predicate -> predicate.test(record));
	}

	private static boolean isIntegerBetween(final String input, final int min, final int max) {
		if (!NUMERIC.matcher(input).matches()) return false;
		final var number = Integer.parseInt(input);
		return number >= min && number <= max;
	}

	private static boolean validateHeight(final String value) {
		if (value.length() < 3) return false;
		final var unit = value.substring(value.length() - 2);
		final var number = value.substring(0, value.length() - 2);
		// isIntegerBetween does a redundant numeric check. Oh well
		return switch (unit) {
			case "cm" -> isIntegerBetween(number, 150, 193);
			case "in" -> isIntegerBetween(number, 59, 76);
			default -> false;
		};
	}

	private static long countRecordsSatisfying(final Predicate<? super Map<String, String>> predicate, final CharSequence input) {
		return splitRecords(input)
			.map(Day04::parseRecord)
			.filter(predicate)
			.count();
	}

	static long solvePart1(final CharSequence input) {
		return countRecordsSatisfying(Day04::validateRecordPart1, input);
	}

	static long solvePart2(final CharSequence input) {
		return countRecordsSatisfying(Day04::validateRecordPart2, input);
	}
}
