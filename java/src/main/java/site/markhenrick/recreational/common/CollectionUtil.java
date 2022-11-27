package site.markhenrick.recreational.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static site.markhenrick.recreational.common.FunctionalUtil.applyAndReturnLeft;

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
}
