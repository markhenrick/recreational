package site.markhenrick.recreational.adventofcode.y2023;

import lombok.val;

import java.util.HashMap;
import java.util.stream.IntStream;

public class Day07 {
	private static final int HAND_SIZE = 5;
	@SuppressWarnings("SpellCheckingInspection")
	public static final String CARD_STRENGTH_ASC = "23456789TJQKA";

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

	static int compareHandsLexicographically(String hand0, String hand1) {
		assert hand0.length() == hand1.length();
		// This is a really stupid way to do it versus just a for loop
		// TODO still need that zip(with) function
		return IntStream.range(0, hand0.length())
				.map(i -> compareCard(hand0.charAt(i), hand1.charAt(i)))
				.peek(System.out::println)
				.filter(x -> x != 0)
				.findFirst()
				.getAsInt();
	}

	static int compareCard(char card0, char card1) {
		// String is too small for anything other than a linear search to be worth it
		return CARD_STRENGTH_ASC.indexOf(card0) - CARD_STRENGTH_ASC.indexOf(card1);
	}

	enum HandType {
		FIVE, FOUR, FULL, THREE, TWO_PAIR, ONE_PAIR, HIGH,
	}
}
