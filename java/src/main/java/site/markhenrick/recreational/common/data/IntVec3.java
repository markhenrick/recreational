package site.markhenrick.recreational.common.data;

public record IntVec3(int x, int y, int z) {
	// TODO perhaps an IntVec wrapping an arbitrarily sized int[] is called for. Just assert matching size
	public static final IntVec3 ORIGIN = new IntVec3(0, 0, 0);
	public static final IntVec3 UNIT_X = new IntVec3(1, 0, 0);
	public static final IntVec3 UNIT_Y = new IntVec3(0, 1, 0);
	public static final IntVec3 UNIT_Z = new IntVec3(0, 0, 1);

	public IntVec3 add(IntVec3 addendum) {
		return new IntVec3(this.x + addendum.x, this.y + addendum.y, this.z + addendum.z);
	}

	public IntVec3 scale(int scalar) {
		return new IntVec3(x * scalar, y * scalar, z * scalar);
	}

	public int componentSum() {
		return x + y + z;
	}

	public int componentProduct() {
		return x * y * z;
	}

	public boolean boundedBy(IntVec3 other) {
		return this.x <= other.x && this.y <= other.y && this.z <= other.z;
	}

	public boolean strictlyBoundedBy(IntVec3 other) {
		return this.x < other.x && this.y < other.y && this.z < other.z;
	}
}
