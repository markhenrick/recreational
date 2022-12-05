package site.markhenrick.recreational.common;

import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class CollectionUtil {
	private CollectionUtil() {}

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
}
