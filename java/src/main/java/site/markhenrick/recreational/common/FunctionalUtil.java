package site.markhenrick.recreational.common;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@UtilityClass
public class FunctionalUtil {
	// Probably in the stdlib somewhere
	public static <A> Predicate<A> constantPredicate(final boolean value) {
		return a -> value;
	}

	public static <A> BinaryOperator<A> applyAndReturnLeft(final BiConsumer<? super A, ? super A> mutatingOperator) {
		return (l, r) -> {
			mutatingOperator.accept(l, r);
			return l;
		};
	}

	public static <A, B> Function<A, Pair<A, B>> zipApply(final Function<? super A, ? extends B> fn) {
		return a -> new FunctionalUtil.Pair<>(a, fn.apply(a));
	}

	// TODO move this to its own file
	public record Pair<L, R> (L l, R r) {
		public <LPrime> Pair<LPrime, R> mapL(final Function<? super L, ? extends LPrime> fn) {
			return new Pair<>(fn.apply(l), r);
		}

		public <RPrime> Pair<L, RPrime> mapR(final Function<? super R, ? extends RPrime> fn) {
			return new Pair<>(l, fn.apply(r));
		}

		public static <T> Pair<T, T> fromArray(T[] array) {
			assert array.length == 2; // should be a hard assert in prod code
			return new Pair<>(array[0], array[1]);
		}

		public static <L, R, T> Function<Pair<L, R>, T> curry(BiFunction<L, R, T> fn) {
			return pair -> fn.apply(pair.l(), pair.r());
		}

		public static <L, R, LPrime> Function<Pair<L, R>, Pair<LPrime, R>> leftMapper(Function<L, LPrime> fn) {
			return pair -> pair.mapL(fn);
		}

		public static <L, R, RPrime> Function<Pair<L, R>, Pair<L, RPrime>> rightMapper(Function<R, RPrime> fn) {
			return pair -> pair.mapR(fn);
		}
	}

	public static Stream<Character> charStream(char[] source) {
		return IntStream.rangeClosed(0, source.length - 1)
			.mapToObj(i -> source[i]);
	}

	public static <T> Stream<T> concat(Stream<T>... streams) {
		return Arrays.stream(streams)
			.reduce(Stream::concat)
			.get();
	}
}
