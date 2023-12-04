package site.markhenrick.recreational.common;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class StatUtil {
	public static long distance(long a, long b) {
		return Math.abs(a - b);
	}

	public static long intSummation(long limitInclusive) {
		return limitInclusive * (limitInclusive + 1) / 2;
	}

	public static double mean(long[] numbers) {
		return (double) Arrays.stream(numbers).sum() / numbers.length;
	}

	public static long median(long[] numbers) {
		return quickSelect(numbers, numbers.length / 2);
	}

	// Thank you, Wikipedia
	public static long quickSelect(long[] numbers, int k) {
		long[] destructibleNumbers = new long[numbers.length];
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

	public static int quickPartition(long[] numbers, int start, int end, int pivotIndex) {
		var pivotVal = numbers[pivotIndex];
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

	public static void swap(long[] arr, int i, int j) {
		var tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
