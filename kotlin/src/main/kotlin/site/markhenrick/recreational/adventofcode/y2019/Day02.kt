package site.markhenrick.recreational.adventofcode.y2019

object Day02 {
    fun part1(input: String): Int {
        val program = input.split(",")
            .map { it.toInt() }
        return runWithNV(program, 12, 2)
    }

    fun part2(input: String): Int {
        val program = input.split(",")
            .map { it.toInt() }
        // Initial brute force attempt. TODO find a better way. Static analysis time?
        for (noun in 0..99) {
            for (verb in 0..99) {
                val result = runWithNV(program, noun, verb)
                if (result == 19690720) {
                    return 100 * noun + verb
                }
            }
        }
        throw AssertionError("No solution found")
    }

    private fun runWithNV(program: List<Int>, noun: Int, verb: Int): Int {
        val vm = IntVm(program)
        vm.memory[1] = noun
        vm.memory[2] = verb
        vm.runUntilHalt()
        return vm.memory[0]
    }
}
