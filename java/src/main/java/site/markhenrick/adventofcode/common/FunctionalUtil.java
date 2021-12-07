package site.markhenrick.adventofcode.common;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

	public record Pair<L, R> (L l, R r) {
		public <LPrime> Pair<LPrime, R> mapL(final Function<? super L, ? extends LPrime> fn) {
			return new Pair<>(fn.apply(l), r);
		}

		public <RPrime> Pair<L, RPrime> mapR(final Function<? super R, ? extends RPrime> fn) {
			return new Pair<>(l, fn.apply(r));
		}
	}

	public static Stream<Character> charStream(char[] source) {
		return IntStream.rangeClosed(0, source.length - 1)
			.mapToObj(i -> source[i]);
	}
}
