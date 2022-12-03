package site.markhenrick.recreational.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class TopNCollector<T> implements Collector<T, List<T>, List<T>> {
	// Invariants:
	// Collecting list ascends
	// Collecting list never contains nulls as valid values
	// TODO add configurable behaviour on duplicates (first, last, insert before, insert after)

	private final int count;
	private final Comparator<? super T> comparator;

	public TopNCollector(final int count, final Comparator<? super T> comparator) {
		if (count < 0) {
			throw new IllegalArgumentException("Negative count");
		}
		this.count = count;
		this.comparator = comparator;
	}

	@Override
	public Supplier<List<T>> supplier() {
		return count > 0 ? () -> new ArrayList<>(count) : Collections::emptyList;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return count == 0 ? (list, candidate) -> {} : (list, candidate) -> {
			assert list.size() <= count;
			Objects.requireNonNull(candidate);
			if (list.size() == count) {
				int i;
				// Traverse ascending and push down any elements < candidate
				// Might technically be a little quicker to just find the partition point and then do System.arraycopy
				for (i = 0; i < list.size() && comparator.compare(candidate, list.get(i)) > 0; i++) {
					if (i > 0) {
						list.set(i - 1, list.get(i));
					}
				}
				if (i > 0) {
					list.set(i - 1, candidate);
				}
			} else {
				list.add(null); // Needed to make the end index "exist"
				int i;
				// Traverse descending and push up any elements > candidate
				for (i = list.size() - 2; i >= 0 && comparator.compare(candidate, list.get(i)) < 0; i--) {
					list.set(i + 1, list.get(i));
				}
				list.set(i + 1, candidate);
			}
		};
	}


	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			assert list1.size() <= count;
			assert list2.size() <= count;
			var result = supplier().get();
			for (var i = 0; i < count; i++) {
				result.add(null); // Fill out capacity
			}
			int list1i = list1.size() - 1;
			var list2i = list2.size() - 1;
			var resulti = count - 1;
			for (resulti = count - 1; resulti >= 0; resulti--) {
				var list1candidate = list1i >= 0 ? list1.get(list1i) : null;
				var list2candidate = list2i >= 0 ? list2.get(list2i) : null;
				if (list1candidate == null && list2candidate == null) {
					break;
				} else if (list1candidate == null) {
					result.set(resulti, list2candidate);
					list2i--;
				} else if (list2candidate == null) {
					result.set(resulti, list1candidate);
					list1i--;
				} else if (comparator.compare(list1candidate, list2candidate) < 0) {
					result.set(resulti, list2candidate);
					list2i--;
				} else {
					result.set(resulti, list1candidate);
					list1i--;
				}
			}
			for (var i = -1; i < resulti; i++) {
				result.remove(0);
			}
			return result;
		};
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Set.of(Characteristics.UNORDERED, Characteristics.IDENTITY_FINISH);
	}
}
