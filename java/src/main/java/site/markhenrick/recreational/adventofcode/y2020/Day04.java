package site.markhenrick.recreational.adventofcode.y2020;

import site.markhenrick.recreational.common.FunctionalUtil;
import site.markhenrick.recreational.common.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

class Day04 {
	private static final Pattern PATTERN = Pattern.compile("(\\w+):([\\w#]+)");
	private static final Pattern NUMERIC = Pattern.compile("^\\d+$");
	private static final Pattern HAIR_COLOUR = Pattern.compile("^#[0-9a-f]{6}$");
	private static final Pattern EYE_COLOUR = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
	private static final Pattern PASSPORT_ID = Pattern.compile("^\\d{9}$");
	private static final Map<String, Predicate<String>> REQUIREMENTS = Map.of(
		"byr", isIntegerBetween(1920, 2002),
		"iyr", isIntegerBetween(2010, 2020),
		"eyr", isIntegerBetween(2020, 2030),
		"hgt", Day04::validateHeight,
		"hcl", HAIR_COLOUR.asMatchPredicate(),
		"ecl", EYE_COLOUR.asMatchPredicate(),
		"pid", PASSPORT_ID.asMatchPredicate()
	);

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
		return record.keySet().containsAll(REQUIREMENTS.keySet());
	}

	static boolean validateRecordPart2(final Map<String, String> record) {
		return REQUIREMENTS.entrySet().stream()
			.allMatch(entry -> {
				final var value = record.get(entry.getKey());
				return value != null && entry.getValue().test(value);
			});
	}

	private static Predicate<String> isIntegerBetween(final int min, final int max) {
		return input -> {
			if (!NUMERIC.matcher(input).matches()) return false;
			final var number = Integer.parseInt(input);
			return number >= min && number <= max;
		};
	}

	private static boolean validateHeight(final String value) {
		if (value.length() < 3) return false;
		final var unit = value.substring(value.length() - 2);
		final var number = value.substring(0, value.length() - 2);
		// isIntegerBetween does a redundant numeric check. Oh well
		final Predicate<String> predicate = switch (unit) {
			case "cm" -> isIntegerBetween(150, 193);
			case "in" -> isIntegerBetween(59, 76);
			default -> FunctionalUtil.constantPredicate(false);
		};
		return predicate.test(number);
	}

	private static long countRecordsSatisfying(final Predicate<? super Map<String, String>> predicate, final String input) {
		return StringUtil.RECORD_SPLITTER.apply(input)
			.map(Day04::parseRecord)
			.filter(predicate)
			.count();
	}

	static long solvePart1(final String input) {
		return countRecordsSatisfying(Day04::validateRecordPart1, input);
	}

	static long solvePart2(final String input) {
		return countRecordsSatisfying(Day04::validateRecordPart2, input);
	}
}
