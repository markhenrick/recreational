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
		return Arrays.stream(input)
				.map(x -> distance(x, optimalPosition))
				.sum();
	}

	static int part2(int[] input) {
		var optimalPosition = mean(input);
		var floor = (int) Math.floor(optimalPosition);
		var ceil = (int) Math.ceil(optimalPosition);
		return floor != ceil ? Math.min(totalCostP2(input, floor), totalCostP2(input, ceil)) : totalCostP2(input, ceil);
	}

	static int totalCostP2(int[] input, int position) {
		return Arrays.stream(input)
				.map(x -> distance(x, position))
				.map(Day07::intSummation)
				.sum();
	}

	static int distance(int a, int b) {
		return Math.abs(a - b);
	}

	static int intSummation(int limitInclusive) {
		return limitInclusive * (limitInclusive + 1) / 2;
	}

	static double mean(int[] numbers) {
		return (double) Arrays.stream(numbers).sum() / numbers.length;
	}

	static int median(int[] numbers) {
		return quickSelect(numbers, numbers.length / 2);
	}

	// Thank you, Wikipedia
	static int quickSelect(int[] numbers, int k) {
		int[] destructibleNumbers = new int[numbers.length];
		System.arraycopy(numbers, 0, destructibleNumbers, 0, numbers.length);
		int start = 0;
		int end = destructibleNumbers.length - 1;
		while (true) {
			assert start >= 0 && end < destructibleNumbers.length && start <= end;
			if (start == end) {
				return destructibleNumbers[start];
			}
			int pivotIndex = (start + end) / 2;
			assert pivotIndex >= start && pivotIndex <= end;
			pivotIndex = quickPartition(destructibleNumbers, start, end, pivotIndex);
			if (k == pivotIndex) {
				return destructibleNumbers[k];
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
