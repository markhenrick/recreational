package site.markhenrick.recreational.adventofcode.y2022;

import static site.markhenrick.recreational.common.FunctionalUtil.charStream;
import static site.markhenrick.recreational.common.StringUtil.LINE_SPLITTER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import site.markhenrick.recreational.common.CollectionUtil;

public class Day03 {
	private static final int BACKPACK_COMPARTMENTS = 2;
	private static final int GROUP_SIZE = 3;

	public static int part1(String input) {
		return LINE_SPLITTER.apply(input)
				.map(String::toCharArray)
				.map(charArray -> splitArray(BACKPACK_COMPARTMENTS, charArray))
				.map(Day03::findCommon)
				.mapToInt(Day03::getPriority)
				.sum();
	}

	public static int part2(String input) {
		var sponge = new Sponge<char[]>(GROUP_SIZE);
		var result = LINE_SPLITTER.apply(input)
				.map(String::toCharArray)
				.flatMap(sponge::add)
				.map(Day03::findCommon)
				.mapToInt(Day03::getPriority)
				.sum();
		assert sponge.getBufferSize() == 0;
		return result;
	}

	// Can't easily make this generic due to char being primitive
	static Stream<char[]> splitArray(int sections, char[] array) {
		assert array.length % sections == 0;
		var sectionLength = array.length / sections;
		return IntStream.range(0, sections)
				.mapToObj(i -> {
					// TODO make this zero-copy by using Arrays.asList() and sublist views
					var section = new char[sectionLength];
					System.arraycopy(array, i * sectionLength, section, 0, sectionLength);
					return section;
				});
	}

	static char findCommon(Stream<char[]> elves) {
		var intersection = elves
				.map(elf -> charStream(elf).collect(Collectors.toSet()))
				.reduce((acc, elf) -> {
					// Stream must NOT be parallel for this to work in-place
					acc.retainAll(elf);
					return acc;
				});
		assert intersection.isPresent();
		return CollectionUtil.unwrapSingleton(intersection.get());
	}

	static int getPriority(char character) {
		// ASCII/UTF8 = ... [A-Z] some punctuation [a-z] ...
		// We want a=1, z=26, A=27, Z=52
		assert character >= 'A' && character <= 'Z' || character >= 'a' && character <= 'z';
		return (character <= 'Z' ? 26 + character - 'A' : character - 'a') + 1;
	}

	/**
	 * Soak up k elements, returning an empty stream the first k-1 calls,
	 * then a singleton stream containing another stream of soaked elements
	 * then repeat
	 */
	static class Sponge<T> {
		private final int k;
		private List<T> buffer;

		Sponge(final int k)
		{
			this.k = k;
			resetBuffer();
		}

		private void resetBuffer() {
			buffer = new ArrayList<>(k);
		}

		public int getBufferSize() {
			return buffer.size();
		}

		public Stream<Stream<T>> add(T element) {
			assert buffer.size() < k;
			buffer.add(element);
			if (buffer.size() == k) {
				var stream = Stream.of(buffer.stream());
				resetBuffer();
				return stream;
			} else {
				return Stream.empty();
			}
		}
	}
}
