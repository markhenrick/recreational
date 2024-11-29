package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.IntVec2
import java.lang.Math.clamp

object Day02 {
    // TODO create a Matrix data class
    private val board = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
    )
    private val moves = mapOf(
        'U' to IntVec2(-1, 0),
        'R' to IntVec2(0, 1),
        'D' to IntVec2(1, 0),
        'L' to IntVec2(0, -1),
    )

    fun solvePart1(input: String): String {
        val output = StringBuilder()
        // We can't just be clever and (easily) use a plain int with +/-3 for rows due to the need to stop at edges
        var position = IntVec2(1, 1)
        for (line in input.lineSequence()) {
            for (char in line) {
                val candidatePosition = position + moves[char]!!
                position = IntVec2(clamp(candidatePosition.i), clamp(candidatePosition.j))
            }
            output.append(board[position.i][position.j])
        }
        return output.toString()
    }

    fun solvePart2(input: String): String = TODO()

    private fun clamp(x: Int): Int = clamp(x.toLong(), 0, 2)
}