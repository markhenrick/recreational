package site.markhenrick.recreational.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@UtilityClass
public class Permutator {
	public static <T> Stream<List<T>> permutations(List<T> list) {
		if (list.size() <= 1) {
			return Stream.of(list);
		}
		return IntStream.range(0, list.size())
			.boxed()
			.flatMap(i -> permutations(listWithout(list, i)).map(subPermutation -> append(subPermutation, list.get(i))));
	}

	// TODO implement a List<T> view that does this without creating a new list
	// And ideally recognises when its working on itself and avoids a chain of ListView(ListView(ListView... objects
	private static <T> List<T> listWithout(List<T> list, int i) {
		assert i >= 0;
		assert list.size() > i;
		val newList = new ArrayList<>(list);
		newList.remove(i);
		return newList;
	}

	// TODO similar idea to above
	private static <T> List<T> append(List<T> list, T appendix) {
		val newList = new ArrayList<>(list);
		newList.add(appendix);
		return newList;
	}
}
