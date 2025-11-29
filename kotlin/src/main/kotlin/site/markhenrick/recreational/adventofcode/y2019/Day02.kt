package site.markhenrick.recreational.adventofcode.y2019

object Day02 {
    fun part1(input: String): Int {
        val inputMemory = input.split(",")
            .map { it.toInt() }
            .toMutableList()
        inputMemory[1] = 12
        inputMemory[2] = 2
        val vm = IntVm(inputMemory)
        vm.runUntilHalt()
        return vm.memory[0]
    }
}
