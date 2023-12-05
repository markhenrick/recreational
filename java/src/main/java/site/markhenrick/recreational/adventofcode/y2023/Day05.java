package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day05 {
	private static final String SEEDS_PREFIX = "seeds: ";
	private static final Pattern SET_NAMES_PATTERN = Pattern.compile("^([a-z]*)-to-([a-z]*) map:$");

	public static long part1(String input) {
		val parsed = parseInput(input);
		val seeds = parsed.l();
		val fns = parsed.r();
		return seeds.stream()
				.mapToLong(seed -> applyFns(fns, seed))
				.min()
				.getAsLong();
	}

	// TODO this is just iterate() right?
	static long applyFns(List<PiecewiseFunction> fns, long input) {
		var current = input;
		for (val fn : fns) {
			current = applyFn(fn, current);
		}
		return current;
	}

	// TODO this should be a method of PiecewiseFunction
	static long applyFn(PiecewiseFunction fn, long input) {
		// Imperative for now. May convert to streams later, performance permitting
		for (val piece : fn.pieces) {
			val offset = input - piece.srcStart;
			if (offset >= 0 && offset < piece.length) {
				return piece.destStart + offset;
			}
		}
		return input;
	}

	/*
	 * Parsing code
	 */

	static Pair<List<Long>, List<PiecewiseFunction>> parseInput(String input) {
		val records = input.split("\n\n");
		assert records.length > 1;
		assert records[0].startsWith(SEEDS_PREFIX);
		val seeds = WORD_SPLITTER.apply(records[0].substring(SEEDS_PREFIX.length()))
				.map(Long::parseLong)
				.toList();

		val functions = Arrays.stream(records, 1, records.length)
				.map(Day05::parseFunction)
				.toList();
		return new Pair<>(seeds, functions);
	}

	static PiecewiseFunction parseFunction(String input) {
		val lines = input.split("\n");
		assert lines.length > 1;
		val setNames = parseSetNames(lines[0]);
		val pieces = Arrays.stream(lines, 1, lines.length)
				.map(Day05::parseFunctionPiece)
				.toList();
		return new PiecewiseFunction(setNames.l(), setNames.r(), pieces);
	}

	private static Pair<String, String> parseSetNames(String input) {
		val matcher = SET_NAMES_PATTERN.matcher(input);
		var matched = matcher.find();
		assert matched;
		assert matcher.groupCount() == 2;
		val srcName = matcher.group(1);
		val destName = matcher.group(2);
		matched = matcher.find();
		assert !matched;
		return new Pair<>(srcName, destName);
	}

	private static FunctionPiece parseFunctionPiece(String input) {
		val parts = input.split(" ");
		assert parts.length == 3;
		return new FunctionPiece(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
	}

	// TODO I'm not gonna need these names am I
	record PiecewiseFunction(String srcName, String destName, List<FunctionPiece> pieces) {}
	record FunctionPiece(long destStart, long srcStart, long length) {}
}
