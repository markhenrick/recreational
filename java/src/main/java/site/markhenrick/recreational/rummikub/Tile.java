package site.markhenrick.recreational.rummikub;

public record Tile(Suit suit, int order) {

	public enum Suit {
		// Not using black to allow unambiguous initials
		R, G, B, Y,
	}
}
