package site.markhenrick.recreational.adventofcode.y2019

class IntVm {
    // Arguably should protect these rather than just making them publicly mutable
    val memory: IntArray
    var pc = 0

    constructor(program: List<Int>) {
        memory = program.toIntArray()
    }

    fun step() {
        try {
            when (val opcode = memory[pc]) {
                1 -> add()
                2 -> mult()
                99 -> halt()
                else -> throw InvalidOpCodeException(opcode)
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            throw OOBMemoryAccessException(e)
        }
    }

    fun runUntilHalt() {
        while(true) {
            try {
                step()
            } catch (_: HaltedException) {
                break
            }
        }
    }

    private fun halt(): Nothing = throw HaltedException()

    private fun add() {
        val leftA = memory[pc + 1]
        val rightA = memory[pc + 2]
        val destA = memory[pc + 3]
        memory[destA] = memory[leftA] + memory[rightA]
        pc += 4
    }

    private fun mult() {
        val leftA = memory[pc + 1]
        val rightA = memory[pc + 2]
        val destA = memory[pc + 3]
        memory[destA] = memory[leftA] * memory[rightA]
        pc += 4
    }
}

open class IntVmException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
// Undecided if this is the best way to model halting
class HaltedException(): IntVmException("The machine is halted")
class InvalidOpCodeException(opcode: Int) : IntVmException("Invalid opcode: $opcode")
class OOBMemoryAccessException(e: ArrayIndexOutOfBoundsException) : IntVmException("Tried to access invalid address", e)
