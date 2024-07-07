package site.markhenrick.recreational.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.*;
import java.util.stream.IntStream;

import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;

@UtilityClass
public final class CollectionUtil {

	// Remind me to check out Guava sometime
	public static <K, V> Map<V, Set<K>> invertMap(Map<K, V> input) {
		var output = new HashMap<V, Set<K>>();
		for (var entry : input.entrySet()) {
			var singleton = new HashSet<K>();
			singleton.add(entry.getKey());
			output.merge(entry.getValue(), singleton, applyAndReturnLeft(Set::addAll));
		}
		return output;
	}

	public static <T> T unwrapSingleton(Collection<T> collection) {
		assert collection.size() == 1;
		return collection.stream().findAny().get();
	}

	public static BitSet bitSetOf(int... ints) {
		val set = new BitSet();
		for (val integer : ints) {
			set.set(integer);
		}
		return set;
	}

	public static <T> List<T> mutableListOf(T... values) {
		val list = new ArrayList<T>(values.length);
		list.addAll(Arrays.asList(values));
		return list;
	}

	public static <T> boolean isRectangular(List<List<T>> array) {
		if (array.isEmpty()) return true;
		val width = array.get(0).size();
		return array.stream().allMatch(list -> list.size() == width);
	}

	public static <T> boolean isRectangular(T[][] array) {
		if (array.length == 0) return true;
		val width = array[0].length;
		return Arrays.stream(array).allMatch(list -> list.length == width);
	}

	public static <T> List<List<T>> transpose(List<List<T>> array) {
		assert isRectangular(array);
		if (array.isEmpty()) return Collections.emptyList();
		return IntStream.range(0, array.get(0).size())
			.mapToObj(i -> array.stream()
				.map(ts -> ts.get(i))
				.toList()
			)
			.toList();
	}
}
