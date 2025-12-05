package site.markhenrick.recreational.adventofcode.y2025

// Not really feeling the point of arrays here. Especially given Kotlin's operator functions
typealias Board = List<List<Boolean>>

object Day04 {
    fun solvePart1(input: String): Int {
        val board = parseInput(input)
        val height = board.size
        val width = board[0].size
        // Let's do this differently (and more slowly) to below for no real reason
        return IntRange(0, height - 1)
            .flatMap {
                x -> IntRange(0, width - 1)
                    .filter { y -> board[x][y] }
                    .map { y -> countNeighbours(board, x, y) }
            }
            .count { it < 4}
    }

    private fun parseInput(input: String): Board {
        val board = input.lineSequence()
            .map { it.trim()}
            .map { parseLine(it.toCharArray()) }
            .toList()
        require(!board.isEmpty())
        val width = board[0].size
        require(board.all { it.size == width })
        return board
    }

    private fun parseLine(input: CharArray): List<Boolean> = input.map {
        require(it == '.' || it == '@')
        it == '@'
    }

    // It is real Conway hours
    private fun countNeighbours(board: Board, x: Int, y: Int): Int {
        var total = 0
        for (i in x - 1..x + 1) {
            for (j in y - 1..y + 1) {
                if (
                    (i != x || j != y) &&
                    i >= 0 && i < board.size &&
                    j >= 0 && j < board[i].size &&
                    board[i][j]
                ) {
                    total++
                }
            }
        }
        return total
    }
}
