package site.markhenrick.recreational.adventofcode.y2022;

import site.markhenrick.recreational.common.StringUtil;

import java.util.ArrayDeque;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day05 {
	private static final int CRATE_PATTERN_LENGTH = "[A] ".length();
	private static final Pattern MOVE_PATTERN = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

	public static String part1(String input) {
		return executeScript(Day05::executeMove9000, input);
	}

	public static String part2(String input) {
		return executeScript(Day05::executeMove9001, input);
	}

	public static String executeScript(BiConsumer<List<ArrayDeque<Character>>, Move> crane, String input) {
		var stacks = parseSetup(input);
		parseMoves(input)
			.forEach(move -> crane.accept(stacks, move));
		return readOffStacks(stacks);
	}

	// horrible code lol

	static List<ArrayDeque<Character>> parseSetup(String input) {
		var stacks = createStacks(input);
		addCrates(stacks, input);
		return stacks;
	}

	static List<ArrayDeque<Character>> createStacks(String input) {
		var stackCountLine = StringUtil.LINE_SPLITTER.apply(input)
			.filter(line -> line.startsWith(" 1"))
			.findFirst()
			.get();
		assert stackCountLine.charAt(stackCountLine.length() - 1) == ' ';
		assert stackCountLine.charAt(stackCountLine.length() - 3) == ' ';
		var stackCount = stackCountLine.charAt(stackCountLine.length() - 2) - '0';
		// For simplicity's sake, we'll use their 1-based indexing
		var stacks = new java.util.ArrayList<>(IntStream.range(0, stackCount + 1)
			.mapToObj(i -> new ArrayDeque<Character>())
			.toList());
		stacks.set(0, null); // Make sure we definitely don't use stack 0. Also GC the tiny amount of memory it was taking
		return stacks;
	}

	static void addCrates(List<ArrayDeque<Character>> stacks, String input) {
		StringUtil.LINE_SPLITTER.apply(input)
			.takeWhile(line -> line.contains("["))
			.forEach(line -> {
				assert line.length() % CRATE_PATTERN_LENGTH == CRATE_PATTERN_LENGTH - 1;
				for (var i = 0; i * CRATE_PATTERN_LENGTH <= line.length(); i++) {
					if (line.charAt(i * CRATE_PATTERN_LENGTH) == '[') {
						var crate = line.charAt(i * CRATE_PATTERN_LENGTH + 1);
						stacks.get(i + 1).addLast(crate);
					}
				}
			});
	}

	static Stream<Move> parseMoves(String input) {
		return MOVE_PATTERN.matcher(input).results()
			.map(matchResult -> {
				assert matchResult.groupCount() == 3;
				return new Move(
					Integer.parseInt(matchResult.group(1)),
					Integer.parseInt(matchResult.group(2)),
					Integer.parseInt(matchResult.group(3))
				);
			});
	}

	static <T> void executeMove9000(List<ArrayDeque<T>> stacks, Move move) {
		stackMove(stacks.get(move.src()), stacks.get(move.dest()), move.count());
	}

	static <T> void executeMove9001(List<ArrayDeque<T>> stacks, Move move) {
		var src = stacks.get(move.src());
		var dest = stacks.get(move.dest());
		var tempStack = new ArrayDeque<T>(move.count());
		stackMove(src, tempStack, move.count());
		stackMove(tempStack, dest, move.count());
	}

	static <T> void stackMove(ArrayDeque<T> src, ArrayDeque<T> dest, int count) {
		for (var i = 0; i < count; i++) {
			dest.push(src.pop());
		}
	}

	static String readOffStacks(List<ArrayDeque<Character>> stacks) {
		var stringLength = stacks.size() - 1;
		char[] charArray = new char[stringLength];
		for (int i = 0; i < stringLength; i++) {
			var stack = stacks.get(i + 1);
			charArray[i] = stack.isEmpty() ? ' ' : stack.peek();
		}
		return new String(charArray);
	}

	public record Move(int count, int src, int dest) { }
}
