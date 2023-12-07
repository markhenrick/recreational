package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;

import java.util.HashMap;

public class Day07 {
	private static final int HAND_SIZE = 5;

	// So the invariant that x.uniqueCardCount() > y.uniqueCardCount() <=> x > y does hold, meaning it would be possible
	// to have a comparator chain that does that first, and only looks deeper for FOUR/FULL and THREE/TWO_PAIR, but the
	// optimisation would be tiny (quite possible negative), and require more complex effort to avoid building `cardCounts`
	// twice (could do it by mapping to a LinkedHashMap early)
	static HandType getHandType(char[] hand) {
		assert hand.length == HAND_SIZE;
		val cardCounts = new HashMap<Character, Integer>(HAND_SIZE);
		// TODO probably a stdlib function for this. Write one if not
		for (val card : hand) {
			cardCounts.merge(card, 1, Integer::sum);
		}
		val uniqueCardCount = cardCounts.size();
		switch (uniqueCardCount) {
			case 1:
				return HandType.FIVE;
			case 2: {
				val countOfOneType = cardCounts.get(hand[0]);
				return countOfOneType == 1 || countOfOneType == 4 ? HandType.FOUR : HandType.FULL;
			}
			case 3: {
				var countOfOneType = cardCounts.get(hand[0]);
				if (countOfOneType == 1) {
					countOfOneType = cardCounts.get(hand[1]);
				}
				return countOfOneType == 3 ? HandType.THREE : HandType.TWO_PAIR;
			}
			case 4:
				return HandType.ONE_PAIR;
			case 5:
				return HandType.HIGH;
			default:
				throw new AssertionError("Unexpected uniqueCardCount = " + uniqueCardCount);
		}
	}

	enum HandType {
		FIVE, FOUR, FULL, THREE, TWO_PAIR, ONE_PAIR, HIGH,
	}
}
