package site.markhenrick.recreational.common.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BinaryRelationTest {
	private BinaryRelation<Character> exampleRelation;

	@BeforeEach
	void setUp() {
		// a-() b->c<->d->e
		exampleRelation = new BinaryRelation<>(List.of('a', 'b', 'c', 'd', 'e'));
		exampleRelation.set('a', 'a');
		exampleRelation.set('b', 'c');
		exampleRelation.set('c', 'd');
		exampleRelation.set('d', 'c');
		exampleRelation.set('d', 'e');
	}

	@ParameterizedTest
	@CsvSource({
		"a, a, true",
		"a, b, false",
		"b, b, false",
		"b, c, true",
		"c, b, false",
		"c, d, true",
		"d, c, true",
		"b, d, false",
	})
	void binaryRelationRetention(final char source, final char target, final boolean expected) {
		assertThat(exampleRelation.get(source, target)).isEqualTo(expected);
	}

	@Test
	void transitivelyClose() {
		// a-() b->c<->d->e
		final var expected = new BinaryRelation<>(List.of('a', 'b', 'c', 'd', 'e'));
		expected.set('a', 'a');
		expected.set('b', 'c');
		expected.set('c', 'd');
		expected.set('d', 'c');
		expected.set('d', 'e');

		expected.set('b', 'd');
		expected.set('b', 'e');
		expected.set('c', 'e');
		expected.set('c', 'c');
		expected.set('d', 'd');

		exampleRelation.transitivelyClose();

		assertThat(exampleRelation).isEqualTo(expected);
	}
}
