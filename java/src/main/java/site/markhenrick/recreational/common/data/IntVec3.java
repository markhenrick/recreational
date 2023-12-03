package site.markhenrick.recreational.common.data;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

public record IntVec3(int x, int y, int z) {
	// TODO perhaps an IntVec wrapping an arbitrarily sized int[] is called for. Just assert matching size
	public static final IntVec3 ORIGIN = new IntVec3(0, 0, 0);
	public static final IntVec3 UNIT_X = new IntVec3(1, 0, 0);
	public static final IntVec3 UNIT_Y = new IntVec3(0, 1, 0);
	public static final IntVec3 UNIT_Z = new IntVec3(0, 0, 1);

	public IntVec3 map(IntUnaryOperator fn) {
		return new IntVec3(fn.applyAsInt(x), fn.applyAsInt(y), fn.applyAsInt(z));
	}

	public IntVec3 reduce(IntBinaryOperator fn, IntVec3 other) {
		//noinspection SuspiciousNameCombination
		return new IntVec3(fn.applyAsInt(this.x, other.x), fn.applyAsInt(this.y, other.y), fn.applyAsInt(this.z, other.z));
	}

	public boolean allMatch(IntPredicate pred) {
		return pred.test(x) && pred.test(y) && pred.test(z);
	}

	public boolean anyMatch(IntPredicate pred) {
		return pred.test(x) || pred.test(y) || pred.test(z);
	}

	public boolean allMatch(BiFunction<Integer, Integer, Boolean> fn, IntVec3 other) {
		return fn.apply(this.x, other.x) && fn.apply(this.y, other.y) && fn.apply(this.z, other.z);
	}

	public boolean anyMatch(BiFunction<Integer, Integer, Boolean> fn, IntVec3 other) {
		return fn.apply(this.x, other.x) || fn.apply(this.y, other.y) || fn.apply(this.z, other.z);
	}

	public int componentSum() {
		return x + y + z;
	}

	public int componentProduct() {
		return x * y * z;
	}

	public IntVec3 add(IntVec3 addendum) {
		return reduce(Integer::sum, addendum);
	}

	public IntVec3 scale(int scalar) {
		return map(x -> x * scalar);
	}

	public boolean boundedBy(IntVec3 other) {
		return allMatch((x, y) -> x <= y, other);
	}

	public boolean strictlyBoundedBy(IntVec3 other) {
		return allMatch((x, y) -> x < y, other);
	}
}
