package site.markhenrick.recreational.adventofcode.y2019

class IntVm {
    // Arguably should protect these rather than just making them publicly mutable
    val memory: IntArray
    var pc = 0

    constructor(program: List<Int>) {
        memory = program.toIntArray()
    }

    fun step() {
        val opcode = memory[pc]
        val arg1 = memory[(pc + 1) % memory.size]
        val arg2 = memory[(pc + 2) % memory.size]
        val arg3 = memory[(pc + 3) % memory.size]

        try {
            when (opcode) {
                99 -> throw HaltedException()
                1 -> add(arg1, arg2, arg3)
                2 -> mult(arg1, arg2, arg3)
                else -> throw InvalidOpCodeException(opcode)
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            throw OOBMemoryAccessException(e)
        }

        pc = (pc + 4) % memory.size
    }

    fun runUntilHalt() {
        while(true) {
            try {
                step()
            } catch (e: HaltedException) {
                break
            }
        }
    }

    private fun add(leftA: Int, rightA: Int, destA: Int) {
        memory[destA] = memory[leftA] + memory[rightA]
    }

    private fun mult(leftA: Int, rightA: Int, destA: Int) {
        memory[destA] = memory[leftA] * memory[rightA]
    }
}

open class IntVmException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
// Undecided if this is the best way to model halting
class HaltedException(): IntVmException("The machine is halted")
class InvalidOpCodeException(opcode: Int) : IntVmException("Invalid opcode: $opcode")
class OOBMemoryAccessException(e: ArrayIndexOutOfBoundsException) : IntVmException("Tried to access invalid address", e)
