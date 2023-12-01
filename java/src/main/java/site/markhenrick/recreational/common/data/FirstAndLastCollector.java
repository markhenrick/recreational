package site.markhenrick.recreational.common.data;

import org.javatuples.Pair;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Find the first and last elements of a stream (by order of yield, not sorting). If it is empty, so is the optional.
 * If it is a singleton, the pair just has the same value for both entries.
 * <p>
 * Order of return is Pair.with(first, last)
 */
public class FirstAndLastCollector<T> implements Collector<T, T[], Optional<Pair<T, T>>> {
	// TODO possible to just create a collector combiner? If so, this class will be made redundant

	@Override
	public Supplier<T[]> supplier() {
		//noinspection unchecked
		return () -> (T[]) new Object[2];
	}

	@Override
	public BiConsumer<T[], T> accumulator() {
		return (array, newVal) -> {
			Objects.requireNonNull(newVal);
			if (array[0] == null) {
				array[0] = newVal;
			}
			array[1] = newVal;
		};
	}

	@Override
	public BinaryOperator<T[]> combiner() {
		return (array0, array1) -> {
			if (array0[0] == null) {
				assert array0[1] == null;
				return array1;
			}
			if (array1[1] != null) {
				array0[1] = array1[1];
			}
			return array0;
		};
	}

	@Override
	public Function<T[], Optional<Pair<T, T>>> finisher() {
		return array -> array[0] != null ? Optional.of(Pair.with(array[0], array[1])) : Optional.empty();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.emptySet();
	}
}
