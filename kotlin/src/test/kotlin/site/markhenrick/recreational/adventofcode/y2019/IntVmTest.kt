package site.markhenrick.recreational.adventofcode.y2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class IntVmTest {

    // TODO unit tests for instructions

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = [
        "1,0,0,0,99;2,0,0,0,99",
        "2,3,0,3,99;2,3,0,6,99",
        "2,4,4,5,99,0;2,4,4,5,99,9801",
        "1,1,1,4,99,5,6,0,99;30,1,1,4,2,5,6,0,99",
    ])
    fun completeExecutionTests(inputRaw: String, expectedRaw: String) {
        val input = inputRaw.split(",").map { it.toInt() }
        val expected = expectedRaw.split(",").map { it.toInt() }.toIntArray()
        val vm = IntVm(input)
        vm.runUntilHalt()
        assertThat(vm.memory).isEqualTo(expected)
    }
}
