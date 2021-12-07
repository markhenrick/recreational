package site.markhenrick.adventofcode.y2021;

import site.markhenrick.adventofcode.common.StringUtil;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Day07 {
	static int[] parse(String input) {
		return StringUtil.COMMA_SPLITTER.apply(input.trim())
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	static int exhaustiveSearch(int[] input, IntBinaryOperator crabCost) {
		var max = Arrays.stream(input).max().orElseThrow();
		return IntStream.range(0, max + 1)
				.map(p -> Arrays.stream(input)
						.map(x -> crabCost.applyAsInt(p, x))
						.sum()
				)
				.min()
				.orElseThrow();
	}

	static int part1(int[] input) {
		var optimalPosition = median(input);
		return Arrays.stream(input).map(x -> Math.abs(x - optimalPosition)).sum();
	}

	static int part2(int[] input) {
		return exhaustiveSearch(input, (p, x) -> intSummation(distance(p, x)));
	}

	static int distance(int a, int b) {
		return Math.abs(a - b);
	}

	static int intSummation(int limitInclusive) {
		return limitInclusive * (limitInclusive + 1) / 2;
	}

	static int median(int[] numbers) {
		int[] destructibleNumbers = new int[numbers.length];
		System.arraycopy(numbers, 0, destructibleNumbers, 0, numbers.length);
		return quickSelect(destructibleNumbers, destructibleNumbers.length / 2);
	}

	// Thank you, Wikipedia
	static int quickSelect(int[] numbers, int k) {
		int start = 0;
		int end = numbers.length - 1;
		while (true) {
			assert start >= 0 && end < numbers.length && start <= end;
			if (start == end) {
				return numbers[start];
			}
			int pivotIndex = (start + end) / 2;
			assert pivotIndex >= start && pivotIndex <= end;
			pivotIndex = quickPartition(numbers, start, end, pivotIndex);
			if (k == pivotIndex) {
				return numbers[k];
			} else if (k < pivotIndex) {
				end -= 1;
			} else {
				start += 1;
			}
		}
	}

	static int quickPartition(int[] numbers, int start, int end, int pivotIndex) {
		int pivotVal = numbers[pivotIndex];
		swap(numbers, pivotIndex, end);
		int newIndex = start;
		for (int i = start; i < end; i++) {
			if (numbers[i] < pivotVal) {
				swap(numbers, newIndex, i);
				newIndex++;
			}
		}
		swap(numbers, end, newIndex);
		return newIndex;
	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
