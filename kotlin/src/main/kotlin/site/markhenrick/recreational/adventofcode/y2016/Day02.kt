package site.markhenrick.recreational.adventofcode.y2016

import site.markhenrick.recreational.common.IntVec2

object Day02 {
    private val moves = mapOf(
        'U' to IntVec2(-1, 0),
        'R' to IntVec2(0, 1),
        'D' to IntVec2(1, 0),
        'L' to IntVec2(0, -1),
    )

    // TODO create a Matrix data class
    private val keypad1 = listOf(
        listOf('1', '2', '3'),
        listOf('4', '5', '6'),
        listOf('7', '8', '9'),
    )
    private val keypad2 = listOf(
        listOf(null, null, '1', null, null),
        listOf(null, '2', '3', '4', null),
        listOf('5', '6', '7', '8', '9'),
        listOf(null, 'A', 'B', 'C', null),
        listOf(null, null, 'D', null, null)
    )

    private val startPos1 = IntVec2(1, 1)
    private val startPos2 = IntVec2(2, 0)

    fun solvePart1(input: String): String = solve(keypad1, startPos1, input)
    fun solvePart2(input: String): String = solve(keypad2, startPos2, input)

    private fun solve(keypad: List<List<Char?>>, startPos: IntVec2, input: String): String {
        val output = StringBuilder()
        var position = startPos
        for (line in input.lineSequence()) {
            for (char in line) {
                val candidatePosition = position + moves[char]!!
                if (
                    candidatePosition.i in keypad.indices &&
                    candidatePosition.j in keypad[candidatePosition.i].indices &&
                    keypad[candidatePosition.i][candidatePosition.j] != null
                ) {
                    position = candidatePosition
                }
            }
            output.append(keypad[position.i][position.j])
        }
        return output.toString()
    }
}