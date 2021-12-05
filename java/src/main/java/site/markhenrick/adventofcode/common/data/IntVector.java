package site.markhenrick.adventofcode.common.data;

public record IntVector(int x, int y) {
	public IntVector add(IntVector addendum) {
		return new IntVector(this.x + addendum.x, this.y + addendum.y);
	}

	public IntVector scale(int scalar) {
		return new IntVector(this.x * scalar, this.y * scalar);
	}
}
