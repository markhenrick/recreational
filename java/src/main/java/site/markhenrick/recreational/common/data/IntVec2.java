package site.markhenrick.recreational.common.data;

public record IntVec2(int x, int y) {
	public static final IntVec2 ORIGIN = new IntVec2(0, 0);
	public static final IntVec2 UNIT_X = new IntVec2(1, 0);
	public static final IntVec2 UNIT_Y = new IntVec2(0, 1);

	public IntVec2 add(IntVec2 addendum) {
		return new IntVec2(this.x + addendum.x, this.y + addendum.y);
	}

	public IntVec2 scale(int scalar) {
		return new IntVec2(this.x * scalar, this.y * scalar);
	}
}
