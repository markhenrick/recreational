package site.markhenrick.recreational.common.data;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ZipSpliterator<A, B, X> extends Spliterators.AbstractSpliterator<X> {
	private final BiFunction<A, B, X> zipper;
	private final Spliterator<A> aSpliterator;
	private final Spliterator<B> bSpliterator;
	private boolean aHasNext = true;
	private boolean bHasNext = true;

	public ZipSpliterator(BiFunction<A, B, X> zipper, Stream<A> aStream, Stream<B> bStream) {
		// A bit stuck here. We can only call spliterator() once, but must call super first, so we just have to give a pessimistic size estimate and characteristics
		super(0, 0);
		this.zipper = zipper;
		this.aSpliterator = aStream.spliterator();
		this.bSpliterator = bStream.spliterator();
	}

	@Override
	public boolean tryAdvance(Consumer<? super X> action) {
		if (!aHasNext || !bHasNext) {
			return false;
		}
		aHasNext = aSpliterator.tryAdvance(aElem ->
			bHasNext = bSpliterator.tryAdvance(bElem ->
				action.accept(zipper.apply(aElem, bElem))
			)
		);
		return aHasNext && bHasNext;
	}
}
