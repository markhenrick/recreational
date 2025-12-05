package site.markhenrick.recreational.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringUtilTest {
    @ParameterizedTest
    @ValueSource(chars = ['0', '1', '8', '9'])
    fun digitToInt(input: Char) {
        assertThat(input.digitToInt()).isEqualTo(input.toString().toInt())
    }
}
