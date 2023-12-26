package site.markhenrick.recreational.rummikub;

import lombok.val;

import java.util.BitSet;
import java.util.List;

public class GameLogic {
	private static final int LOWEST_ORDER = 1;
	private static final int HIGHEST_ORDER = 13;
	private static final int MIN_SET_SIZE = 3;

	// TODO, consider just using Set<Tile>, since neither sets nor groups can have duplicates
	// UI can just render with a simple sort
	// Hand can, and the user may want to order it, so that remains a list

	// TODO next step: validate move (hand, board) -> (hand, board)

	public static boolean validateSet(List<Tile> set) {
		return tilesInRange(set) && (validateRun(set) || validateGroup(set));
	}

	// TODO - might be best to validate this someplace else and just have it as a debug assert here
	private static boolean tilesInRange(List<Tile> run) {
		return run.stream()
				.map(Tile::order)
				.allMatch(order -> order >= LOWEST_ORDER && order <= HIGHEST_ORDER);
	}

	private static boolean validateRun(List<Tile> run) {
		if (run.size() < MIN_SET_SIZE || run.size() > (HIGHEST_ORDER - LOWEST_ORDER + 1)) return false;
		var previousTile = run.get(0);
		// TODO could do this by zipping with an int stream from first tile
		for (int i = 1; i < run.size(); i++) {
			var tile = run.get(i);
			if (tile.suit() != previousTile.suit()) return false;
			if (tile.order() != previousTile.order() + 1) return false;
			previousTile = tile;
		}
		return true;
	}

	private static boolean validateGroup(List<Tile> set) {
		if (set.size() < MIN_SET_SIZE || set.size() > Tile.Suit.values().length) return false;
		val suitsSeen = new BitSet(); // TODO if using a set in the first place, this would be redundant
		val ordersSeen = new BitSet();
		for (val tile : set) {
			val suitIndex = tile.suit().ordinal();
			if (suitsSeen.get(suitIndex)) return false;
			suitsSeen.set(suitIndex);
			ordersSeen.set(tile.order());
			if (ordersSeen.cardinality() > 1) return false;
		}
		return true;
	}
}
