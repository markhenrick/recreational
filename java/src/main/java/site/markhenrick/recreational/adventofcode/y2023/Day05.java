package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;
import site.markhenrick.recreational.common.FunctionalUtil.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

import static site.markhenrick.recreational.common.StringUtil.WORD_SPLITTER;

public class Day05 {
	private static final String SEEDS_PREFIX = "seeds: ";

	// TODO compose all the functions *first*, then apply
	// And/or do interval arithmetic

	public static long part1(String input) {
		val parsed = parseInputP1(input);
		val seeds = parsed.l();
		val fns = parsed.r();
		return seeds.stream()
				.mapToLong(seed -> applyFns(fns, seed))
				.min()
				.getAsLong();
	}

	public static long part2(String input) {
		val parsed = parseInputP2(input);
		val ranges = parsed.l();
		val fns = parsed.r();
		return LongStream.range(0, Long.MAX_VALUE)
				.filter(i -> inRange(ranges, applyFns(fns, i)))
				.findFirst()
				.getAsLong();
	}

	// TODO instance method?
	// TODO this is just iterate() right? Or foldl1 <*> or something
	static long applyFns(List<PiecewiseFunction> fns, long input) {
		var current = input;
		for (val fn : fns) {
			current = fn.apply(current);
		}
		return current;
	}

	// TODO instance method?
	static boolean inRange(List<SeedRange> ranges, long input) {
		return ranges.stream().anyMatch(range -> input >= range.start && input < range.start + range.length);
	}

	/*
	 * Parsing code
	 */

	static Pair<List<Long>, List<PiecewiseFunction>> parseInputP1(String input) {
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

	static Pair<List<SeedRange>, List<PiecewiseFunction>> parseInputP2(String input) {
		// TODO merge duplicate code with above
		val records = input.split("\n\n");
		assert records.length > 1;
		assert records[0].startsWith(SEEDS_PREFIX);
		val seeds = parseSeedRanges(records[0].substring(SEEDS_PREFIX.length()));

		// need explicit type
		List<PiecewiseFunction> inverseFunctions = Arrays.stream(records, 1, records.length)
				.map(Day05::parseFunction)
				.map(PiecewiseFunction::inverse)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll); // need mutation
		Collections.reverse(inverseFunctions);
		return new Pair<>(seeds, inverseFunctions);
	}

	static List<SeedRange> parseSeedRanges(String input) {
		val parts = input.split(" ");
		assert parts.length % 2 == 0;
		val list = new ArrayList<SeedRange>(parts.length / 2);
		for (var i = 0; i < parts.length; i+= 2) {
			list.add(new SeedRange(Long.parseLong(parts[i]), Long.parseLong(parts[i + 1])));
		}
		return list;
	}

	static PiecewiseFunction parseFunction(String input) {
		val lines = input.split("\n");
		assert lines.length > 1;
		val pieces = Arrays.stream(lines, 1, lines.length)
				.map(Day05::parseFunctionPiece)
				.toList();
		return new PiecewiseFunction(pieces);
	}

	private static FunctionPiece parseFunctionPiece(String input) {
		val parts = input.split(" ");
		assert parts.length == 3;
		return new FunctionPiece(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
	}

	record SeedRange(long start, long length) {}

	record PiecewiseFunction(List<FunctionPiece> pieces) {
		long apply(long input) {
			// Imperative for now. May convert to streams later, performance permitting
			for (val piece : pieces) {
				val offset = input - piece.srcStart;
				if (offset >= 0 && offset < piece.length) {
					return piece.destStart + offset;
				}
			}
			return input;
		}

		PiecewiseFunction inverse() {
			return new PiecewiseFunction(
					pieces.stream()
							.map(piece -> new FunctionPiece(piece.srcStart, piece.destStart, piece.length))
							.toList()
			);
		}
	}

	record FunctionPiece(long destStart, long srcStart, long length) {}
}
