package site.markhenrick.adventofcode2020;

import java.util.ArrayList;
import java.util.regex.Pattern;

class Day02 {
	private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (\\w+)");

	static ArrayList<PasswordAndPolicy> parse(final CharSequence input) {
		final var passwordsAndPolicies = new ArrayList<PasswordAndPolicy>();
		final var matches = PATTERN.matcher(input);
		while (matches.find()) {
			assert matches.groupCount() == 4;
			final var min = Integer.parseInt(matches.group(1));
			final var max = Integer.parseInt(matches.group(2));
			assert min <= max;
			assert matches.group(3).length() == 1;
			final var character = matches.group(3).charAt(0);
			final var password = matches.group(4);
			passwordsAndPolicies.add(new PasswordAndPolicy(min, max, character, password));
		}
		return passwordsAndPolicies;
	}

	// Arrays.stream doesn't exist for char[], so commented out
//	static boolean simpleValidatePassword(PasswordAndPolicy passwordAndPolicy) {
//		final var count = Arrays.stream(passwordAndPolicy.password.toCharArray())
//			.filter(character -> character == passwordAndPolicy.character)
//			.count();
//		return count >= passwordAndPolicy.min && count <= passwordAndPolicy.max;
//	}

	static boolean fastValidatePasswordPart1(final PasswordAndPolicy passwordAndPolicy) {
		var count = 0;
		for (final var character : passwordAndPolicy.password.toCharArray()) {
			if (character == passwordAndPolicy.character) {
				count++;
				if (count > passwordAndPolicy.max) {
					return false;
				}
			}
		}
		return count >= passwordAndPolicy.min;
	}

	static long solvePart1(final CharSequence input) {
		return parse(input).stream()
			.filter(Day02::fastValidatePasswordPart1)
			.count();
	}

	static boolean validatePasswordPart2(final PasswordAndPolicy passwordAndPolicy) {
		return checkChar(passwordAndPolicy.password, passwordAndPolicy.character, passwordAndPolicy.min)
			^ checkChar(passwordAndPolicy.password, passwordAndPolicy.character, passwordAndPolicy.max);
	}

	private static boolean checkChar(final CharSequence password, final char character, final int oneBasedIndex) {
		assert oneBasedIndex > 0 && oneBasedIndex <= password.length();
		return password.charAt(oneBasedIndex - 1) == character;
	}

	static long solvePart2(final CharSequence input) {
		return parse(input).stream()
			.filter(Day02::validatePasswordPart2)
			.count();
	}

	static record PasswordAndPolicy(int min, int max, char character, String password) {
	}
}
