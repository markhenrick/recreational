package site.markhenrick.adventofcode.common.data;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;

public class SparseMatrix<T> {
	Integer maxI = 0;
	Integer maxJ = 0;
	Map<IntVector, T> elements = new HashMap<>();

	public synchronized T get(int i, int j) {
		return elements.get(new IntVector(i, j));
	}

	public synchronized T set(int i, int j, T element) {
		var coord = new IntVector(i, j);
		var old = elements.get(coord);
		if (element != null) {
			elements.put(coord, element);
			maxI = max(maxI, i);
			maxJ = max(maxJ, j);
		} else {
			elements.remove(coord);
		}
		return old;
	}

	public synchronized Integer getMaxI() {
		if (maxI == null) {
			maxI = elements.keySet().stream()
				.mapToInt(IntVector::x)
				.max()
				.orElse(0);
		}
		return maxI;
	}

	public synchronized Integer getMaxJ() {
		if (maxJ == null) {
			maxJ = elements.keySet().stream()
				.mapToInt(IntVector::y)
				.max()
				.orElse(0);
		}
		return maxJ;
	}
}
