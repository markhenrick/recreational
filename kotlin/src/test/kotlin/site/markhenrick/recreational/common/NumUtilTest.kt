package site.markhenrick.recreational.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NumUtilTest {
    @ParameterizedTest
    @CsvSource(
        "1, 1",
        "2, 1",
        "9, 1",
        "10, 2",
        "11, 2",
        "19, 2",
        "20, 2",
        "99, 2",
        "100, 3",
        "0, 0",
    )
    fun base10DigitCount(input: Long, expected: Long) {
        assertThat(base10DigitCount(input)).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1, 0",
        "0, 2, 0",
        "1, 0, 1",
        "1, 1, 1",
        "1, 2, 1",
        "2, 0, 1",
        "2, 1, 2",
        "2, 2, 4",
        "-1, 0, 1",
        "-1, 1, -1",
        "-1, 2, 1",
    )
    fun intPow(base: Long, exponent: Long, result: Long) {
        assertThat(intPow(base, exponent)).isEqualTo(result)
    }
}
