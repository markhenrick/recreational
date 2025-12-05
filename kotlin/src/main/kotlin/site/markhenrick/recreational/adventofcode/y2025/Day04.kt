package site.markhenrick.recreational.adventofcode.y2025

// Not really feeling the point of arrays here. Especially given Kotlin's operator functions
// Making it mutable rather than maintaining two nominative types of similar structure
typealias Board = MutableList<MutableList<Boolean>>

private const val THRESHOLD = 4

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
            .count { it < THRESHOLD }
    }

    fun solvePart2(input: String): Int {
        val board = parseInput(input)
        return solvePart2Internal(board, 0)
    }

    tailrec fun solvePart2Internal(board: Board, previouslyRemoved: Int): Int {
        val height = board.size
        val width = board[0].size
        val nextBoard = createEmptyBoard(height, width)
        var newlyRemoved = 0
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j]) {
                    val neighbours = countNeighbours(board, i, j)
                    if (neighbours < THRESHOLD) {
                        newlyRemoved++
                    } else {
                        nextBoard[i][j] = true
                    }
                }
            }
        }
        return if (newlyRemoved > 0) {
            solvePart2Internal(nextBoard, previouslyRemoved + newlyRemoved)
        } else {
            previouslyRemoved
        }
    }

    private fun createEmptyBoard(height: Int, width: Int): Board = IntRange(1, height)
        .map { i -> IntRange(1, width).map { j -> false }.toMutableList() }
        .toMutableList()

    private fun parseInput(input: String): Board {
        val board = input.lineSequence()
            .map { it.trim()}
            .map { parseLine(it.toCharArray()) }
            .toMutableList()
        require(!board.isEmpty())
        val width = board[0].size
        require(board.all { it.size == width })
        return board
    }

    private fun parseLine(input: CharArray): MutableList<Boolean> = input.map {
        require(it == '.' || it == '@')
        it == '@'
    }.toMutableList()

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
